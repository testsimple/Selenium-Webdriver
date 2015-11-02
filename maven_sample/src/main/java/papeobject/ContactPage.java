package papeobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage extends AbstractPage {

	// PageObject Factory
	@FindBy(xpath = "//input[@name='name_field']")
	private WebElement txtName;

	@FindBy(xpath = "//input[@type='radio']")
	private List<WebElement> rdioCheck;

	@FindBy(xpath = "//input[@type='checkbox']")
	private List<WebElement> chkBox;

	@FindBy(xpath = "//input[@name='address_field']")
	private WebElement txtAddress;

	@FindBy(xpath = "//input[@name='postcode_field']")
	private WebElement txtPostcode;

	@FindBy(xpath = "//input[@name='email_field']")
	private WebElement txtEmail;

	@FindBy(id = "submit_message")
	private WebElement btnSend;

	@FindBy(id = "rinfo")
	private WebElement rdioInfo;

	@FindBy(id = "cdona")
	private WebElement chkZooVolunteer;

	@FindBy(id = "slider-1")
	private WebElement sldBar;

	// Constructor
	public ContactPage(WebDriver driver) {
		super(driver);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	// ComeBack HomePage
	public HomePage navigateToHomePage() {
		driver.get(URL);
		log4j.debug("[info] Executing: |-- clickElemt " + "navigateToHomePage" + " --|");
		return new HomePage(driver);
	}

	// Enter Username
	public ContactPage enterName(String name) {
		txtName.sendKeys(name);
		log4j.debug("[info] Executing: |-- enterName " + name + " --|");
		return this;
	}

	// Enter Address
	public ContactPage enterAddress(String address) {
		txtAddress.sendKeys(address);
		log4j.debug("[info] Executing: |-- enterAddress " + address + " --|");
		return this;
	}

	// Enter PostCode
	public ContactPage enterPostcode(String postcode) {
		txtPostcode.sendKeys(postcode);
		log4j.debug("[info] Executing: |-- enterPostcode " + postcode + " --|");
		return this;
	}

	// Enter Email
	public ContactPage enterEmail(String mail) {
		txtEmail.sendKeys(mail);
		log4j.debug("[info] Executing: |-- enterEmail " + mail + " --|");
		return this;
	}

	// Move Slider-Bar
	public ContactPage moveSliderBar(int value_percent) {
		int height = sldBar.getSize().getHeight();
		int width = sldBar.getSize().getWidth();

		if (width > height) {
			action.moveToElement(sldBar, (value_percent - 5) * (width / 100), 0).click().perform();
		} else {
			action.moveToElement(sldBar, 0, (value_percent - 5) * (height / 100)).click().perform();
		}
		log4j.debug("[info] Executing: |-- moveSliderBar " + value_percent + " --|");
		return this;
	}

	// Click radio-button option
	public ContactPage radioChoice(String option) {
		int INDEX = -1;

		String[] optCheck = { "Information", "Donation", "Adoption" };
		for (int j = 0; j < optCheck.length; j++) {
			if (optCheck[j].toString().equals(option)) {
				INDEX = j;
			}
		}

		if (-1 < INDEX && INDEX < 3) {
			rdioCheck.get(INDEX).click();
		}

		log4j.debug("[info] Executing: |-- radioChoice " + option + " --|");
		return this;
	}

	// Click checkbox option
	public ContactPage checkBoxOption(String option) {
		int INDEX;

		switch (option) {
		case "Zoo Volunteer":
			INDEX = 0;
			break;
		case "Email Newsletter":
			INDEX = 1;
			break;
		default:
			INDEX = -1;
			break;
		}

		if (-1 < INDEX && INDEX < 2) {
			chkBox.get(INDEX).click();
		}

		log4j.debug("[info] Executing: |-- checkBoxOption " + option + " --|");
		return this;
	}

	// Click Send button
	public ConfirmMsgPage clickSendBtn() {
		btnSend.click();
		log4j.debug("[info] Executing: |-- clickSendBtn  --|");
		return new ConfirmMsgPage(driver);
	}
}
