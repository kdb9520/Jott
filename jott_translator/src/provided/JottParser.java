package provided;

// import nodes;

/**
 * This class is responsible for paring Jott Tokens
 * into a Jott parse tree.
 *
 * @author
 */

import java.util.ArrayList;

public class JottParser {

    /**
     * Parses an ArrayList of Jotton tokens into a Jott Parse Tree.
     * @param tokens the ArrayList of Jott tokens to parse
     * @return the root of the Jott Parse Tree represented by the tokens.
     *         or null upon an error in parsing.
     */
    public static JottTree parse(ArrayList<Token> tokens){
      // We need to change the nodes to be a package so we can grab the info from it 
      // In this separate package

      // To my understanding, this would need to have a try catch in which 
      // the token list just has the top most token look for the program definition
      try {

      }
      // WILL BE CHANGED TO SYNTAX ERROR LATER
      catch (Error e){

      }

		return null;
    }
}
