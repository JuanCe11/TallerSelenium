import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedditTest {

    public static WebDriver webDriver;

    public void waitForLoad(WebDriver driver) {/* 
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                }; */
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//a[contains(@href,'f=flair')]"), 10));
    }

    @Before
    public void setUp() {

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

    @Test
    public void shouldFilterByFlair() {
        WebElement element;

        webDriver.get("https://www.reddit.com/r/selenium/");
        webDriver.manage().window().maximize();

        element = webDriver.findElement(By.xpath("//a[contains(@href,'f=flair')]"));
        String textToCompare = element.getText();
        element.click();
        waitForLoad(webDriver);
        List<WebElement> elements = webDriver.findElements(By.xpath("//a[contains(@href,'f=flair')]"));

        for (WebElement webElement : elements) {
            assertEquals(textToCompare, webElement.getText());
        }
    }

    @After
    public void close() {
        webDriver.close();
    }
    
}
