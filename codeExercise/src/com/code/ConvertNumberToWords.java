package com.code;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
* This class is used to convert to positive integers or rounded to nearest integers into words and will able to convert till 10 crores
* */
public class ConvertNumberToWords {
    private static final String[] ONEDIGITS = {"zero", "one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen", "nineteen"};

    private static final String[] TENDIGITS = {"","","twenty", "thirty","forty","fifty", "sixty", "seventy", "eighty", "ninety" };

    private static final String[] BIGDIGITS = {"hundred", "thousand", "lakh", "crore"};

    public String convertNumberToWords(double orderAmount, String formatType) {
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


    private String convertToText (int number, int power) {
        if(power==3){
            return convertBelowThousand(number);
        }else {
            return convertBelowHundred(number);
        }
    }

    private String convertBelowThousand (int number) {
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

    private String convertBelowHundred (int number) {
        if (number < 20) {
            return ONEDIGITS[number];
        }
        String tenString = TENDIGITS[number/10];
        if (number % 10 == 0) {
            return tenString;
        }
        return tenString + " " + ONEDIGITS[number % 10];
    }

}
