package comments_page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        try{
            if(driver.findElement(nextPageButton).isDisplayed()){
                driver.findElement(nextPageButton).click();
                return new MainPage(driver);
            }
        }catch (Exception e){
            log("last page");
        }
        return this;
    }

    public CommentAddingPage startToAddComment(){
        driver.findElement(addCommentButton).click();
        return new CommentAddingPage(driver);
    }
}
