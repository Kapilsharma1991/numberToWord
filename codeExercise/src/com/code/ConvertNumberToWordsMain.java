package com.code;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
* This class is used to convert to positive integers or rounded to nearest integers into words and will able to convert till 10 crores.
* provide number in main method for e.g 2522.4 is provided to be converted to rounded number 2522 which will be converted to two thousand five hundred twenty two
* */
public class ConvertNumberToWordsMain {
    private static final String[] ONEDIGITS = {"zero", "one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen", "nineteen"};

    private static final String[] TENDIGITS = {"","","twenty", "thirty","forty","fifty", "sixty", "seventy", "eighty", "ninety" };

    private static final String[] BIGDIGITS = {"hundred","thousand", "lakh", "crore"};

    public static final String INDIAN_FORMAT = "99,99,99,999";

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

   public static String convertNumberToWords(double orderAmount, String formatType) {
        if (orderAmount <0) {
            return "not positive";
        }
        if (orderAmount >= 999999999) {
            return "long digit";
        }

        int orderAmountRoundOff = (int)Math.round(orderAmount);
        if (orderAmountRoundOff == 0) {
            return ONEDIGITS[0];
        }
        String numberToWord = null;
        int place = 0;
        List<String> numberFormatAsList = Arrays.asList(formatType.split(","));
        Collections.reverse(numberFormatAsList);
        while (orderAmountRoundOff > 0) {
           int powerOfTen =  numberFormatAsList.get(place).length();
           int orderOfTen = (int) Math.pow(10, powerOfTen);
                if (orderAmountRoundOff % orderOfTen != 0) {
                    String textForOrderOfTen = convertToText(orderAmountRoundOff % orderOfTen, powerOfTen);
                    if (place > 0) {
                        textForOrderOfTen = textForOrderOfTen + " " + BIGDIGITS[place];
                    }
                    if (numberToWord == null) {
                        numberToWord = textForOrderOfTen;
                    }
                    else {
                        numberToWord = textForOrderOfTen + " " + numberToWord;
                    }
                }
                orderAmountRoundOff /= orderOfTen;
                place++;
        }
        return numberToWord;
    }


    private static String convertToText (int number, int power) {
        if(power==3){
            return convertBelowThousand(number);
        }else {
            return convertBelowHundred(number);
        }
    }

    private static String convertBelowThousand (int number) {
        String thousandDigitString = ONEDIGITS[number/100] + " hundred";
        String hundredDigitString = convertBelowHundred(number % 100);
        if (number <= 99) {
            return hundredDigitString;
        }
        else if (number % 100 == 0) {
            return thousandDigitString;
        }
        else {
            return thousandDigitString + " " + hundredDigitString;
        }
    }

    private static String convertBelowHundred (int number) {
        if (number < 20) {
            return ONEDIGITS[number];
        }
        String tenString = TENDIGITS[number/10];
        if (number % 10 == 0) {
            return tenString;
        }
        return tenString + " " + ONEDIGITS[number % 10];
    }

    public static  void  main (String args []) {
        String s = convertNumberToWords(65432, INDIAN_FORMAT);
        System.out.println(s);
    }
}
