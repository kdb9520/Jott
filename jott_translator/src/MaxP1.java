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

                System.out.println("The token to add: " +  currentString);
                // Now that all the characters with the current id are found, construct the token
                Token currentToken = new Token(currentString, filename, lineNum, TokenType.ID_KEYWORD);
                // Append the token to the list
                tokens.add(currentToken);
            }

            // This is the token to be built in this loop
           

            // After the above, if no error occured, check if the 
        }

        return tokens;

    }

    public static void main(String[] args){
        // Need to handle the letter and "" case 
        // Will eventually do some testing of it here
        readLetterTokens("something");
        readLetterTokens("UPPER CASE");
        readLetterTokens("Sta11");
        readLetterTokens("Tes1ing");
        
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
 * 
 */