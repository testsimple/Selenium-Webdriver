package papeobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {

	// PageObject Factory
	@FindBy(id = "contact_link")
	private WebElement contactLink;

	@FindBy(id = "about_link")
	private WebElement aboutZooLink;

	@FindBy(id = "adoption_link")
	private WebElement adoptionLink;

	@FindBy(id = "footer_term")
	private WebElement termLink;

	// Constructor
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Goto Contact-Page
	public ContactPage navigateToContactPage() {
		contactLink.click();
		log4j.debug("[info] Executing: |-- clickElemt " + "navigateToContactPage" + " --|");
		return new ContactPage(driver);
	}

	// Goto AboutZoo-Page
	public AboutZooPage navigateToAboutZooPage() {
		aboutZooLink.click();
		log4j.debug("[info] Executing: |-- clickElemt " + "navigateToAboutZooPage" + " --|");
		return new AboutZooPage(driver);
	}

	// Goto Adoption-Page
	public AdoptionPage navigateToAdoptionPage() {
		adoptionLink.click();
		log4j.debug("[info] Executing: |-- clickElemt " + "navigateToAdoptionPage" + " --|");
		return new AdoptionPage(driver);
	}

	// Goto Term-Page
	public TermPage navigateToTermPage() {
		termLink.click();
		log4j.debug("[info] Executing: |-- clickElemt " + "navigateToTermPage" + " --|");
		return new TermPage(driver);
	}

}
