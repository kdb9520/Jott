/*
 * MaxP1.java
 * 
 * Intended to implement the use cases for the letter and " cases of the DFA 
 * for phase 1 of the project.  Code from here will eventually be ported into the 
 * JottTokenizer
 * 
 * Author: Max O'Malley 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


import provided.Token;
import provided.TokenType;



public class MaxP1 {

    // The below values will be from outside of the loop in the JottTokenizer
    static String filename = "Placeholder";
    static int lineNum = 1;

    
    public static ArrayList<Token> readLetterTokens(String str){
        // To ease importing into JottTokenizer, I should try and keep the variable names the same
        // Use tokens here and replicate the loop from the tokenizer

        ArrayList<Token> tokens = new ArrayList<>();

        // This for replicates the inner loop of the while loop inside of tokenize
        for(int i = 0; i < str.length(); i++) {
            // Two main outer cases, either a letter or a "

            // If this is the case, dealing with a letter 
            if(Character.isLetter(str.charAt(i))){
                // Minimally, the current token will be made up of just this character 
                String currentString = new String();
                // Using this to loop over the characters to add as many to the current id
                while(i < str.length()){
                    // Check if you're dealing with a character, if so, continue to loop
                    char currentChar = str.charAt(i);
                    if(Character.isLetter(currentChar) || Character.isDigit(currentChar)){
                        // If either is true, then the value is valid for an id/keyword
                        // In that case append the character to the end of the current string
                        currentString = currentString.concat(Character.toString(currentChar));

                        // Iterate i to look at the next character 
                        i++;
                    }
                    else {
                        // Found a non letter/digit so break the loop.
                        // Also decrement so that this character can be looked at by the outer loop
                        i--;
                        break;
                    }
                }

                System.out.println("The id token to add: " +  currentString);
                // Now that all the characters with the current id are found, construct the token
                Token currentToken = new Token(currentString, filename, lineNum, TokenType.ID_KEYWORD);
                // Append the token to the list
                tokens.add(currentToken);
            }
            else if(str.charAt(i) == '"'){
                String currentString = "\"";
                // Check to ensure that the string will end in a "
                boolean stringEndsWithQuote = false;
                
                // Increment i by 1 since the first " is already added to the currentString
                i++;

                while(i < str.length()){
                    char currentChar = str.charAt(i);
                    if(currentChar == '"'){
                        // Means that you reached the end of the string
                        // Mark the string as ending properly then finish the loop
                        stringEndsWithQuote = true;
                        currentString = currentString.concat(Character.toString(currentChar));
                        break;
                    }
                    else if(Character.isLetter(currentChar) || Character.isDigit(currentChar) || currentChar == ' '){
                        // If above is true, you still have a valid string
                        currentString = currentString.concat(Character.toString(currentChar));

                        // Iterate i to look at the next character 
                        i++;
                    }
                    else{
                        // In this case you hit a invalid follow up in a string, break to hit the below error
                        String errMessage = "Invalid token \"" + currentChar + "\"";
                        formattedTokenizerError(errMessage, lineNum, filename);
                        return null;
                    }

                    // Figure out how to add a check to see if you didn't end the string before the line ended
                }

                if(!stringEndsWithQuote){
                    // Means that you hit an error as the string never ended properly
                    String errorMessage = "ERROR: A string token did not end in a \" character on line: " + lineNum;
                    formattedTokenizerError(errorMessage, lineNum, filename);
                    return null;
                }

                System.out.println("The string token to add: " +  currentString);
                // No errors found for the string, so make a new token
                Token currentToken = new Token(currentString, filename, lineNum, TokenType.STRING);
                // Append the token to the list
                tokens.add(currentToken);
            }

        }

        return tokens;

    }


    public static void printTokenList(ArrayList<Token> tokenList){
        System.out.println("Printing a token list in order: ");
    }

    public static void formattedTokenizerError(String errorMessage, int lineNumber, String filename){
        System.err.println("Syntax Error"); 
        System.err.println(errorMessage);
        System.err.println(filename + ":" + lineNumber);
    }


    public static void main(String[] args){
        // Need to handle the letter and "" case 
        // Will eventually do some testing of it here

        // id/keyword testing
        readLetterTokens("A");
        readLetterTokens("something");
        readLetterTokens("UPPER CASE");
        readLetterTokens("Sta11");
        readLetterTokens("Tes1ing");
        readLetterTokens("a b C D");
        readLetterTokens("First\nSecond");

        System.out.println("\nString Testing:");

        // string testing
        readLetterTokens("\"Sample\"");
        readLetterTokens("\" Testing Spaces Here \"");
        readLetterTokens("\"1234567890\"");
        readLetterTokens("\"All 011a\"");
        readLetterTokens("\"String ends here");
        readLetterTokens("\"Invalid?!\"");

        System.out.println("\nCombined Testing:");

        // Combined testing
        readLetterTokens("id \"string\"");
        readLetterTokens(" key\" str1ng \" ");
        
        
    }
    
}



/*
 * Main thing for this is just creating a set with all the letters and digits to compare to
 * Once I do that I just need to determine if the current character is in that list.  
 * If it is, then we're good.  If not, I need to error out 
 * Also need to figure out how exactly to handle the looping case 
 * 
 * 
 * Need to handle two cases: 
 * Cases when you start with a letter
 * Cases when you start with a "
 */

/*
 * Ask Scott about if the letters only include the 26 alphanumeric letters for upper and lower case
 * or if it includes other symbolds like & $ ! ?
 * 
 * For a string, do we still consider the string valid if you just have a " followed by nothing with a 
 * " after?  It seems like it SHOULD work but I don't know that for certain.
 * 
 * 
 * Can I assume there are no multi line strings?
 * Based on page 2 of hte Jott write up, yes.  
 * Strings are NOT allowed to wrap lines
 */