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
public class LinkedinTest {
    public static WebDriver webDriver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        webDriver =  new ChromeDriver();
    }

    /**
     * deberia estar el boton de buscar un empleo
     */
    @Test
    public void shouldRedirectOferts() {
        WebElement element;
        String word;
        webDriver.get("https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiO-rfwloXsAhXoguAKHexjAnQQFjAAegQIAhAB&url=https%3A%2F%2Fco.linkedin.com%2F&usg=AOvVaw31DDNwat59yYx5XgAkUAGg");
        webDriver.manage().window().maximize();
        element = webDriver.findElement(By.xpath("/html/body/main/section[1]/div[2]/ul/li[1]/a"));
        word = element.getText();
        System.out.println(word);
        assertEquals(word,"Buscar un empleo");
    }

    


    @After
    public void close() {
        webDriver.close();
    }
}

