import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import provided.Token;
import provided.TokenType;

public class KellenP1 {
    /**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename){
            
        File file = new File(filename);
        Scanner sc = new Scanner(System.in);
        ArrayList<Token> tokens = new ArrayList<>();

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File " + filename + " not found.");
        }
        
        while (sc.hasNext()) {
            String str = sc.nextLine();
            for (int i = 0; i < str.length(); i++) {
                System.out.println(str.charAt(i));
                // make new tokens and add them to the ArrayList token
                
                // starts with a colon
                if (str.charAt(i) == ':'){

                    // if it has another colon it is a function header
                    if (str.charAt(i+1) == ':'){
                        Token fcHeader = new Token(str, filename, i, TokenType.FC_HEADER);
                        tokens.add(fcHeader);
                    } 
                    
                    // if it doesn't have a colon it is simply a colon
                    else {
                        Token colon = new Token(str, filename, i, TokenType.COLON);
                        tokens.add(colon);
                    }
                }

                // if there is a ! it is potentially a relOp
                if (str.charAt(i) == '!'){

                    // must have an = to be valid
                    if (str.charAt(i+1) == '='){
                        Token nEqRelOp = new Token(str, filename, i, TokenType.REL_OP);
                        tokens.add(nEqRelOp);
                    }

                    // not valid case
                    else {
                        formattedTokenizerError(currentString, lineNum, filename);
                    }
                }

            }
        }

        sc.close();
    
        return null;
	}
}
