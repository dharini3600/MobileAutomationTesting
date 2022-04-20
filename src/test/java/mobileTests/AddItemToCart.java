package mobileTests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.junit.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class AddItemToCart {
	private String reportDirectory = "reports";
	private String reportFormat = "xml";
	private String testName = "AddItemToCart";
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
	public void testAddItemToCart() {
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.id("email")).sendKeys("johntesting3@gmail.com");
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id("passwd")));
		driver.findElement(By.id("passwd")).sendKeys("Test@123" + Keys.ENTER);
		try {
			Thread.sleep(10000);
		} catch (Exception ignore) {
		}
		driver.findElement(By.linkText("T-shirts")).click();
		try {
			Thread.sleep(10000);
		} catch (Exception ignore) {
		}
		driver.findElement(By.linkText("Add to cart")).click();
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Proceed to checkout")));
		driver.findElement(By.linkText("Proceed to checkout")).click();
		try {
			Thread.sleep(5000);
		} catch (Exception ignore) {
		}
		String text1 = driver.findElement(By.xpath("//*[@class='cart_quantity_input form-control grey']")).getText();
		Assert.assertEquals("1", text1);
		new WebDriverWait(driver, 120)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class=\"icon-trash\"]")));
		driver.findElement(By.xpath("//i[@class=\"icon-trash\"]")).click();
		new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign out")));
		driver.findElement(By.linkText("Sign out")).click();
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}