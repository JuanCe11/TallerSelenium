import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class GithubTest {
    public static WebDriver webDriver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        webDriver =  new ChromeDriver();
    }

    @Test
    public void shouldRedirectCampus() {
        WebElement element;
        int numberIssue = (int) Math.floor(Math.random()*400000+1);
        webDriver.get("https://github.com/");
        webDriver.manage().window().maximize();
        element = webDriver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div[2]/a[1]"));
        element.click();
        //Login
        element = webDriver.findElement(By.xpath("//*[@id=\"login_field\"]"));
        element.sendKeys("tacstest06@gmail.com");
        element = webDriver.findElement(By.xpath("//*[@id=\"password\"]"));
        element.sendKeys("Tacs12345");
        element = webDriver.findElement(By.xpath("//*[@id=\"login\"]/form/div[4]/input[9]"));
        element.click();
        //Redirect
        element = webDriver.findElement(By.xpath("//*[@id=\"repos-container\"]/ul/li/div/a"));
        element.click();
        element = webDriver.findElement(By.xpath("//*[@id=\"js-repo-pjax-container\"]/div[1]/nav/ul/li[2]/a"));
        element.click();
        //New Issue
        element = webDriver.findElement(By.xpath("//*[@id=\"js-repo-pjax-container\"]/div[2]/div/div/div[2]/div[2]/a"));
        element.click();
        element = webDriver.findElement(By.xpath("//*[@id=\"issue_title\"]"));
        element.sendKeys("Issue numero " + numberIssue);
        element = webDriver.findElement(By.xpath("//*[@id=\"issue_body\"]"));
        element.sendKeys("La prueba numero " + numberIssue + " Resulto satisfactoria");
        element = webDriver.findElement(By.xpath("//*[@id=\"new_issue\"]/div/div/div[1]/div/div[1]/div[2]/button"));
        element.submit();
        element = webDriver.findElement(By.xpath("//*[@id=\"partial-discussion-header\"]/div[1]/div/h1/span[1]"));
        Assert.assertEquals("Issue numero " + numberIssue, element.getText());
    }


    @After
    public void close() {
        webDriver.close();
    }
}
