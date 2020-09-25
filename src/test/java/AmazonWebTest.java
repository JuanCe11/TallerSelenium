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

public class AmazonWebTest {

    public static WebDriver webDriver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        webDriver =  new ChromeDriver();
    }

    /**
     * deberia funcionar el boton de ofertas del dia
     */
    @Test
    public void shouldRedirectOferts() {
        WebElement element;
        String word;
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();
        element = webDriver.findElement(By.xpath("//*[@id=\"nav-xshop\"]/a[1]"));
        element.click();
        element = webDriver.findElement(By.xpath("//*[@id=\"fst-hybrid-dynamic-h1\"]/div/h1/b"));
        word = element.getText();
        System.out.println(word);
        assertEquals(word,"Ofertas y Promociones");
    }

    /**
     * deberia buscar un articulo "disco duro ssd"
     */
    @Test
    public void shouldSearchObject() {
        WebElement element;
        String word;
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();
        element = webDriver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));

        element.sendKeys("disco duro ssd");
        element.submit();
        element = webDriver.findElement(By.xpath("//*[@id=\"search\"]/span/div/span/h1/div/div[1]/div/div/span[1]"));
        word = element.getText();
        assertEquals(word.split(" ")[0],"1");
    }

    /**
     * deberia mostrar carro vacio
     */
    @Test public void shouldEmptyCar() {
        WebElement element;
        String word;
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();
        element = webDriver.findElement(By.xpath("//*[@id=\"nav-cart\"]"));
        element.click();
        element = webDriver.findElement(By.xpath("//*[@id=\"sc-active-cart\"]/div/div[2]/div[1]/h2"));
        word = element.getText();
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(word);
        assertEquals(word.split(" ")[1],"Carrito");
    }

    @After
    public void close() {
        webDriver.close();
    }
}
