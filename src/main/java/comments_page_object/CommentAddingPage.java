package comments_page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static log.Logger.log;

public class CommentAddingPage {
    private WebDriver driver;
    private String unqCommentNumber;

    public String getUnqCommentNumber(){
        return this.unqCommentNumber;
    }

    public CommentAddingPage(WebDriver driver, String unqCommentNumber) {
        this.driver = driver;
        this.unqCommentNumber = unqCommentNumber;
    }
    private By commentText = By.xpath("//input[@id='Text']");
    private By commentNumber = By.xpath("//input[@id='Number']");
    private By commentCategories = By.xpath("//div[@class='categoryitem']");
    private By commentCategoryName = By.xpath("./span");
    private By commentCategoryCheckbox = By.xpath("./input[@id='Categories']");
    private By categorySelectButton = By.xpath("//div[@class='selectbuttons']/input[@value='>']");
    private By saveButton = By.xpath("//input[@value='Save']");
    private By returnButton = By.xpath("//a[text()='Return']");

    public CommentAddingPage typeText(String text){
        log("type text");
        driver.findElement(commentText).sendKeys(text);
        return this;
    }

    public CommentAddingPage typeNumber(){
        log("type number");
        driver.findElement(commentNumber).sendKeys(unqCommentNumber);
        return this;
    }

    public CommentAddingPage selectCategories(String categories){
        log("select categories");
        log("category is "+categories);
        List<String> categoriesList = Arrays.asList(categories.split(", "));
        log(categoriesList.get(0));
        List<WebElement> categoryElements = driver.findElements(commentCategories);
        for(WebElement element : categoryElements){
            log(element.findElement(commentCategoryName).getText());
            if(categoriesList.contains(element.findElement(commentCategoryName).getText())){
                log("click category");
                element.findElement(commentCategoryCheckbox).click();
            }
        }
        driver.findElement(categorySelectButton).click();
        return this;
    }

    public CommentAddingPage save(){
        log("save");
        driver.findElement(saveButton).click();
        return this;
    }

    public MainPage returnToMainPage(){
        log("return");
        driver.findElement(returnButton).click();
        return new MainPage(driver);
    }

}
