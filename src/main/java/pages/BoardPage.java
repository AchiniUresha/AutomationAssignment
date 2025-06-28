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
    private final WebDriverWait wait;
    WebDriver driver;

    public BoardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //private By addListField = By.xpath("//div[@data-cy='create-list'] ");
    private By listTitleInputField = By.xpath("//input[@placeholder='Enter list title...']");
    private By addListButton = By.xpath("//button[normalize-space()='Add list']");
    private By createdLists = By.xpath("//input[@data-cy='list-name']");

    String listXpath = "//div[@data-cy='board-detail']//div[contains(@class, 'w-list') and not(.//input[@data-cy='add-list-input'])]";

    private By tripleDotButton = By.xpath("//button[@data-cy='list-options']//*[name()='svg']");
    private By listDeleteButton = By.xpath("//div[@data-cy='delete-list']");

    public void addList(String listName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement listInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter list title...']")));
        driver.findElement(listTitleInputField).sendKeys(listName);
        driver.findElement(addListButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(createdLists));


    }



public List<String> getAllListTitles() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(createdLists));
    List<WebElement> elements = driver.findElements(createdLists);
    List<String> titles = new ArrayList<>();
    for (WebElement el : elements) {
        titles.add(el.getAttribute("value").trim());
    }
    return titles;
}

public void deleteList() {
    int initialListCount = getVisibleListCount();
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(tripleDotButton)).click();


    WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(listDeleteButton));
    deleteBtn.click();

    wait.until(ExpectedConditions.numberOfElementsToBe(createdLists, initialListCount - 1));




}

    public int getVisibleListCount() {
        List<WebElement> lists = driver.findElements(createdLists);
        return lists.size();
    }

}
