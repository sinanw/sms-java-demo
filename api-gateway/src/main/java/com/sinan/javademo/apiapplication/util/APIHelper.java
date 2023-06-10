package com.sinan.javademo.apiapplication.util;

import org.apache.commons.math3.util.Precision;

public final class APIHelper {
    public static double formatDouble(double num) {
        return Precision.round(num, 2);
    }
}
