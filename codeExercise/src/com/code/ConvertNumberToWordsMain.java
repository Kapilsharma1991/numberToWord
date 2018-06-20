package com.code;

/*
* This class is used to convert to positive integers or rounded to nearest integers into words and will able to convert till 10 crores.
* provide number in main method for e.g 2522.4 is provided to be converted to rounded number 2522 which will be converted to two thousand five hundred twenty two
* */
public class ConvertNumberToWordsMain {
    private static final String[] ONEDIGITS = {"zero", "one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen", "nineteen"};

    private static final String[] TENDIGITS = {"","","twenty", "thirty","forty","fifty", "sixty", "seventy", "eighty", "ninety" };

    private static final String[] BIGDIGITS = {"hundred", "thousand", "lakh", "crore"};

    public static String convertNumberToText (double orderAmount) {
        if (orderAmount <0) {
            return "not positive";
        }
        if (orderAmount >= 999999999) {
            return "long digit";
        }

        int orderAmountRoundOff = (int)Math.round(orderAmount);
        if (orderAmountRoundOff < 20) {
            return ONEDIGITS[orderAmountRoundOff];
        }
        if (orderAmountRoundOff < 100) {
            return TENDIGITS[orderAmountRoundOff/10] + ((orderAmountRoundOff % 10 > 0) ? " " + convertNumberToText(orderAmountRoundOff % 10) : "");
        }
        if (orderAmountRoundOff < 1000) {
            return ONEDIGITS[orderAmountRoundOff / 100] + " " + BIGDIGITS[0] + ((orderAmountRoundOff % 100 > 0) ? " " + convertNumberToText(orderAmountRoundOff % 100) : "");
        }
        if (orderAmountRoundOff < 100000) {
            return convertNumberToText(orderAmountRoundOff / 1000) + " " + BIGDIGITS[1] + ((orderAmountRoundOff % 1000 > 0) ? " " + convertNumberToText(orderAmountRoundOff % 1000) : "");
        }
        if (orderAmountRoundOff < 10000000) {
            return convertNumberToText(orderAmountRoundOff / 100000) + " " + BIGDIGITS[2] +  ((orderAmountRoundOff % 100000 > 0) ? " " + convertNumberToText(orderAmountRoundOff % 100000) : "");
        }
        return convertNumberToText(orderAmountRoundOff/10000000) + " " + BIGDIGITS[3] +  ((orderAmountRoundOff % 10000000 > 0) ? " " + convertNumberToText(orderAmountRoundOff % 10000000) : "");

    }

    public static  void  main (String args []) {
        String s = convertNumberToText(2522.4);
        System.out.println(s);
    }
}