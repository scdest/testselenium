package comments_page_object;

import comment_test.Comment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static log.Logger.log;

public class CommentAddingPage {
    private WebDriver driver;

    public CommentAddingPage(WebDriver driver) {
        this.driver = driver;
    }
    private By commentText = By.xpath("//input[@id='Text']");
    private By commentNumber = By.xpath("//input[@id='Number']");
    private By commentCategories = By.xpath("//div[@class='categoryitem']");
    private By categorySelectButton = By.xpath("//div[@class='selectbuttons']/input[@value='>']");
    private By saveButton = By.xpath("//input[@value='Save']");
    private By returnButton = By.xpath("//a[text()='Return']");
    
}
