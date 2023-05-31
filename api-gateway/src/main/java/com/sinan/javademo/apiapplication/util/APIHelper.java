package com.sinan.javademo.apiapplication.util;

public final class APIHelper {
    public static double formatDouble(double num) {
        return Math.round(num * 100) / 100.0;
    }
}
