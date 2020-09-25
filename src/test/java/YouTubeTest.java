import okhttp3.Cache;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class YouTubeTest {

    public static WebDriver webDriver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        webDriver =  new ChromeDriver();
    }

    @Test
    public void shouldSearchVideo(){
        WebElement element;
        String word;

        webDriver.get("https://www.youtube.com/");
        webDriver.manage().window().maximize();

        element = webDriver.findElement(By.xpath("//html/body/ytd-app/div/div/ytd-masthead/div[3]/div[2]/ytd-searchbox/form/div/div[1]/input"));
        element.clear();
        element.sendKeys("what is gradle");
        element.submit();
        element = webDriver.findElement(By.xpath("//html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]/div[1]/div/div[1]/div/h3/a/yt-formatted-string"));
        word = element.getText();
        assertEquals("Gradle Beginner Tutorial 1 | What are Build Tools | What is Gradle | Step by Step", word);
    }

    @Test
    public void shouldGoToVideo(){
        WebElement element;
        String word;

        webDriver.get("https://www.youtube.com/results?search_query=what+is+gradle");
        webDriver.manage().window().maximize();

        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        element = webDriver.findElement(By.xpath("//html/body/ytd-app/div/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]/div[1]/div/div[1]/div/h3/a"));
        element.click();
        assertEquals(webDriver.getCurrentUrl(),"https://www.youtube.com/watch?v=Nabjqv8KDgc");
    }

    @Test
    public void shouldGoMainPage(){
        WebElement element;
        String word;

        webDriver.get("https://www.youtube.com/watch?v=Nabjqv8KDgc");
        webDriver.manage().window().maximize();

        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        element = webDriver.findElement(By.xpath("//html/body/ytd-app/div/div/ytd-masthead/div[3]/div[1]/ytd-topbar-logo-renderer/a/div[1]"));
        element.click();
        assertEquals(webDriver.getCurrentUrl(),"https://www.youtube.com/");
    }

    @After
    public void close(){
        webDriver.close();
    }
}
