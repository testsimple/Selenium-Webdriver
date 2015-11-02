package papeobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdoptionPage extends AbstractPage {

	// PageObject Factory
	@FindBy(xpath = "//select[@id='start_select']/option")
	private List<WebElement> selDay;
	
	@FindBy(id = "start_select")
	private WebElement selectDay;

	@FindBy(id = "check_btn_01")
	private WebElement chkLion;

	@FindBy(id = "check_btn_02")
	private WebElement chkTurtle;
	
	// Constructor
	public AdoptionPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Comeback HomePage
	public HomePage navigateToHomePage() {
		navigateToWebApp();
		return new HomePage(driver);
	}

	// Choice Day
	public AdoptionPage choiceDay(String optionday) {
		for (WebElement sel : selDay) {
			String day = sel.getAttribute("value");
			if (day.equals(optionday)) {
				sel.click();
			}
		}
		log4j.debug("[info] Executing: |-- choiceDay " + "AdoptionPage" + " --|");
		return this;
	}
	
	// Select Day
		public AdoptionPage selectDay(String optionday) {
			Select select = new Select(selectDay);
			select.selectByVisibleText(optionday);
			
			log4j.debug("[info] Executing: |-- selectDay " + "AdoptionPage" + " --|");
			return this;
		}

	// Check Lion-animal
	public CheckAnimalAvailablePage checkLion() {
		chkLion.click();
		log4j.debug("[info] Executing: |-- checkLion " + "CheckAnimalAvailablePage" + " --|");
		return new CheckAnimalAvailablePage(driver);
	}
	
	// Check Turtle-animal
	public CheckAnimalAvailablePage checkTurtle() {
		chkTurtle.click();
		log4j.debug("[info] Executing: |-- checkTurtle " + "CheckAnimalAvailablePage" + " --|");
		return new CheckAnimalAvailablePage(driver);
	}

}
