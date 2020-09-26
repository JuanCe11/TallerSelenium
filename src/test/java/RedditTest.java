import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class RedditTest {

    public static WebDriver webDriver;

    @Before
    public void setUp(){
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        webDriver = new ChromeDriver(options);
    }

    @Test
    public void shouldFindSubreddit() {
        WebElement element;

        webDriver.get("https://www.reddit.com/");
        webDriver.manage().window().maximize();

        webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        element = webDriver.findElement(By.xpath("//input[@id='header-search-bar']"));
        element.click();
        element.sendKeys("selenium");
        element.submit();

        webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        element = webDriver.findElement(By.xpath("//a[@href='/r/selenium/']"));
        element.click();
        assertEquals("https://www.reddit.com/r/selenium/", webDriver.getCurrentUrl());
        
    }

    @After
    public void close() {
        webDriver.close();
    }
    
}
