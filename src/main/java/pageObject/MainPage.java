package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private By signInButton = By.xpath("//a[text()='Sign in']");
    private By signUpButton = By.xpath("//a[text()='Sign up']");
    private By userNameField = By.xpath(".//*[@id='user[login]']");
    private By userEmailField = By.xpath(".//*[@id='user[email]']");
    private By userPasswordField = By.xpath(".//*[@id='user[password]']");
    private By signUpFormButton = By.xpath("//button[text()='Sign up for GitHub']");

    public LoginPage clickSingIn(){
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    public SignUpPage clickSingUp(){
        driver.findElement(signUpButton).click();
        return new SignUpPage(driver);
    }

    public SignUpPage clickSignUpFormButton(){
        driver.findElement(signUpFormButton).click();
        return new SignUpPage(driver);
    }

    public MainPage typeUserName(String username){
        driver.findElement(userNameField).sendKeys(username);
        return this;
    }

    public MainPage typePassword(String password){
        driver.findElement(userPasswordField).sendKeys(password);
        return this;
    }

    public MainPage typeEmail(String email){
        driver.findElement(userEmailField).sendKeys(email);
        return this;
    }

    public SignUpPage register(String username, String email, String password){
        this.typeUserName(username);
        this.typeEmail(email);
        this.typePassword(password);
        this.clickSignUpFormButton();
        return new SignUpPage(driver);
    }

}
