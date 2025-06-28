//package testdata;
//
//import org.testng.annotations.DataProvider;
//
//public class TestData {
//
//    @DataProvider(name = "listNames")
//    public static String[] getListNames() {
//        return new String[] { "List A", "List B" };
//
//    }
//}

package testdata;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "listNames")
    public static Object[][] getListNames() {
        return new Object[][] {
                { new String[] { "List A", "List B" } }
        };
    }
}


