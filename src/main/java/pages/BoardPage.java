package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BoardPage {
    private final WebDriverWait wait;   // WebDriverWait instance for explicit waits
    WebDriver driver;

    /**
     * Constructor to initialize BoardPage with a WebDriver instance and default wait time.
     */
    public BoardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By listTitleInputField = By.xpath("//input[@placeholder='Enter list title...']");
    private By addListButton = By.xpath("//button[normalize-space()='Add list']");
    private By createdLists = By.xpath("//input[@data-cy='list-name']");
    private By tripleDotButton = By.xpath("//button[@data-cy='list-options']//*[name()='svg']");
    private By listDeleteButton = By.xpath("//div[@data-cy='delete-list']");

    /**
     * Adds a new list to the board with the given name.
     *
     * @param listName Name of the list to be added
     */
    public void addList(String listName){
        // Wait until the list input field is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement listInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter list title...']")));

        // Enter the list name and click the "Add list" button
        driver.findElement(listTitleInputField).sendKeys(listName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addListButton));
        driver.findElement(addListButton).click();

        // Wait until the list appears on the board
        wait.until(ExpectedConditions.visibilityOfElementLocated(createdLists));    // Wait until list is visible on the board
    }

    /**
     * Deletes one list from the board (assumes at least one list is present).
     */
public void deleteList() {
    // Get the number of lists currently visible
    int initialListCount = getVisibleListCount();   // Get current list count
    // Open the list options menu (3 dots)
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(tripleDotButton)).click();
    // Click the "Delete list" option
    WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(listDeleteButton));
    deleteBtn.click();
    // Wait until one list is removed (i.e., list count is reduced by 1)
    wait.until(ExpectedConditions.numberOfElementsToBe(createdLists, initialListCount - 1));    // Wait until one list is removed (list count is reduced by 1)




}
    /**
     * Returns the total number of visible lists on the board.
     *
     * @return number of list elements present
     */
    public int getVisibleListCount() {
        List<WebElement> lists = driver.findElements(createdLists);
        return lists.size();
    }

}
