package papeobject;

import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class AbstractPage {

	protected WebDriver driver;
	protected String URL = "http://thetestroom.com/webapp/";
	protected Actions action;

	// Constructor
	public AbstractPage(WebDriver driver) {
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Log4JLogger");
		this.driver = driver;
	}

	// Config Log4j
	public Logger log4j = Logger.getLogger("autoLogger");

	// navigateToHomepage
	public HomePage navigateToWebApp() {
		driver.get(URL);
		return new HomePage(driver);
	}

}
