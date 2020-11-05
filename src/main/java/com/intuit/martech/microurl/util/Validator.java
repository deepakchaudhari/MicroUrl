package com.intuit.martech.microurl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.intuit.martech.microurl.util.Constants;


public class Validator {
    public static final Validator INSTANCE = new Validator();
   
    private Validator() {
    }

    public boolean validateURL(String url) {
        Matcher m = Constants.URL_PATTERN.matcher(url);
        return m.matches();
    }
    
    public static boolean isStingNullOrEmpty(String inputString) {
    	 if(inputString !=null && !inputString.isEmpty())
    		 return false;
    	 return true;
    }
}
