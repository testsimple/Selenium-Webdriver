package papeobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AboutZooPage extends AbstractPage {

	// Constructor
	public AboutZooPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Comeback HomePage
	public HomePage navigateToHomePage() {
		navigateToWebApp();
		return new HomePage(driver);
	}
}
