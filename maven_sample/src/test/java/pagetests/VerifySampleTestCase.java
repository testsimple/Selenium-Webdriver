package pagetests;
import java.io.IOException;

//
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dataset.ProviderData;

public class VerifySampleTestCase extends BaseTest {
	
	@BeforeTest(alwaysRun=true)
	@Parameters("browser")
	public void setUp(String browser) {
		configBrowser(browser);
	}

	@AfterTest(alwaysRun=true)
	public void cleanUp() {
		clean();
	}

	@Test(groups="group1", description = "Verify Adoption-Page checkLion")
	public void test_1() throws InterruptedException, IOException {

		// 1-Goto Adoption-Page
		homePage.navigateToAdoptionPage();

		// 2-Choice Day, click 'check Lion animal' button
		adoptionPage.choiceDay("today").checkLion();

		// 3-Get Check message
		actualResult = checkAnimal.getCheckMsg();

		// 4-Verify Confirm-Page
		expectedResult = "SORRY, ANIMAL NOT AVAILABLE";
		assertResult(expectedResult, actualResult);
		
		// 5-Take screenshot
		takeScreenShot("Verify Adoption-Page checkLion");
	}
	
	@Test(groups="group1", description = "Verify Adoption-Page checkTurle", dependsOnMethods="test_1")
	public void test_2() throws InterruptedException, IOException {

		// 1-Goto Adoption-Page
		homePage.navigateToAdoptionPage();

		// 2-Choice Day, click 'check Turtle animal' button
		adoptionPage.choiceDay("today").checkTurtle();

		// 3-Get Check message
		actualResult = checkAnimal.getCheckMsg();

		// 4-Verify Confirm-Page
		expectedResult = "CONGRATULATIONS, ANIMAL AVAILABLE";
		assertResult(expectedResult, actualResult);
		
		// 5-Take screenshot
		takeScreenShot("Verify Adoption-Page checkTurtle");
	}
	
	
	@Test(groups="group2", description = "Verify Adoption-Page checkTurle, DataProvider Object", 
			dataProviderClass=ProviderData.class, dataProvider="infomation")
	public void test_3(String name, String address, String email, String postcode) throws InterruptedException, IOException {

		// 1-Goto Adoption-Page
		homePage.navigateToAdoptionPage();

		// 2-Choice Day, click 'check Turtle animal' button
		adoptionPage.choiceDay("today").checkTurtle();

		// 3-Insert information
		checkAnimal.enterName(name)
				   .enterAddress(address)
				   .enterEmail(email)
				   .enterPostCode(postcode)
				   .clickSubmitBtn();
		
		// 4-Comfirm-Page displays
		adoptionPassConfirmPage.headingInfo().isDisplayed();
		
		// 5-Verify info
		assertExist(adoptionPassConfirmPage.headingInfo().isDisplayed());
		
		// 6-Take screenshot
		takeScreenShot("Verify User Information");
	}
	
	@Test(groups="group2", description = "Verify User Information  using Excel dataset", 
			dataProviderClass=ProviderData.class, dataProvider="exceldata")
	public void test_4(String name, String address, String email, String postcode) throws InterruptedException, IOException {

		// 1-Goto Adoption-Page
		homePage.navigateToAdoptionPage();

		// 2-Choice Day, click 'check Turtle animal' button
		adoptionPage.choiceDay("today").checkTurtle();

		// 3-Insert information
		checkAnimal.enterName(name)
				   .enterAddress(address)
				   .enterEmail(email)
				   .enterPostCode(postcode)
				   .clickSubmitBtn();
		
		// 4-Comfirm-Page displays
		adoptionPassConfirmPage.headingInfo().isDisplayed();
		
		// 5-Verify info
		assertExist(adoptionPassConfirmPage.headingInfo().isDisplayed());
		
		// 6-Take screenshot
		takeScreenShot("Verify User Information");
	}
	
}
