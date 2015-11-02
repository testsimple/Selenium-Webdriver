package pagetests;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import papeobject.AboutZooPage;
import papeobject.AdoptionPage;
import papeobject.AdoptionPassConfirmPage;
import papeobject.CheckAnimalAvailablePage;
import papeobject.ConfirmMsgPage;
import papeobject.ContactPage;
import papeobject.HomePage;
import papeobject.TermPage;

public class BaseTest {
	
	// Declare variable:
	protected WebDriver driver;
	protected WebElement element;
	protected Actions action;

	protected HomePage homePage;
	protected ContactPage contactPage;
	protected ConfirmMsgPage confirmPage;
	protected AdoptionPage adoptionPage;
	protected CheckAnimalAvailablePage checkAnimal;
	protected AboutZooPage aboutZooPage;
	protected AdoptionPassConfirmPage adoptionPassConfirmPage;
	protected TermPage termPage;
	
	public static String PAGE_URL;
	protected static String actualResult;
	protected static String expectedResult;

	// Constructor
	public BaseTest(WebDriver driver, 
					WebElement element, 
					Actions action, 
					HomePage homePage, 
					ContactPage contactPage,
					ConfirmMsgPage confirmPage, 
					AdoptionPage adoptionPage, 
					CheckAnimalAvailablePage checkAnimal,
					AboutZooPage aboutZooPage,
					AdoptionPassConfirmPage adoptionPassConfirmPage,
					TermPage termPage,
			
					String actualResult, 
					String expectedResult, 
					String PAGE_URL) {
		this.driver = driver;
		this.element = element;
		this.action = action;
		this.homePage = homePage;
		this.contactPage = contactPage;
		this.confirmPage = confirmPage;
		this.adoptionPage = adoptionPage;
		this.checkAnimal = checkAnimal;
		this.aboutZooPage = aboutZooPage;
		this.adoptionPassConfirmPage = adoptionPassConfirmPage;
		this.termPage = termPage;
		
		BaseTest.PAGE_URL = PAGE_URL;
		BaseTest.actualResult = actualResult;
		BaseTest.expectedResult = expectedResult;
	}

	public BaseTest() {
	}

	public void clean() {
		driver.quit();
	}

	// Initial
	public void configBrowser(String browser) {

		PAGE_URL ="http://thetestroom.com/webapp/";
		driver = getBrownserInstance(browser);
		driver.get(PAGE_URL);

		homePage = new HomePage(driver);
		confirmPage = new ConfirmMsgPage(driver);
		contactPage = new ContactPage(driver);
		adoptionPage = new AdoptionPage(driver);
		checkAnimal = new CheckAnimalAvailablePage(driver);
		aboutZooPage = new AboutZooPage(driver);
		adoptionPassConfirmPage =  new AdoptionPassConfirmPage(driver);
		termPage = new TermPage(driver);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// Config Browser
	public static WebDriver getBrownserInstance(String browserType) {
		WebDriver driver = null;

		if (browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./properties/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserType.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "./properties/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	// Assert method
	public void assertResult(String expected, String actual) {
		Assert.assertEquals(expected, actual);
	}
	
	public boolean assertExist(boolean condition){
		 Assert.assertTrue(condition);
		 return condition;
		
	}
	
	// ScreenShot
	public void takeScreenShot(String filename) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("./screenshot/" + filename + ".jpg"));
	}
	
	/*  ---  END   ---*/
}
