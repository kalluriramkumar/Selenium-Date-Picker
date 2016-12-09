package demoUser;

import static org.junit.Assert.fail;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class HotelBooking {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://phptravels.net/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testHotelBooking() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("My Account")).click();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("user@phptravels.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("demouser");
    driver.findElement(By.xpath("//button[text()='Login']")).click();
    driver.findElement(By.linkText("Hotels")).click();
    
    driver.findElement(By.xpath("//input[@name='checkin']")).click();
    String checkin="20";		
    DateSelection(checkin);    
    driver.findElement(By.xpath("//input[@name='checkout']"));
    String checkout="25";		
    DateSelection(checkout);
    new Select(driver.findElement(By.id("adults"))).selectByVisibleText("3");
    new Select(driver.findElement(By.id("child"))).selectByVisibleText("1");
    driver.findElement(By.xpath("//button[@class='btn-danger btn btn-lg btn-block']")).click();
    driver.findElement(By.xpath("//*[text()='Rendezvous Hotels']")).click();
    driver.findElement(By.xpath("//*[@id='ROOMS']/form[1]/div/div[2]/div[1]/button")).click();
    driver.findElement(By.xpath("//textarea[@name='additionalnotes']")).sendKeys("Automation Test");;
    driver.findElement(By.xpath("//button[text()='CONFIRM THIS BOOKING']")).click();
    driver.findElement(By.xpath("//button[text()='Pay on Arrival']")).click();
    driver.switchTo().alert().accept();
    driver.findElement(By.linkText("John")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
 
  public void DateSelection(String day){
	  List<WebElement> days=driver.findElements(By.xpath("//div[@class='datepicker dropdown-menu']/div[@class='datepicker-days']/table/tbody/tr/td"));
      for(WebElement d:days){
    	  if(day.equals(d.getText())){
    		  d.click();
    		  return;
    	  }
      }
  }

}