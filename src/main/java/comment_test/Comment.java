package comment_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.*;

import static log.Logger.log;

public class Comment {
    private String number;
    private String text;
    private String active;
    private List<String> categories = new ArrayList<String>();
    //private static WebDriver driver;

    public Comment(String number, String text, String active, List<String> categories) {
        this.number = number;
        this.text = text;
        this.active = active;
        this.categories = categories;
    }

    public String getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public String getActive() {
        return active;
    }

    public List<String> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(number, comment.number) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(active, comment.active) &&
                Objects.equals(categories, comment.categories);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, text, active, categories);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "number='" + number + '\'' +
                ", text='" + text + '\'' +
                ", active='" + active + '\'' +
                ", categories=" + categories +
                '}';
    }

    public static Comment addComment(String commentNumber, List<String> categories, String text, WebDriver driver) {
        log("start adding comment");
        driver.findElement(By.xpath("//input[@value='New...']")).click();
        driver.findElement(By.xpath("//input[@id='Text']")).sendKeys(text);
        driver.findElement(By.xpath("//input[@id='Number']")).sendKeys(commentNumber);

        log("adding category");
        driver.findElement(By.xpath(String.format("//span[text()='%s']/preceding-sibling::input[@id='Categories']", categories.get(0)))).click();
        driver.findElement(By.xpath("//div[@class='selectbuttons']/input[@value='>']")).click();
        driver.findElement(By.xpath("//input[@value='Save & Return']")).click();

        log("end adding comment");
        return new Comment(commentNumber, text, "V", categories);
    }

    public static Comment findCommentByNumber(String commentNumber, WebDriver driver) {
        String commentNumberXpath = String.format("//td[@class='numbercolumn' and text()='%s']", commentNumber);
        log("comment number xpath: " + commentNumberXpath);
        while (true) {
            log("try to find comment");
            try {
                if (driver.findElement(By.xpath(commentNumberXpath)).isDisplayed()) {
                    log("comment found");
                    String text = driver.findElement(By.xpath(commentNumberXpath + "/following-sibling::td[@class='textcolumn']")).getText();
                    String active = driver.findElement(By.xpath(commentNumberXpath + "/following-sibling::td[@class='inactivecolumn']")).getText();
                    List<String> categories = Arrays.asList(driver.findElement(By.xpath(commentNumberXpath + "/following-sibling::td[@class='categorycolumn']"))
                            .getText()
                            .split(", "));
                    return new Comment(commentNumber, text, active, categories);
                }
            } catch (Exception e) {
                log("not found on this page");
            }

            try {
                if (driver.findElement(By.xpath("//a[text()='>']")).isDisplayed()) {
                    driver.findElement(By.xpath("//a[text()='>']")).click();
                } else {
                    return null;
                }
            } catch (Exception e) {
                log("comment not found");
            }
        }
    }
}
