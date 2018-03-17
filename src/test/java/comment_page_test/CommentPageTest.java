package comment_page_test;

import comments_page_object.CommentAddingPage;
import comments_page_object.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import url.Url;
import static log.Logger.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CommentPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    private CommentAddingPage commentAddingPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\IdeaProjects\\testselenium\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(new Url.UrlBuilder("comments.azurewebsites.net").withHttps(false).build().getUrl());
        mainPage = new MainPage(driver);
    }
    @Test
    public void commentAddingTest(){
        commentAddingPage = mainPage.startToAddComment();
        commentAddingPage.typeNumber();
        commentAddingPage.typeText("Some text");
        commentAddingPage.selectCategories("Cat0");
        commentAddingPage.save();
        mainPage = commentAddingPage.returnToMainPage();

        Map<String,String> commentValues = mainPage.findCommentByNumber(commentAddingPage.getUnqCommentNumber());
        log(commentValues.toString());
        Assert.assertEquals(commentAddingPage.getUnqCommentNumber(),commentValues.get("Number"));
        Assert.assertEquals("Some text",commentValues.get("Comment Text"));
        Assert.assertEquals("Cat0",commentValues.get("Categories"));
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
