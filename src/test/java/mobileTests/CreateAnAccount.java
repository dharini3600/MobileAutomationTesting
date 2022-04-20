package mobileTests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class CreateAnAccount {
	private String reportDirectory = "reports";
	private String reportFormat = "xml";
	private String testName = "CreateAnAccount";
	protected AndroidDriver<AndroidElement> driver = null;

	DesiredCapabilities dc = new DesiredCapabilities();

	@Before
	public void setUp() throws MalformedURLException {
		dc.setCapability("reportDirectory", reportDirectory);
		dc.setCapability("reportFormat", reportFormat);
		dc.setCapability("testName", testName);
		dc.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		dc.setBrowserName(MobileBrowserType.CHROMIUM);
		driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
		driver.setLogLevel(Level.INFO);
	}

	@Test
	public void testCreateAnAccount() {
		driver.get("http://automationpractice.com/index.php");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign in")));
		driver.findElement(By.linkText("Sign in")).click();
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Create an account")));
		driver.findElement(By.id("email_create")).sendKeys("johntesting3@gmail.com");
		try {
			Thread.sleep(10000);
		} catch (Exception ignore) {
		}
		driver.findElement(By.linkText("Create an account")).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("id_gender1")));
		driver.findElement(By.id("id_gender1")).click();
		driver.findElement(By.id("customer_firstname")).sendKeys("John");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("customer_lastname")));
		driver.findElement(By.id("customer_lastname")).sendKeys("T");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("passwd")));
		driver.findElement(By.id("passwd")).sendKeys("Test@123");
		driver.context("NATIVE_APP");
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='back']")));
		driver.context("WEBVIEW_1");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("address1")));
		driver.findElement(By.id("address1")).sendKeys("Test Street");
		driver.context("WEBVIEW_1");
		driver.findElement(By.id("city")).sendKeys("Flushing");
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id("id_state")));
		driver.findElement(By.id("id_state")).click();
		try {
			Thread.sleep(10000);
		} catch (Exception ignore) {
		}
		driver.findElement(By.xpath("//*[@text='California']"));
		try {
			Thread.sleep(5000);
		} catch (Exception ignore) {
		}
		driver.findElement(By.id("postcode")).sendKeys("92782");
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id("phone_mobile")));
		driver.findElement(By.id("phone_mobile")).sendKeys("9876543210");
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id("alias")));
		driver.findElement(By.id("alias")).sendKeys("Home");
		driver.findElement(By.linkText("Register")).click();
		try {
			Thread.sleep(5000);
		} catch (Exception ignore) {
		}
		driver.findElement(By.xpath("//*[@text='John T']"));
		new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign out")));
		driver.findElement(By.linkText("Sign out")).click();
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}