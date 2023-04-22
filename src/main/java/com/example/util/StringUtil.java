package com.example.util;

public class StringUtil {
	
	public static String stripNonAlphanumeric(String input) {
	    return input == null ? null : input.replaceAll("[^\\p{Alnum}]", "");
	}
	
	public static boolean isAlphanumeric(String str) {
	    return str.matches("^[a-zA-Z0-9]*$");
	}

}
