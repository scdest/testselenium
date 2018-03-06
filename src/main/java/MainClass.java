import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainClass {
    static WebDriver driver;
    static WebDriverWait wait;


    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Admin\\IdeaProjects\\testselenium\\drivers\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\IdeaProjects\\testselenium\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver,5);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        driver.get("http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html");
        String mainWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='Oracle Binary Code License Agreement for Java SE'][1]")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        for(String windowHandle:driver.getWindowHandles()){
            driver.switchTo().window(windowHandle);
        }
        driver.findElement(By.xpath("//a[@href='/technetwork/java/javamagazine/index.html']")).click();
        driver.switchTo().window(mainWindow);
        driver.findElement(By.xpath("//span[contains(text(),'Overview')]")).click();





        //driver.quit();


    }

    public static void selectOptionFromRegionsBlock(String option){
        String listXpath = "//span[@data-qaid='region_selector_block']";
        String optionXpath = String.format("//div[@data-qaid='region_selector_dd']//a[text()='%s'][1]",option);
        driver.findElement(By.xpath(listXpath)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXpath)));
        driver.findElement(By.xpath(optionXpath)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(listXpath)));
    }

}
