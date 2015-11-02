package papeobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmMsgPage extends AbstractPage {

	// PageObject Factory
	@FindBy(xpath = "//td[@class='content']/h1")
	private WebElement msgConfirm;

	// Constructor
	public ConfirmMsgPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Get confirm message
	public String getConfirmMsg() throws InterruptedException {
		Thread.sleep(3000);
		return msgConfirm.getText();
	}

}
