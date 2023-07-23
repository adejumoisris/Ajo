package com.AjoPay.AjoPay.utilis;

import java.time.Year;

public class AccountUtils {



    public static  final String INSUFFICIENT_BALANCE_CODE = "006";
    public static final String INSUFFICIENT_BALANCE_MESSAGE = "Insufficient Balance";
    public static final String  TRANSFER_SUCCESSFUL_CODE ="008";
    public static final String  TRANSFER_SUCCESSFUL_MESSAGE= "Transfer Successful";

    // Auto- generated Account Number

    public static String generateAccountNumber(){
        /**
         * method that generate account number which will have current year
         * 2023 + and any random six digit
         */
        // current year

        Year curentYear = Year.now();
        // minimum six digit
        int min = 100000;
        // maximum six digit
        int max = 999999;
        // generate a random number btw min and max
        int randNumber = (int) Math.floor (Math.random() * (max -min +1) +min);
        // convert the current and randomNumber to strings , then concatenate them
        String year = String.valueOf(curentYear);
        String randomNumber = String.valueOf(randNumber);
        // to append it
        StringBuilder accountNumber = new StringBuilder();
        return accountNumber.append(year).append(randomNumber).toString();


    }

}
