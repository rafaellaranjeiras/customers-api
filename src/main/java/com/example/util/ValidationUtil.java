package com.example.util;

public class ValidationUtil {
	
	public static boolean isCPFValid(String cpf) {
	    cpf = StringUtil.stripNonAlphanumeric(cpf);
	    if (cpf.length() != 11)
	        return false;	    
	    int sum = 0;
	    for (int i = 0; i < 9; i++) 
	        sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);	    
	    int firstDigit = 11 - (sum % 11);
	    if (firstDigit >= 10) 
	        firstDigit = 0;	    
	    sum = 0;
	    for (int i = 0; i < 9; i++) 
	        sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);	    
	    sum += firstDigit * 2;
	    int secondDigit = 11 - (sum % 11);
	    if (secondDigit >= 10) 
	        secondDigit = 0;	    
	    return Character.getNumericValue(cpf.charAt(9)) == firstDigit && Character.getNumericValue(cpf.charAt(10)) == secondDigit;
	}

}
