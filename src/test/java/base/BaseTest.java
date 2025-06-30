package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public WebDriver driver;

    /**
     * This method runs once before all @Test methods in the test class.
     * It sets up the Chrome driver, launches the browser, and navigates to the app.
     */
    @BeforeClass
    public void setUp() throws InterruptedException{

        WebDriverManager.chromedriver().setup();    // Set up the ChromeDriver using WebDriverManager (automatically downloads the compatible version)
        driver = new ChromeDriver();    // Initialize the ChromeDriver (this opens a new Chrome browser window)
        driver.manage().window().maximize();    // Maximize the browser window to full screen

        // Open the application
        driver.get("http://localhost:3000/");
        Thread.sleep(10000);    // Wait for the app to fully load
    }

    /**
     * This method runs once after all @Test methods in the test class have executed.
     * It cleanly closes the browser and ends the session.
     */
    @AfterClass
    public void tearDown(){
        driver.quit();  // Close the browser and clean up the driver
    }
}