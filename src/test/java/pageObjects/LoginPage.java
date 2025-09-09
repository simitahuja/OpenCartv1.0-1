package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath= "//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath= "//input[@id='input-password']")
	WebElement txt_pwd;
	
	@FindBy(xpath= "//input[@value='Login']")
	WebElement login_btn;
	
	public void setEmail(String email)
	{
		txt_email.sendKeys("simitahuja1@gmail.com");
	}
	
	public void setPassword(String password)
	{
		txt_pwd.sendKeys("Dhwanic2s2");
	}
	
	public void clickLogin()
	{
		login_btn.click();
	}

}
