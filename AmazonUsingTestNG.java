package simple.simple;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonUsingTestNG {
	WebDriver driver;

	@BeforeTest
	public void login() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\omkar\\Downloads\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
	}

	@Test(priority = 0)
	public void SearchProduct() {
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("Iphone 15");
		WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
		searchButton.click();
	}

	@Test(priority = 1)
	public void scrollProduct() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		// img[@alt='Apple iPhone 15 (128 GB) - Blue']
		driver.findElement(By.xpath("//img[@alt='Apple iPhone 15 (128 GB) - Blue']")).click();
		Set<String> WindowsHandle = driver.getWindowHandles();
		for (String handle : WindowsHandle) {
			driver.switchTo().window(handle);
		}

	}

	@Test(priority=2)
    public void ColourOption() throws InterruptedException {
    	Thread.sleep(2000);
        WebElement colour = driver.findElement(By.xpath("//span[@class=\"xoverlay\"]/following::img[@alt='Pink']"));
        Actions s=new Actions(driver);
        WebDriverWait wait1 = new WebDriverWait(driver, 40);
        wait1.until(ExpectedConditions.elementToBeClickable(colour));
        s.moveToElement(colour).click().perform();
        
        
        /*WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[\"a-button-inner\"]/following::input[@id='add-to-cart-button'][2]']")));
        addToCartButton.click();*/
    WebElement addToCart=  driver.findElement(By.xpath("//span[contains(text(),'Add to Cart')]"));
//      if(addToCart.isEnabled()) {
//    	  addToCart.click();
//      }else {
//    	  System.out.println("no element found");
//      }
        Actions a=new Actions(driver);
       Thread.sleep(2000);
    a.click(addToCart).build().perform();
//        
//        
//        
//        try {
//            Thread.sleep(2000); // Adjust wait time as needed
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
        List<WebElement> elements = driver.findElements(By.xpath("//span[contains(text(),'Add to Cart')]")); 
        System.out.println(elements);
        if (!elements.isEmpty())
        { elements.get(1).click(); 
        } else { System.out.println("No elements found on the page."); }
        
        

    // Other test methods can be added here

   // @AfterTest
//    public void tearDown() {
//        // Close the browser
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
}
