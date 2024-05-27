package testsuite;
/**
 * Created by Jigar Patel
 */
import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    //1. userSholdLoginSuccessfullyWithValidCredentials
    public void userSholdLoginSuccessfullyWithValidCredentials() {
        //* Enter “tomsmith” username
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        //* Enter “SuperSecretPassword!” password
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        //* Click on ‘LOGIN’ button
        driver.findElement(By.className("fa-sign-in")).click();
        //* Verify the text “Secure Area”
        String expectedText = "Secure Area";
        String actualText = driver.findElement(By.xpath("//h2[text()=' Secure Area']")).getText();
        Assert.assertEquals("Text",expectedText,actualText);
            }
            
    @Test
    //2. verifyTheUsernameErrorMessage
    public void verifyTheUsernameErrorMessage() {
        //* Enter “tomsmith1” username
        driver.findElement(By.name("username")).sendKeys("tomsmith1");
        //* Enter “SuperSecretPassword!” password
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        //* Click on ‘LOGIN’ button
        driver.findElement(By.className("fa-sign-in")).click();
        //* Verify the error message “Your username is invalid!”
        String expectedText = "Your username is invalid!";
        //String actualText=driver.findElement(By.xpath("//div[text()='Your username is invalid!']")).getText().substring(0,25);
      String actualText = driver.findElement(By.xpath("//div[@class='flash error']")).getText().substring(0,25);
        Assert.assertEquals("Text Not Found" , expectedText,actualText);
    }

    @Test
    //3. verifyThePasswordErrorMessage
    public void verifyThePasswordErrorMessage() {
        //* Enter “tomsmith” username
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        //* Enter “SuperSecretPassword” password
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        //* Click on ‘LOGIN’ button
        driver.findElement(By.className("fa-sign-in")).click();
        //* Verify the error message “Your password is invalid!”
        String expectedText = "Your password is invalid!";
        String actualText = driver.findElement(By.xpath("//div[@class='flash error']")).getText().substring(0,25);
        Assert.assertEquals("Text no found", expectedText, actualText);
    }

    @After
    //close session
    public void tearDown() {
        closeBrowser();
    }
}
