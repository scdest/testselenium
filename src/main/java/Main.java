import comment_test.CommentTable;
import comment_test.Comment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import url.Url;

import javax.xml.bind.SchemaOutputResolver;

import static log.Logger.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Admin\\IdeaProjects\\testselenium\\drivers\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\IdeaProjects\\testselenium\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        Url url = new Url.UrlBuilder("comments.azurewebsites.net").withHttps(false).build();
        driver.get(url.getUrl());
        log("get comment number from all pages");
        List<String> commentNumbers = getCommentNumbers();

        try {
            while (driver.findElement(By.xpath("//a[text()='>']")).isDisplayed()) {
                driver.findElement(By.xpath("//a[text()='>']")).click();
                commentNumbers.addAll(getCommentNumbers());
            }
        } catch (Exception e) {
            log("last page");
            //e.printStackTrace();
        }
        log("get unq value for comment adding");
        String newCommentNumber = getUnqNumberForComment(commentNumbers);
        List<String> categories = new ArrayList<>();
        categories.add("Cat0");
        String text = "SomeText";
        Comment addedComment = Comment.addComment(newCommentNumber,categories,text,driver);
        log("check if added comment is present");
        //driver.findElement(By.xpath("//a[@href='/?page=1']")).click();
        Comment comment = Comment.findCommentByNumber(newCommentNumber,driver);
        System.out.println(comment);
        System.out.println(addedComment.equals(comment));


    }

    public static List<String> getCommentNumbers() {
        List<WebElement> commentNumbersElements = driver.findElements(By.xpath("//td[@class='numbercolumn']"));
        List<String> commentNumbers = new ArrayList<>();
        for (WebElement element : commentNumbersElements) {
            commentNumbers.add(element.getText());
        }
        return commentNumbers;
    }


    public static String getUnqNumberForComment(List<String> commentNumbers) {
        Random r = new Random();
        while (true) {
            String commentNumber = String.valueOf(r.nextInt(999));
            if (!commentNumbers.contains(commentNumber) && !commentNumber.substring(2,2).equals("0")) {
                return commentNumber;
            }
        }
    }
}