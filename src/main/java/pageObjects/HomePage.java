package pageObjects;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='O365_MainLink_MePhoto']")
	WebElement user;

	@FindBy(id = "mectrl_currentAccount_primary")
	WebElement userInfo;

	@FindBy(id = "mectrl_currentAccount_secondary")
	WebElement userEmail;
	
	@FindBy(linkText = "OneCognizant")
	WebElement oneCognizant;

	public void clickUserInfo() {
		wait.until(ExpectedConditions.elementToBeClickable(user));
		user.click();
	}

	public String getUserInfo() {
		return userInfo.getText();
	}

	public String getUserEmail() {
		return userEmail.getText();
	}

	public void clickOneCognizant() {
		oneCognizant.click();
	}

	public void windowHandlesOneCog(WebDriver driver) {
		Set<String> Window = driver.getWindowHandles();
		List<String> Window1 = new ArrayList<String>(Window);
		// One Cognizant's Window Handle - Window1.get(1)
		driver.switchTo().window(Window1.get(1));
	}

}
