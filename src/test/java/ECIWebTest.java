import okhttp3.Cache;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ECIWebTest {

    public static WebDriver webDriver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        webDriver =  new ChromeDriver();
    }

    @Test
    public void shouldRedirectCampus() {
        WebElement element;
        String word;

        webDriver.get("https://www.escuelaing.edu.co/es/");
        webDriver.manage().window().maximize();

        element = webDriver.findElement(By.xpath("//html/body/header/div/div/div[2]/nav[2]/ul/li[2]/a/span"));
        element.click();
        element = webDriver.findElement(By.xpath("//html/body/nav/div/div/ul/li[2]"));
        word = element.getText();

        assertEquals(word,"Campus");
    }

    @Test
    public void shouldFindSystemEngineering(){
        WebElement element;
        String text;
        String[] keyWords = {"Programa","Carreras profesionales","presencial","diurna"};

        webDriver.get("https://www.escuelaing.edu.co/es/");
        webDriver.manage().window().maximize();

        element = webDriver.findElement(By.xpath("//html/body/header/div/div/div[3]/span/button[1]"));
        element.click();
        element = webDriver.findElement(By.xpath("//*[@id=\"id_search_text\"]"));
        element.sendKeys("Ingenieria de sistemas");
        element.submit();
        element = webDriver.findElement(By.xpath("//html/body/div[3]/section/h2"));
        text = element.getText();

        assertTrue(text.contains("Ingenieria de sistemas"));

        element = webDriver.findElement(By.xpath("//html/body/div[3]/section/div[2]/article[1]/header"));
        text = element.getText();

        for (int i = 0; i < keyWords.length ; i++) {
            assertTrue(text.contains(keyWords[i]));
        }
    }

    @After
    public void close() {
        webDriver.close();
    }
}
