package com.example.cars4sale.Tokenizer;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Token {

    static final String[] keyword = {"name", "location", "price", "year"};
    static final char[] comparison = {'=', '<', '>'};
    static final String nameRegex = "name*";
    static final String locationRegex = "location*";
    static final String priceRegex = "price*";
    static final String yearRegex = "year*";
    private String _token = "";
    private Type _type = Type.UNKNOWN;

    public Token(String token, Type type) {
        _token = token;
        _type = type;
    }

    public static boolean regexMatching(String regex, String input) {
        return Pattern.matches(regex, input);
    }

    public static String sortString(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public static boolean nameContaining(String input) {
        String keyword = "name";
        keyword = sortString(keyword);
        input = sortString(input);
        return keyword.contains(input) || input.contains(keyword);
    }

    public static boolean locationContaining(String input) {
        String keyword = "location";
        keyword = sortString(keyword);
        input = sortString(input);
        return keyword.contains(input) || input.contains(keyword);
    }

    public static boolean priceContaining(String input) {
        String keyword = "price";
        keyword = sortString(keyword);
        input = sortString(input);
        return keyword.contains(input) || input.contains(keyword);
    }

    public static boolean yearContaining(String input) {
        String keyword = "year";
        keyword = sortString(keyword);
        input = sortString(input);
        return keyword.contains(input) || input.contains(keyword);
    }

    public String token() {
        return _token;
    }

    public Type type() {
        return _type;
    }

    public enum Type {UNKNOWN, INT, KEYWORD, COMPARISON, SEMICOLON, ELSE}

}