package comments_page_object;

import comment_test.Comment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

import static log.Logger.log;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By addCommentButton = By.xpath("//input[@value='New...']");
    private By nextPageButton = By.xpath("//a[text()='>']");
    private By numberColumn = By.xpath("//td[@class='numbercolumn']");
    private By textColumn = By.xpath("//td[@class='textcolumn']");
    private By activeColumn = By.xpath("//td[@class='inactivecolumn']");
    private By categoriesColumn = By.xpath("//td[@class='categorycolumn']");

    public MainPage goToNextPage(){
            if(isNextPageExist()){
                driver.findElement(nextPageButton).click();
                return new MainPage(driver);
            }
        return this;
    }

    public boolean isNextPageExist(){
        try{
            if(driver.findElement(nextPageButton).isDisplayed()){
                return true;
            }
        }catch (Exception e){
            log("last page");
        }
        return false;
    }

    public CommentAddingPage startToAddComment(){
        log("start to add comment");
        String commentUnqNumber = getUnqNumberForComment(getCommentNumbers());
        log(commentUnqNumber);
        log("click");
        driver.findElement(addCommentButton).click();
        return new CommentAddingPage(driver,commentUnqNumber);
    }

    private List<String> getCommentNumbers() {
        List<WebElement> commentNumbersElements = driver.findElements(numberColumn);
        List<String> commentNumbers = new ArrayList<>();
        for (WebElement element : commentNumbersElements) {
            commentNumbers.add(element.getText());
        }
        return commentNumbers;
    }


    private String getUnqNumberForComment(List<String> commentNumbers) {
        Random r = new Random();
        while (true) {
            String commentNumber = String.valueOf(r.nextInt(999));
            if (!commentNumbers.contains(commentNumber) && !(commentNumber.charAt(commentNumber.length()-1) == '0')) {
                return commentNumber;
            }
        }
    }

    public Map<String, String> findCommentByNumber(String commentNumber) {

        Map<String,String> commentValues = new HashMap();
        String commentNumberXpath = String.format("//td[@class='numbercolumn'][text()='%s']",commentNumber);
        String commentTextXpath = commentNumberXpath+"/following-sibling::td[@class='textcolumn']";
        String commentInactiveXpath = commentNumberXpath+"/following-sibling::td[@class='inactivecolumn']";
        String commentCategoriesXpath = commentNumberXpath+"/following-sibling::td[@class='categorycolumn']";

        commentValues.put("Number",commentNumber);
        log(commentNumber);
        while(true) {
            for (WebElement element : driver.findElements(numberColumn)) {
                log(element.getText());
                if (element.getText().equals(commentNumber)) {
                    log("equals");
                    commentValues.put("Comment Text", driver.findElement(By.xpath(commentTextXpath)).getText());
                    commentValues.put("Active", driver.findElement(By.xpath(commentInactiveXpath)).getText());
                    commentValues.put("Categories", driver.findElement(By.xpath(commentCategoriesXpath)).getText());
                    return commentValues;
                }
            }
            log("not found on this page");
            if (isNextPageExist()) {
                goToNextPage();
            } else {
                return null;
            }
        }
    }
}
