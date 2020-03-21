package in.co.gorest.utils;

import static org.testng.Assert.assertEquals;

public class AssertUtils {
    public static void makeAssert(String actualMessage, String expectedMessage) {
        assertEquals(actualMessage, expectedMessage);
    }

    public static void makeAssert(int actualInteger, int expectedInteger) {
        assertEquals(actualInteger, expectedInteger);
    }
}