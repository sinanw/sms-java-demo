package com.sinan.javademo.apiapplication.util;

import org.apache.commons.math3.util.Precision;

/**
 * A helper class to provide assistant functionalities.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
public final class APIHelper {
    
    /**
     * Rounds double number to a specific precision.
     *
     * @param num the number to be rounded.
     * @return the rounded number.
     */
    public static double formatDouble(double num) {
        return Precision.round(num, 2);
    }
}
