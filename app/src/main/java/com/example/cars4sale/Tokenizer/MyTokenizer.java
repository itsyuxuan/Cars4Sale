package com.example.cars4sale.Tokenizer;

import java.util.Arrays;

public class MyTokenizer extends Tokenizer {

    private String _buffer;        //save text
    private Token currentToken;    //save token extracted from next()

    /**
     * Tokenizer class constructor
     * The constructor extracts the first token and save it to currentToken
     */
    public MyTokenizer(String text) {
        _buffer = text;        // save input text (string)
        next();        // extracts the first token.
    }

    /**
     * returned the current token extracted by {@code next()}
     *
     * @return type: Token
     */
    public Token current() {
        return currentToken;
    }

    /**
     * check whether there still exists another tokens in the buffer or not
     *
     * @return type: boolean
     */
    public boolean hasNext() {
        return currentToken != null;
    }

    /**
     * This function will find and extract a next token from {@code _buffer} and
     * save the token to {@code currentToken}.
     */
    public void next() {

        _buffer = _buffer.trim(); // remove whitespace

        if (_buffer.isEmpty()) {
            currentToken = null;    // if there's no string left, set currentToken null and return
            return;
        }

        char firstChar = _buffer.charAt(0);

        if (firstChar == '=' || firstChar == '<' || firstChar == '>') {
            currentToken = new Token(Character.toString(firstChar), Token.Type.COMPARISON);
        }

        if (firstChar == ';') {
            currentToken = new Token(";", Token.Type.SEMICOLON);
        }

        if (Character.isDigit(firstChar)) {
            StringBuilder stringBuilder = new StringBuilder(Character.toString(firstChar));
            for (int i = 1; i < _buffer.length() && Character.isDigit(_buffer.charAt(i)); i++) {
                stringBuilder.append(_buffer.charAt(i));
            }
            currentToken = new Token(stringBuilder.toString(), Token.Type.INT);
        }

        if (Character.isLetter(firstChar)) {
            int i = 0;
            while (Character.isLetter(_buffer.charAt(i)) && i < _buffer.length()) {
                i += 1;
            }
            String findWord = _buffer.substring(0, i);
            if (Token.regexMatching(findWord, Token.nameRegex) || Token.nameContaining(findWord)) {
                findWord = "name";
            } else if (Token.regexMatching(findWord, Token.locationRegex) || Token.locationContaining(findWord)) {
                findWord = "location";
            } else if (Token.regexMatching(findWord, Token.priceRegex) || Token.priceContaining(findWord)) {
                findWord = "price";
            } else if (Token.regexMatching(findWord, Token.yearRegex) || Token.yearContaining(findWord)) {
                findWord = "year";
            }
            if (Arrays.asList(Token.keyword).contains(findWord.toLowerCase())) {
                currentToken = new Token(findWord, Token.Type.KEYWORD);
            } else {
                currentToken = new Token(findWord, Token.Type.ELSE);
            }
        }

        // Remove the extracted token from buffer
        int tokenLen = currentToken.token().length();
        _buffer = _buffer.substring(tokenLen);
    }

    public static void main(String[] args) {
        String text = "loc = canberra; priceeeee < 100000 ; hyear > 2000";
        MyTokenizer tokenizer = new MyTokenizer(text);
        while (tokenizer.hasNext()) {
            System.out.println(tokenizer.currentToken.token());
            tokenizer.next();
        }
    }

}