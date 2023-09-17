import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import provided.Token;
import provided.TokenType;

public class Justinp1 {

    public static void main(String[] args) {

    }

    public static ArrayList<Token> tokenize(String filename) {
        File file = new File(filename);
        Scanner sc;
        ArrayList<Token> tokens = new ArrayList<>();
        int lineNum = 0;

        try {
            sc = new Scanner(file);
            while (sc.hasNext()) {
                String str = sc.nextLine();
                for (int i = 0; i < str.length(); i++) {

                    // handle relational operator cases
                    if (str.charAt(i) == '=') {
                        if (i+1 < str.length() && str.charAt(i+1) == '=') {
                            i++;  // increases iterator to unread part of string
                            Token token = new Token("==", filename, lineNum, TokenType.REL_OP);
                            tokens.add(token);
                        } else {
                            Token token = new Token("=", filename, lineNum, TokenType.ASSIGN);
                            tokens.add(token);
                        }
                    } else if (str.charAt(i) == '>') {
                        if (i+1 < str.length() && str.charAt(i+1) == '=') {
                            i++;  // increases iterator to unread part of string
                            Token token = new Token(">=", filename, lineNum, TokenType.REL_OP);
                            tokens.add(token);
                        } else {
                            Token token = new Token(">", filename, lineNum, TokenType.REL_OP);
                            tokens.add(token);
                        }
                    } else if (str.charAt(i) == '<') {
                        if (i+1 < str.length() && str.charAt(i+1) == '=') {
                            i++;  // increases iterator to unread part of string
                            Token token = new Token("<=", filename, lineNum, TokenType.REL_OP);
                            tokens.add(token);
                        } else {
                            Token token = new Token("<", filename, lineNum, TokenType.REL_OP);
                            tokens.add(token);
                        }

                    // handle semi-colon case
                    } else if (str.charAt(i) == ';') {
                        Token token = new Token(";", filename, lineNum, TokenType.SEMICOLON);
                        tokens.add(token);
                    
                    // handle arithmetic cases
                    } else if (str.charAt(i) == '/') {
                        Token token = new Token("/", filename, lineNum, TokenType.MATH_OP);
                        tokens.add(token);
                    } else if (str.charAt(i) == '+') {
                        Token token = new Token("+", filename, lineNum, TokenType.MATH_OP);
                        tokens.add(token);
                    } else if (str.charAt(i) == '-') {
                        Token token = new Token("-", filename, lineNum, TokenType.MATH_OP);
                        tokens.add(token);
                    } else if (str.charAt(i) == '*') {
                        Token token = new Token("*", filename, lineNum, TokenType.MATH_OP);
                        tokens.add(token);
                    }
                }

                // increase line number
                lineNum++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File " + filename + " not found.");
            return null;
        }

        // close scanner
        sc.close();

        return tokens;

    }
}
