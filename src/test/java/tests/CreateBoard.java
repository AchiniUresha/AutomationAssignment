package tests;//package tests;


import base.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.Duration;

/**
 * Test Class: CreateBoard
 * Purpose: This test verifies that a new board can be created from the home page
 * and confirms that the board title matches the expected input.
 */

public class CreateBoard extends BaseTest {

    /**
     * Test: createBoardAndVerifySuccess
     * Steps:
     *  1. Click the "Create New Board" tile
     *  2. Enter the board name and submit
     *  3. Capture the board name displayed after creation
     *  4. Assert that the board name matches the input
     */
    @Test
    public void createBoardAndVerifySuccess() {

        // Initialize HomePage with the current browser session
        HomePage homePage = new HomePage(driver);
        // Step 1: Click on "Create New Board"
        homePage.clickCreateNewBoard();
        // Step 2: Define and enter the name for the new board
        String boardName = "Test Board_2";
        homePage.enterBoardNameAndSubmit(boardName);

        // Step 3: Get the board name displayed at the top corner after board creation
        String actualBoardName = homePage.getBoardTitleOnTopCorner();

        // Step 4: Assert that the created board's name matches the expected name
        Assert.assertEquals(actualBoardName.trim(), boardName.trim(), "Board name does not match after creation"); // trim() is used to ignore any accidental leading/trailing spaces.

        // Optional: Print to console for debugging or visibility
        System.out.println("Board name matches after creation: " + actualBoardName);

    }

}
