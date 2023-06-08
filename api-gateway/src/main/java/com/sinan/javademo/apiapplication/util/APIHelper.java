package com.sinan.javademo.apiapplication.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class APIHelper {
    public static double formatDouble(double num) {
        return new BigDecimal(num).setScale(2, RoundingMode.HALF_UP).doubleValue();
//        return Math.round(num * 100) / 100.0;
    }
}
