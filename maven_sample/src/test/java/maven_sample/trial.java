package maven_sample;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class trial {
	public WebDriver driver;

	public trial(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public trial() {
	}

	@BeforeClass(alwaysRun=true)
	public void setUp() {
		driver = new FirefoxDriver();
		driver.get("http://www.tripadvisor.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass(alwaysRun=true)
	public void cleanUp() {
		driver.quit();
	}

	@Test(groups="g1", priority = 1)
	public void test_1() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='searchbox']")).sendKeys("hochiminh");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='typeahead-choices']/div/span")));

		List<WebElement> list = driver.findElements(By.xpath("//div[@class='typeahead-choices']/div/span"));
		for (WebElement l : list) {
			if (l.getText().equals("Park Hyatt Saigon, Ho Chi Minh City, Vietnam")) {
				l.click();
			}
		}
		driver.findElement(By.cssSelector("#SUBMIT_HOTEL")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("#HEADING")).isDisplayed());
	}

	@Test(groups="g2", priority=2)
	public void test_2() throws InterruptedException {
		driver.findElement(By.cssSelector("#GEO_SCOPED_SEARCH_INPUT")).sendKeys("vietnam");
		driver.findElement(By.cssSelector("#GEO_SCOPED_SEARCH_INPUT")).sendKeys(Keys.ENTER);
		// Thread.sleep(3000);
		driver.findElement(By.cssSelector("#mainSearch")).sendKeys("hotel");
		driver.findElement(By.cssSelector("#SEARCH_BUTTON")).click();

		Assert.assertTrue(driver.findElement(By.cssSelector("#HEADING")).isDisplayed());

	}
	@Test(groups="g2", dependsOnMethods = "test_2")
	public void test_3() throws InterruptedException {
		driver.findElement(By.cssSelector(".nav.next.ui_button.primary.taLnk")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("#HEADING")).isDisplayed());
	}

}
