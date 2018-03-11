import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.SignUpPage;

import java.util.concurrent.TimeUnit;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\IdeaProjects\\testselenium\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://github.com");
        mainPage = new MainPage(driver);
    }
    @Test
    public void signInTest(){
        LoginPage loginPage = mainPage.clickSingIn();
        String heading = loginPage.getHeadingText();
        Assert.assertEquals("Sign in to GitHub",heading);
    }

    @Test
    public void registerFailTest(){
        SignUpPage signUpPage = mainPage.register("username","email@email.com","password");
        Assert.assertEquals("There were problems creating your account.",signUpPage.getMainError());
    }
    @After
    public void tearDown(){
        driver.quit();
    }

}
