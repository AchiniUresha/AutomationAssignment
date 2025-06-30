package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BoardPage;
import pages.HomePage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CreateDeleteListTest extends BaseTest {

    // Board name to be used for creating the board
    private String boardName = "Test Board_6";

    // Array to store list names read from CSV file
    private String[] listNames;
    // Variable to store the expected number of lists after creation
    private int expectedCountAfterCreation;

    /**
     * Step 1: Create a new board
     * This test will open the home page, click the button to create a new board,
     * then enter the board name and submit the form.
     */
    @Test(priority = 1) //Step 1: Create a new board
    public void createBoard() {
        HomePage homePage = new HomePage(driver);
        homePage.clickCreateNewBoard();
        homePage.enterBoardNameAndSubmit(boardName);

    }

    /**
     * Step 2: Add multiple lists to the board using names read from a CSV file,
     * then verify that the total number of lists displayed matches the expected count.
     * @throws Exception If reading from the CSV file fails
     */
    @Test(priority = 2) //Step 2: Add two lists using CSV file and verify count
    public void addListsAndVerifyFromCSV() throws Exception {
        BoardPage board = new BoardPage(driver);
        // Read list names from CSV file into an array
        listNames = readListNamesFromCSV();

        // Loop through each list name and add it to the board
        for (String name : listNames) {
            board.addList(name);
            Thread.sleep(1000);
        }

        // Store the expected count of lists after adding
        expectedCountAfterCreation = listNames.length;
        // Get the actual count of visible lists on the board
        int actualCountAfterCreation = board.getVisibleListCount();

        // Assert that the expected and actual list counts are equal
        Assert.assertEquals(actualCountAfterCreation, expectedCountAfterCreation,
                "Expected " + expectedCountAfterCreation + " lists, but found " + actualCountAfterCreation + " after creation.");

        System.out.println(actualCountAfterCreation + " lists found and verified after creation.");
    }

    /**
     * Step 3: Delete one list from the board and verify
     * that the remaining list count decreases by one.
     */
    @Test(priority = 3) // Step 3: Delete one list and verify the remaining count
    public void deleteOneListAndVerifyRemaining() {
        BoardPage board = new BoardPage(driver);

        // Perform deletion of one list
        board.deleteList();
        System.out.println("One list successfully deleted.");

        // Calculate expected remaining list count
        int expectedCountAfterDeletion = expectedCountAfterCreation - 1; // Expected: 2 - 1 = 1 list
        // Get the actual count of visible lists after deletion
        int actualCountAfterDeletion = board.getVisibleListCount();
        // Assert the remaining count matches the expected count after deletion
        Assert.assertEquals(actualCountAfterDeletion, expectedCountAfterDeletion,
                "Assertion Failed: Expected " + expectedCountAfterDeletion + " list remaining, but found " + actualCountAfterDeletion + " after deletion.");
        System.out.println("Verified: " + actualCountAfterDeletion + " list remaining after deletion.");
    }


    /**
     * Helper method to read list names from a CSV file located in src/test/resources.
     * Each non-empty line in the file represents one list name.
     * @return Array of list names read from the CSV
     * @throws Exception If an error occurs while reading the file
     */
    private String[] readListNamesFromCSV() throws Exception { //Read list names line by line from CSV file
        List<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/listData.csv"));
        String line;

        while ((line = br.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                list.add(line.trim());
            }
        }

        br.close();
        return list.toArray(new String[0]);
    }
}






