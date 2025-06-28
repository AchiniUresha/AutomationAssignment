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

    private By createBoardTitle = By.xpath("//div[@data-cy='create-board']");
    private By boardTitleInput = By.xpath("//input[@class='new-board-input']");
    private By boardHeader = By.xpath("//input[@name='board-title']");
    private By boardItems = By.xpath("//div[@class='board']");
    private String boardByNameXPath = "//div[contains(@class,'board')]//span[text()='%s']";

    public HomePage(WebDriver driver) {
        this.driver = driver;
   }

   public void clickCreateNewBoard(){
        driver.findElement(createBoardTitle).click();
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='new-board-input']")));
   }

   public void enterBoardNameAndSubmit(String boardName){
        driver.findElement(boardTitleInput).sendKeys(boardName+ Keys.ENTER);

   }

    public String getBoardTitleOnTopCorner() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(boardHeader));
        return driver.findElement(boardHeader).getAttribute("value");
    }




}