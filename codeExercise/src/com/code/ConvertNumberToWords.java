package com.code;

/*
* This class is used to convert to positive integers or rounded to nearest integers into words and will able to convert till 10 crores
* */
public class ConvertNumberToWords {
    private static final String[] ONEDIGITS = {"zero", "one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen", "nineteen"};

    private static final String[] TENDIGITS = {"","","twenty", "thirty","forty","fifty", "sixty", "seventy", "eighty", "ninety" };

    private static final String[] BIGDIGITS = {"hundred", "thousand", "lakh", "crore"};

    public String convertNumberToWords(double n) {
        if (n <0) {
            return "not positive";
        }
        if (n >= 999999999) {
            return "long digit";
        }
        int orderAmountRoundOff = (int)Math.round(n);
        if (orderAmountRoundOff < 20) {
            return ONEDIGITS[orderAmountRoundOff];
        }
        if (orderAmountRoundOff < 100) {
            return TENDIGITS[orderAmountRoundOff/10] + ((orderAmountRoundOff % 10 > 0) ? " " + convertNumberToWordsWithoutIf(orderAmountRoundOff % 10) : "");
        }
        if (orderAmountRoundOff < 1000) {
            return ONEDIGITS[orderAmountRoundOff / 100] + " " + BIGDIGITS[0] + ((orderAmountRoundOff % 100 > 0) ? " " + convertNumberToWordsWithoutIf(orderAmountRoundOff % 100) : "");
        }
        if (orderAmountRoundOff < 100000) {
            return convertNumberToWordsWithoutIf(orderAmountRoundOff / 1000) + " " + BIGDIGITS[1] + ((orderAmountRoundOff % 1000 > 0) ? " " + convertNumberToWordsWithoutIf(orderAmountRoundOff % 1000) : "");
        }
        if (orderAmountRoundOff < 10000000) {
            return convertNumberToWordsWithoutIf(orderAmountRoundOff / 100000) + " " + BIGDIGITS[2] +  ((orderAmountRoundOff % 100000 > 0) ? " " + convertNumberToWordsWithoutIf(orderAmountRoundOff % 100000) : "");
        }
        return convertNumberToWordsWithoutIf(orderAmountRoundOff/10000000) + " " + BIGDIGITS[3] +  ((orderAmountRoundOff % 10000000 > 0) ? " " + convertNumberToWordsWithoutIf(orderAmountRoundOff % 10000000) : "");

    }

    public String convertNumberToWordsWithoutIf(double orderAmount) {
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
        boolean flag = true;
        while (orderAmountRoundOff > 0) {
            if(flag){
                //convert last 3 digits into words i.e from 1-999
                if (orderAmountRoundOff % 1000 != 0) {
                    String threeDigitString = convertBelowThousand(orderAmountRoundOff % 1000);
                    if (place > 0) {
                        threeDigitString = threeDigitString + " " + BIGDIGITS[place];
                    }
                    if (numberToWord == null) {
                        numberToWord = threeDigitString;
                    }
                    else {
                        numberToWord = threeDigitString + " " + numberToWord;
                    }

                }
                flag = false;
                orderAmountRoundOff /= 1000;
                place++;
            }
            //as in Indian nomenclature, after 3rd digit, every 2 digit has hundredth significance like thousand will come at 4th digit then lakh will come at 6th digit and crore will come at 8th digit
            // convert next 2 digit into words with placing bigWords like Thousand, Lakh, Crore according to place variable.
            if (!flag){
                if (orderAmountRoundOff % 100 != 0) {
                    String nextTwoDigits = convertBelowHundred(orderAmountRoundOff % 100);
                    if (place > 0) {
                        nextTwoDigits = nextTwoDigits + " " + BIGDIGITS[place];
                    }
                    if (numberToWord == null) {
                        numberToWord = nextTwoDigits;
                    }
                    else {
                        numberToWord = nextTwoDigits + " " + numberToWord;
                    }
                }
                orderAmountRoundOff /= 100;
                place++;
            }
        }
        return numberToWord;
    }

    // Range 0 to 999.
    private String convertBelowThousand (int number) {
        String oneDigitString = ONEDIGITS[number/100] + " hundred";
        String hundredDigitString = convertBelowHundred(number % 100);
        if (number <= 99) {
            return hundredDigitString; }
        else if (number % 100 == 0) {
            return oneDigitString;
        }
        else {
            return oneDigitString + " " + hundredDigitString;
        }
    }

    //Range 0 to 99
    private String convertBelowHundred (int number) {
        //returning one digit words.
        if (number < 20) {
            return ONEDIGITS[number];
        }
        // if greater than or equal to 20 then have to divide number by 10 and convert it into words with TEN Digits word constant and one digit word constant.
        String tenString = TENDIGITS[number/10];
        if (number % 10 == 0) {
            return tenString;
        }
        return tenString + " " + ONEDIGITS[number % 10];
    }
}
