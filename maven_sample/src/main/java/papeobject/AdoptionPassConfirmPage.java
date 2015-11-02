package papeobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdoptionPassConfirmPage extends AbstractPage{
	
	// PageObject Factory
	@FindBy(css=".content>p")
	private WebElement msgTitle;
	
	//Constructor
	public AdoptionPassConfirmPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public WebElement headingInfo() throws InterruptedException{
		Thread.sleep(3000);
		return msgTitle;
		
	}

}
