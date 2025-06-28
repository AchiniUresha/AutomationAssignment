package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BoardPage;
import pages.HomePage;
import testdata.TestData;

import java.util.ArrayList;
import java.util.List;

public class CreateListTest extends BaseTest {
//private TestData testData = new TestData();


      @Test(dataProvider = "listNames", dataProviderClass = TestData.class)
   public void addListAndVerifyTest(String[] listNames){
          HomePage homePage = new HomePage(driver);
          BoardPage board = new BoardPage(driver);

        homePage.clickCreateNewBoard();
        homePage.enterBoardNameAndSubmit("Test Board_6");
        homePage.getBoardTitleOnTopCorner();


            for (String name : listNames){
                board.addList(name);
            }


          int expectedCountAfterCreation = listNames.length; // This will be 2 from your TestData
          int actualCountAfterCreation = board.getVisibleListCount();
          Assert.assertEquals(actualCountAfterCreation, expectedCountAfterCreation,
                  "Expected " + expectedCountAfterCreation + " lists, but found " + actualCountAfterCreation + " after creation.");
          System.out.println(actualCountAfterCreation + " lists found and verified after creation.");




          board.deleteList();
          System.out.println("One list successfully deleted.");

          int expectedCountAfterDeletion = expectedCountAfterCreation - 1; // Expected: 2 - 1 = 1 list
          int actualCountAfterDeletion = board.getVisibleListCount();
          Assert.assertEquals(actualCountAfterDeletion, expectedCountAfterDeletion,
                  "Assertion Failed: Expected " + expectedCountAfterDeletion + " list remaining, but found " + actualCountAfterDeletion + " after deletion.");
          System.out.println("Verified: " + actualCountAfterDeletion + " list remaining after deletion.");
      }


      }


