package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    private By heading = By.xpath("//div[@class='setup-wrapper']//h1");
    private By userNameField = By.xpath(".//*[@id='user_login']");
    private By emailField = By.xpath(".//*[@id='user_email']");
    private By passwordField = By.xpath(".//*[@id='user_password']");
    private By mainError = By.xpath(".//*[@id='user_login']/ancestor::dl/preceding-sibling::div[contains(@class,'flash-error')]");
    private By userNameError = By.xpath("//input[@id='user_login']/ancestor::dd/following-sibling::dd");
    private By emailError = By.xpath("//input[@id='user_email']/ancestor::dd/following-sibling::dd");
    private By passwordError = By.xpath("//input[@id='user_password']/ancestor::dd/following-sibling::dd");
    private By signUpButton = By.xpath(".//*[@id='signup_button']");

    public SignUpPage typeUserName(String username){
        driver.findElement(userNameField).sendKeys(username);
        return this;
    }

    public SignUpPage typePassword(String password){
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage typeEmail(String email){
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage register(String username, String email, String password){
        this.typeUserName(username);
        this.typeEmail(email);
        this.typePassword(password);
        driver.findElement(signUpButton).click();
        return new SignUpPage(driver);
    }

    public String getHeadingText(){
        return driver.findElement(heading).getText();
    }

    public String getMainError(){
        return driver.findElement(mainError).getText();
    }

    public String getUserNameError(){
        return driver.findElement(userNameError).getText();
    }

    public String getEmailError(){
        return driver.findElement(emailError).getText();
    }

    public String getPasswordError(){
        return driver.findElement(passwordError).getText();
    }
}
