package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegisterationTest extends BaseClass{
	
	
	@Test(groups={"Regression", "Master" })
	public void verify_account_registeration()
	{
	try {
		
		logger.info("**** Starting TC001_AccountRegisterationTest ****");
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		
		logger.info("Clicked on my account");
		hp.clickRegister();
		logger.info("Clicked on my Register");
		AccountRegisterationPage arp= new AccountRegisterationPage(driver);
		
		logger.info("Providing customer details");
		arp.setFirstName(randomeString().toUpperCase());
		arp.setLastName(randomeString().toUpperCase());
		arp.setEmail(randomeString()+ "@gmail.com");
		arp.setTelephone(randomeNumber());
		
		String pwd= randomeAlphaNumeric();
		arp.setPassword(pwd);
		arp.setConfirmPassword(pwd);
		arp.setPrivacyPolicy();
		arp.clickContinue();
		
		logger.info("Validating the expected message");
		String confirmmsg= arp.getConfirmationMsg();
		
		Assert.assertEquals(confirmmsg, "Your Account Has Been Created!");
	}
	
	catch(Exception e)
	{
		logger.error("Test Failed");
		logger.info("Debug logs..");
		Assert.fail();
	}
	logger.info("Test finished");

}
}
