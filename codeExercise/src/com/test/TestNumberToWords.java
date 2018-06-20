package com.test;

import com.code.ConvertNumberToWords;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
* this class is used to test ConvertNumberToWords class with different test cases
* */

public class TestNumberToWords {
    @Test
    public static void main (String args[]) {
        ConvertNumberToWords convertNumberToWords = new ConvertNumberToWords();
        assertEquals("2523 should converted to two thousand five hundred twenty three", "two thousand five hundred twenty three", convertNumberToWords.convertNumberToText(2523));
        assertEquals("2522.4 should converted to two thousand five hundred twenty two", "two thousand five hundred twenty two", convertNumberToWords.convertNumberToText(2522.4));
        assertEquals("2522.7 should converted to two thousand five hundred twenty three", "two thousand five hundred twenty three", convertNumberToWords.convertNumberToText(2522.7));
        assertEquals("-123 should return not positive", "not positive", convertNumberToWords.convertNumberToText(-123));
        assertEquals("999999999 should not be converted", "long digit", convertNumberToWords.convertNumberToText(999999999));
        assertEquals("0.01 should converted to zero", "zero", convertNumberToWords.convertNumberToText(0.01));
        System.out.println("test cases successful");
    }
}
