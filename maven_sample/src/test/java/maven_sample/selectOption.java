package maven_sample;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class selectOption {
	public WebDriver driver;
	
	public selectOption(WebDriver driver) {
		this.driver = driver;
	}

	public selectOption() {
	}

	@BeforeClass(alwaysRun=true)
	public void setUp() {
		driver = new FirefoxDriver();
		driver.get("http://www.facebook.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass(alwaysRun=true)
	public void cleanUp() {
		driver.quit();
	}

	@Test(description="Select method")
	public void test_1() throws InterruptedException {
		
		// Dropdown Selection
		WebElement selDay = driver.findElement(By.id("day"));
		WebElement selMonth = driver.findElement(By.id("month"));
		WebElement selYear = driver.findElement(By.id("year"));
		
		// Select option
		Select selectDay = new Select(selDay);
		selectDay.selectByValue("9");
		
		Select selectMonth = new Select(selMonth);
		selectMonth.selectByValue("10");
		
		Select selectYear = new Select(selYear);
		selectYear.selectByValue("2000");

	}

}
