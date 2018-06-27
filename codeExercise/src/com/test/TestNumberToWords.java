package com.test;

import com.code.ConvertNumberToWords;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
* this class is used to test ConvertNumberToWords class with different test cases
* */

public class TestNumberToWords {
    public static final String INDIAN_FORMAT = "99,99,99,999";
    @Test
    public static void main (String args[]) {

        ConvertNumberToWords convertNumberToWords = new ConvertNumberToWords();
        assertEquals("2523 should converted to two thousand five hundred twenty three", "two thousand five hundred twenty three", convertNumberToWords.convertNumberToWords(2523,INDIAN_FORMAT));
        assertEquals("2522.4 should converted to two thousand five hundred twenty two", "two thousand five hundred twenty two", convertNumberToWords.convertNumberToWords(2522.4, INDIAN_FORMAT));
        assertEquals("2522.7 should converted to two thousand five hundred twenty three", "two thousand five hundred twenty three", convertNumberToWords.convertNumberToWords(2522.7, INDIAN_FORMAT));
        assertEquals("-123 should return not positive", "not positive", convertNumberToWords.convertNumberToWords(-123, INDIAN_FORMAT));
        assertEquals("999999999 should not be converted", "long digit", convertNumberToWords.convertNumberToWords(999999999, INDIAN_FORMAT));
        assertEquals("0.01 should converted to zero", "zero", convertNumberToWords.convertNumberToWords(0.01, INDIAN_FORMAT));
        System.out.println("test cases successful");
    }
}
