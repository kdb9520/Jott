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

    
    public static ArrayList<Token> readLetterTokens(String str){
        // To ease importing into JottTokenizer, I should try and keep the variable names the same
        // Use tokens here and replicate the loop from the tokenizer

        ArrayList<Token> tokens = new ArrayList<>();

        // This for replicates the inner loop of the while loop inside of tokenize
        for(int i = 0; i < str.length(); i++) {
            // Two main outer cases, either a letter or a "
        }

        return tokens;

    }

    public static void main(String[] args){
        // Need to handle the letter and "" case 
        // Will eventually do some testing of it here

        
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