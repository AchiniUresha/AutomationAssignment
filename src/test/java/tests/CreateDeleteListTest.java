package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BoardPage;
import pages.HomePage;

import java.io.BufferedReader;
import java.io.FileReader;

public class CreateDeleteListTest extends BaseTest {

    private String boardName = "Test Board_6";
    private String[] listNames;
    private int expectedCountAfterCreation;

    @Test(priority = 1)
    public void createBoard() {
        HomePage homePage = new HomePage(driver);
        homePage.clickCreateNewBoard();
        homePage.enterBoardNameAndSubmit(boardName);
        homePage.getBoardTitleOnTopCorner();

    }

//    @Test(priority = 2, dataProvider = "listNames", dataProviderClass = TestData.class)
//    public void addListsAndVerify(String[] listNames) throws InterruptedException {
//
//        BoardPage board = new BoardPage(driver);
//        for (String name : listNames){
//                board.addList(name);
//                Thread.sleep(1000);
//        }
//
//          expectedCountAfterCreation = listNames.length; // This will be 2 from your TestData
//          int actualCountAfterCreation = board.getVisibleListCount();
//          Assert.assertEquals(actualCountAfterCreation, expectedCountAfterCreation,
//                  "Expected " + expectedCountAfterCreation + " lists, but found " + actualCountAfterCreation + " after creation.");
//          System.out.println(actualCountAfterCreation + " lists found and verified after creation.");
//    }

    @Test(priority = 2)
    public void addListsAndVerifyFromCSV() throws Exception {
        BoardPage board = new BoardPage(driver);
        listNames = readListNamesFromCSV();

        for (String name : listNames) {
            board.addList(name);
            Thread.sleep(1000);  // optional wait; better to use explicit waits in practice
        }

            expectedCountAfterCreation = listNames.length;
            int actualCountAfterCreation = board.getVisibleListCount();

            Assert.assertEquals(actualCountAfterCreation, expectedCountAfterCreation,
                    "Expected " + expectedCountAfterCreation + " lists, but found " + actualCountAfterCreation + " after creation.");

            System.out.println(actualCountAfterCreation + " lists found and verified after creation.");
        }



    @Test(priority = 3)
    public void deleteOneListAndVerifyRemaining() {
        BoardPage board = new BoardPage(driver);

          board.deleteList();
          System.out.println("One list successfully deleted.");

          int expectedCountAfterDeletion = expectedCountAfterCreation - 1; // Expected: 2 - 1 = 1 list
          int actualCountAfterDeletion = board.getVisibleListCount();
          Assert.assertEquals(actualCountAfterDeletion, expectedCountAfterDeletion,
                  "Assertion Failed: Expected " + expectedCountAfterDeletion + " list remaining, but found " + actualCountAfterDeletion + " after deletion.");
          System.out.println("Verified: " + actualCountAfterDeletion + " list remaining after deletion.");
    }

private String[] readListNamesFromCSV() throws Exception {
    BufferedReader br = new BufferedReader(new FileReader("src/test/resources/listData.csv"));
    String line = br.readLine();  // read only the first line
    br.close();

    if (line != null && !line.trim().isEmpty()) {
        return line.trim().split(",");
    } else {
        return new String[0];  // fallback empty array
    }
}

}





