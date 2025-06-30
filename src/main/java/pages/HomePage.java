package pages;//package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    WebDriver driver;

    // Locators
    private By createBoardTitle = By.xpath("//div[@data-cy='create-board']");
    private By boardTitleInput = By.xpath("//input[@class='new-board-input']");
    private By boardHeader = By.xpath("//input[@name='board-title']");
    private By boardItems = By.xpath("//div[@class='board']");
    private String boardByNameXPath = "//div[contains(@class,'board')]//span[text()='%s']";

    /**
     * Constructor to initialize the HomePage with a WebDriver.
     *
     * @param driver the WebDriver instance passed from the test
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
   }

    /**
     * Clicks on the "Create new board" tile.
     * Waits until the board title input becomes visible.
     */
   public void clickCreateNewBoard(){
        driver.findElement(createBoardTitle).click();
       // Wait up to 20 seconds for the board title input field to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='new-board-input']")));
   }

    /**
     * Enters the board name into the input and presses Enter to submit it.
     *
     * @param boardName the name of the board to be created
     */
   public void enterBoardNameAndSubmit(String boardName){
        driver.findElement(boardTitleInput).sendKeys(boardName+ Keys.ENTER);

   }

    /**
     * Returns the title of the currently opened board from the top input box.
     *
     * @return the board title as shown in the corner input field
     */
    public String getBoardTitleOnTopCorner() {
        // Wait until the board title input becomes visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(boardHeader));
        // Return the current value of the input field (board title)
        return driver.findElement(boardHeader).getAttribute("value");
    }

}