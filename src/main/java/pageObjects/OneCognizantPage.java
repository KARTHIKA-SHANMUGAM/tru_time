package pageObjects;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OneCognizantPage extends BasePage {

	WebDriver driver;
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	public OneCognizantPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@id='oneC_searchAutoComplete']")
	WebElement searchBar;


	@FindBy(xpath="//div[@class='appsResultIcon']")
	WebElement resultText;
	
	@FindBy(css="div.dayHeadr")
	List <WebElement> currentWeek;
	
	@FindBy(xpath="//div[@ng-if='item.Date==activeDate']")
	WebElement activeDate;
	
	@FindBy(xpath="//span[@class='ui-datepicker-month']")
	WebElement currentMonth;
	
	@FindBy(css="span.topupavailablefromDate")
	WebElement topup;
	
	@FindBy(id="legendListID")
	WebElement legends;
	
	public void clickSearchBar() {
		wait.until(ExpectedConditions.elementToBeClickable(searchBar));
		searchBar.click();
	}
	
	public void setSearchText(String searchText ) {
		searchBar.sendKeys(searchText);
	}
	
	public void clickTruTime() {
		wait.until(ExpectedConditions.elementToBeClickable(resultText));
		resultText.click();
	}
	
	public List<WebElement> getCurrentWeek() {
		return currentWeek;
	}
	
	public String getActiveDate() {
		return activeDate.getText();
	}
	
	public String getCurrentMonth() {
		return currentMonth.getText();
	}
	
	public String getBackDate() {
		return topup.getText();
	}
	
	public String getLegends() {
		return legends.getText();
	}
	
}
