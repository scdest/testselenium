import comment_test.CommentTable;
import comment_test.Comment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import url.Url;

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
        CommentTable table = new CommentTable(driver.findElement(By.xpath("//table[@class='webgrid']")),driver);
        String commentNumber = getUnqNumberForComment(table);
        driver.findElement(By.xpath("//input[@value='New...']")).click();
        driver.findElement(By.xpath("//input[@id='Text']")).sendKeys("SomeText");
        driver.findElement(By.xpath("//input[@id='Number']")).sendKeys(commentNumber);
        String category = "Cat0";
        driver.findElement(By.xpath(String.format("//span[text()='%s']/preceding-sibling::input[@id='Categories']",category))).click();
        driver.findElement(By.xpath("//div[@class='selectbuttons']/input[@value='>']")).click();
        driver.findElement(By.xpath("//input[@value='Save & Return']")).click();



    }

    public static String getUnqNumberForComment(CommentTable table){
        Random r = new Random();
        while(true){
            String commentNumber = String.valueOf(r.nextInt(999));
            if(!isCommentPresent(commentNumber,table)){
                return commentNumber;
            }
        }
    }

    public static boolean isCommentPresent(String commentNumber, CommentTable table){
        return table.getRowsValuesByColumnForAllPages("Number").contains(commentNumber);
    }

    public static Comment findCommentByNumber(String number,CommentTable table){
        if(isCommentPresent(number,table)) {
            return new Comment(number,
                    driver.findElement(By.xpath("//td[text()='1']/following-sibling::td[@class='textcolumn']")).getText(),
                    driver.findElement(By.xpath("//td[text()='1']/following-sibling::td[@class='inactivecolumn']")).getText(),
                    Arrays.asList(driver.findElement(By.xpath("//td[text()='1']/following-sibling::td[@class='categorycolumn']")).getText().split(", ")));
        }
        return null;
    }
}
