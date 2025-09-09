package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * Data is valid- Login success- test passed- Logout
 * Data is valid- Login failed- test failed
 * Data is invalid- Login success- test failed- Logout
 * Data is invalid- Login failed- test passed-
 */

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups={"DataDriven", "Master"}) //getting dataprovider from different class
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		logger.info("****Starting the TC003 test*******");
		try
		{
		//HomePage
				HomePage hp= new HomePage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				
				LoginPage lp= new LoginPage(driver);
				lp.setEmail(email);
				lp.setPassword(pwd);
				lp.clickLogin();
				
				//My ACcount page
				MyAccountPage myacc= new MyAccountPage(driver);
				boolean target=myacc.isMyAccountPageExists();
				
				if(exp.equalsIgnoreCase("valid"))
				{
					if(target==true)
					{
						Assert.assertTrue(true);
						myacc.logout();
					}
					else
					{
						Assert.assertTrue(false);
					}
				}
				
				if(exp.equalsIgnoreCase("invalid"))
				{
					if(target==true)
					{
						
						myacc.logout();
						Assert.assertTrue(false);
					}
					else
					{
						Assert.assertTrue(true);
					}
				}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
				logger.info("****Finished the TC003 test*******");
				
				
				
	}
	
	

}
