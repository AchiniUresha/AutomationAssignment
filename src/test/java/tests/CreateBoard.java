package tests;//package tests;


import base.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.Duration;

public class CreateBoard extends BaseTest {

    @Test
    public void createBoardAndVerifySuccess() {
        HomePage homePage = new HomePage(driver);
        homePage.clickCreateNewBoard();

        String boardName = "Test Board_2";
        homePage.enterBoardNameAndSubmit(boardName);


        String actualBoardName = homePage.getBoardTitleOnTopCorner();
        Assert.assertEquals(actualBoardName.trim(), boardName.trim(), "Board name does not match after creation");


        System.out.println("Board name matches after creation: " + actualBoardName);

    }

}
