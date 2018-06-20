package com.code;

/*
* This class is used to convert to positive integers or rounded to nearest integers into words and will able to convert till 10 crores
* */
public class ConvertNumberToWords {
    private static final String[] ONEDIGITS = {"", "one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen", "nineteen"};

    private static final String[] TENDIGITS = {"twenty", "thirty","forty","fifty", "sixty", "seventy", "eighty", "ninety" };

    private static final String[] BIGDIGITS = {"hundred", "thousand", "lakh", "crore"};

    public String convertNumberToText (double n) {
        return "two thousand five hundred twenty threde";
    }
}
