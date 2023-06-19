package com.sinan.javademo.smscore.util;

import com.sinan.javademo.smscore.exception.InvalidMinItemPriceException;
import com.sinan.javademo.smscore.exception.InvalidMinItemQuantityException;
import com.sinan.javademo.smscore.exception.InvalidPercentageException;
import com.sinan.javademo.smscore.exception.TimeRangeConflictException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class InputValidatorTest {

    @DataProvider(name = "validPercentagesDataProvider")
    public static Object[][] getValidPercentages() {
        return new Object[][]{{50d, 0d, 100d}, {25d, 0d, 100d}, {75d, 0d, 100d}, {0d, 0d, 100d}, {100d, 0d, 100d}};
    }

    @Test(dataProvider = "validPercentagesDataProvider")
    public void testValidatePercentage_withValidValues(double value, double lowerBound, double upperBound) {
        InputValidator.validatePercentage(value, lowerBound, upperBound);
    }

    @DataProvider(name = "invalidPercentagesDataProvider")
    public static Object[][] getInvalidPercentages() {
        return new Object[][]{{-1d, 0d, 100d}, {-25d, 0d, 100d}, {101d, 0d, 100d}, {125d, 0d, 100d}};
    }

    @Test(dataProvider = "invalidPercentagesDataProvider", expectedExceptions = InvalidPercentageException.class)
    public void testValidatePercentage_withInvalidValues(double value, double lowerBound, double upperBound) {
        InputValidator.validatePercentage(value, lowerBound, upperBound);
    }

    @DataProvider(name = "validStartEndTimeDataProvider")
    public static Object[][] getValidStartEndTimes() {
        LocalDateTime now = LocalDateTime.now();
        return new Object[][]{
                {now, now.plusSeconds(1)},
                {now.minusSeconds(1), now},
                {now, now.plusDays(1)},
                {now.minusDays(1), now},
                {now, now},
                {now, null},
                {null, now},
                {null, null}
        };
    }

    @Test(dataProvider = "validStartEndTimeDataProvider")
    public void testValidateDateTimeRange_validStartEndTime(LocalDateTime startTime, LocalDateTime endTime) {
        InputValidator.validateDateTimeRange(startTime, endTime);
    }

    @DataProvider(name = "conflictedStartEndTimeDataProvider")
    public static Object[][] getConflictedStartEndTimes() {
        LocalDateTime now = LocalDateTime.now();
        return new Object[][]{
                {now, now.minusSeconds(1)},
                {now.plusSeconds(1), now},
                {now, now.minusDays(1)},
                {now.plusDays(1), now},
        };
    }

    @Test(dataProvider = "conflictedStartEndTimeDataProvider", expectedExceptions = TimeRangeConflictException.class)
    public void testValidateDateTimeRange_conflictedStartEndTime(LocalDateTime startTime, LocalDateTime endTime) {
        InputValidator.validateDateTimeRange(startTime, endTime);
    }


    @DataProvider(name = "validQuantityDataProvider")
    public static Object[][] getValidQuantities() {
        return new Object[][]{{0, 0}, {5, 5}, {11, 10}, {25, 15}};
    }

    @Test(dataProvider = "validQuantityDataProvider")
    public void testValidateMinQuantity_withValidValues(int quantity, int minimumBound) {
        InputValidator.validateMinQuantity(quantity, minimumBound);
    }

    @DataProvider(name = "invalidQuantityDataProvider")
    public static Object[][] getInvalidQuantities() {
        return new Object[][]{{0, 1}, {5, 6}, {10, 11}, {15, 25}};
    }

    @Test(dataProvider = "invalidQuantityDataProvider", expectedExceptions = InvalidMinItemQuantityException.class)
    public void testValidateMinQuantity_withInvalidValues(int quantity, int minimumBound) {
        InputValidator.validateMinQuantity(quantity, minimumBound);
    }

    @DataProvider(name = "validPriceDataProvider")
    public static Object[][] getValidPrices() {
        return new Object[][]{{5d, 5d}, {11d, 10d}, {25d, 15d}};
    }

    @Test(dataProvider = "validPriceDataProvider")
    public void testValidateMinPrice_withValidValues(double price, double minimumBound) {
        InputValidator.validateMinPrice(price, minimumBound);
    }

    @DataProvider(name = "invalidPriceDataProvider")
    public static Object[][] getInvalidPrices() {
        return new Object[][]{{0d, 1d}, {5d, 6d}, {10d, 11d}, {15d, 25d}};
    }

    @Test(dataProvider = "invalidPriceDataProvider", expectedExceptions = InvalidMinItemPriceException.class)
    public void testValidateMinPrice_withInvalidValues(double price, double minimumBound) {
        InputValidator.validateMinPrice(price, minimumBound);
    }
}