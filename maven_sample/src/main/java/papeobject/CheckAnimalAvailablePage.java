package papeobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckAnimalAvailablePage extends AbstractPage {

	// PageObject Factory
	@FindBy(xpath = "//p[@id='result']")
	private WebElement chkMsg;

	@FindBy(name = "name_field")
	private WebElement txtName;

	@FindBy(name = "address_field")
	private WebElement txtAddress;

	@FindBy(name = "postcode_field")
	private WebElement txtPstcode;

	@FindBy(name = "email_field")
	private WebElement txtEmail;

	@FindBy(id = "submit_adoption")
	private WebElement btnSubmit;
	
	// Constructor
	public CheckAnimalAvailablePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Get Message
	public String getCheckMsg() throws InterruptedException {
		Thread.sleep(3000);
		return chkMsg.getText();
	}
	
	// Methods
	public CheckAnimalAvailablePage enterName(String name){
		txtName.sendKeys(name);
		return this;
	}
	
	public CheckAnimalAvailablePage enterAddress(String address){
		txtAddress.sendKeys(address);
		return this;
	}
	
	public CheckAnimalAvailablePage enterPostCode(String postcode){
		txtPstcode.sendKeys(postcode);
		return this;
	}
	
	public CheckAnimalAvailablePage enterEmail(String email){
		txtEmail.sendKeys(email);
		return this;
	}
	
	public AdoptionPassConfirmPage clickSubmitBtn(){
		btnSubmit.click();
		return new AdoptionPassConfirmPage(driver);
	}

}
