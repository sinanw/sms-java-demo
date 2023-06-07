package com.sinan.javademo.apiapplication.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class APIHelperTest {


    @Test(dataProvider = "doubleDataProvider")
    public void testFormatDouble(double original, double rounded) {
        assertEquals(APIHelper.formatDouble(original), rounded);
    }

    @DataProvider(name = "doubleDataProvider")
    private static Object[][] getDoubleNumbers() {
        return new Object[][]{
                {1.123456, 1.12},
                {19.65821, 19.66},
                {135.9999, 136},
                {1951.195, 1951.2}
        };
    }
}