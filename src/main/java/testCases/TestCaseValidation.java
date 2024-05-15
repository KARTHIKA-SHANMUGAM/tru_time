package testCases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.OneCognizantPage;

public class TestCaseValidation extends BaseClass{
	
	@Test(priority=1)
	public void capture_user_info() throws InterruptedException {
		
		HomePage hp = new HomePage(driver);
		hp.clickUserInfo();
		try {
			System.out.println("The Username is : " + hp.getUserInfo());
			System.out.println("The EmailID of user is : " + hp.getUserEmail());
		}
		catch(Exception e) {
			
		}	
		
	}
	@Test(priority = 2)
	public void oneCognizant() throws InterruptedException {

		HomePage hp = new HomePage(driver);
		hp.clickOneCognizant();
		hp.windowHandlesOneCog(driver);
		driver.navigate().refresh();
		OneCognizantPage oc = new OneCognizantPage(driver);
		oc.clickSearchBar();
		oc.setSearchText("Tru Time");
		oc.clickTruTime();
	}

	@Test(priority = 3, dependsOnMethods = { "oneCognizant()" })
	public void switchFrame() {

		driver.switchTo().frame("appFrame");

	}
	
	@Test(priority=4, dependsOnMethods= {"oneCognizant()", "switchFrame()"})
	public void validateCurrentWeek() {
		
	    OneCognizantPage oc = new OneCognizantPage(driver);
	    List<WebElement> results = oc.getCurrentWeek();
	    ArrayList<String> happy = new ArrayList<>();
	    for (WebElement y : results) {
	        happy.add(y.getText());
	    }
	    LocalDate currentDate = LocalDate.now();
	    List<String> result2 = new ArrayList<>();
	    for (int i = 0; i <= 6; i++) {
	        LocalDate weekDay = currentDate.minusDays(currentDate.getDayOfWeek().getValue() - i);
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM", Locale.ENGLISH);
	        String formattedDate = weekDay.format(formatter);
	        result2.add(formattedDate);
	    }
	    Assert.assertEquals(happy, result2, "The lists do not match!");
	    System.out.println("The current week dates are : "+happy);

	}
	@Test(priority=5, dependsOnMethods= {"oneCognizant()", "switchFrame()"})
	public void validateCurrentDate() {
		
		OneCognizantPage oc = new OneCognizantPage(driver);
		String currDate = oc.getActiveDate();

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd MMM");
		Assert.assertEquals(dtf.format(now), currDate, "Dates are not matching");
		System.out.println("The current Date is : " + currDate);


	}
	@Test(priority=6, dependsOnMethods= {"oneCognizant()", "switchFrame()"})
	public void validateCurrentMonth() {

		OneCognizantPage oc = new OneCognizantPage(driver);
		String currMonth = oc.getCurrentMonth();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter month = DateTimeFormatter.ofPattern("MMMM");
		Assert.assertEquals(month.format(now), currMonth, "The month is not matching with the current month");
		System.out.println("The current Month is  : " + currMonth);

	}
	
	@Test(priority=7, dependsOnMethods= {"oneCognizant()", "switchFrame()"})
	public void backDatedTopup() {

		OneCognizantPage oc = new OneCognizantPage(driver);
		String topupDate = oc.getBackDate();
		LocalDateTime fifteenDaysBack = LocalDateTime.now().minusDays(15);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd MMM");
		Assert.assertEquals(dtf.format(fifteenDaysBack), topupDate, "Topup Date is not matching");
		System.out.println("The backDated topup date is : " + topupDate);

	}
	@Test(priority=8, dependsOnMethods= {"oneCognizant()", "switchFrame()"})
	public void legends() {
		
		OneCognizantPage oc = new OneCognizantPage(driver);
		String actualLegends = oc.getLegends();
		String expectedLegends = "Data Porting   10+Hrs   9-10Hrs   7-9Hrs   <7Hrs   NoTimeLog   Holiday   Leave   HalfDayLeave   Travel   ClientHoliday   ClientLocation   Approved   Rejected   Pending";
		// Replace multiple spaces with a single space for comparison
		actualLegends = actualLegends.replaceAll("\\s+", " ").trim();
		expectedLegends = expectedLegends.replaceAll("\\s+", " ").trim();
		Assert.assertEquals(actualLegends, expectedLegends, "The legends text does not match the expected result.");
		System.out.println("The Legends are : " + actualLegends);


	}

}
