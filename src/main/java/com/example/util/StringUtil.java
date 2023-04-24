package com.example.util;

public class StringUtil {
	
	public static String stripNonAlphanumeric(String input) {
	    return input == null ? null : input.replaceAll("[^\\p{Alnum}]", "");
	}
	
	public static boolean isAlphanumeric(String str) {
	    return str.matches("^[a-zA-Z0-9]*$");
	}
	
	public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        StringBuilder camelCase = new StringBuilder();
        char[] inputArr = input.toCharArray();
        boolean toUpperCase = false;
        for (int i=0; i < input.toCharArray().length; i++) {
        	char c = inputArr[i];
        	if(i>0 && c == Character.toUpperCase(c)) {
        		camelCase.append(c);
        		continue;				
        	}
            if (c == ' ' || c == '_' || c == '-') {
                toUpperCase = true;
            } else {
                if (toUpperCase) {
                    camelCase.append(Character.toUpperCase(c));
                    toUpperCase = false;
                } else {
                    camelCase.append(Character.toLowerCase(c));
                }
            }
        }
        return camelCase.toString();
	}

}
