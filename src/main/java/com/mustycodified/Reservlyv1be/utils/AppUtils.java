package com.mustycodified.Reservlyv1be.utils;


import org.springframework.stereotype.Component;

@Component
public class AppUtils {
    public String getFormattedNumber(final String number){
        String trimmedNumber = number.trim();
        String formattedNumber = null;
        if(trimmedNumber.startsWith("0"))
            formattedNumber = "+234" + trimmedNumber.substring(1);
        else if(trimmedNumber.startsWith("234"))
            formattedNumber = "+" + number;
        else if (!number.startsWith("+")
                && Integer.parseInt(String.valueOf(number.charAt(0))) > 0) {
            formattedNumber = "+234" + number;
        }
        return  formattedNumber;
    }
}
