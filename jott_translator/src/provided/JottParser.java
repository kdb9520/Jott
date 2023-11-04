package provided;

// import nodes;

import validate.symbolTable;

/**
 * This class is responsible for paring Jott Tokens
 * into a Jott parse tree.
 *
 * @author Max O'Malley
 */

import nodes.*;

import java.util.ArrayList;

public class JottParser {

  static String lastTokenFilename;
  static int lastTokenLineNumber;

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
        if(tokens.size() != 0){
          Token lastToken = tokens.get(tokens.size() - 1);
          lastTokenFilename = lastToken.getFilename();
          lastTokenLineNumber = lastToken.getLineNum();
        }
        return ProgramNode.parseProgramNode(tokens);
      }

      catch (SyntaxException e){

        System.err.println(e.getMessage());
        return null;
      }
      catch(SemanticException e){
        System.err.println(e.getMessage());
        return null;
      }
      catch(IndexOutOfBoundsException e){
        System.err.println("IndexOutOfBounds Exception");
        System.err.println("Unexpected EOF Reached");
        System.err.println(lastTokenFilename + ":" + lastTokenLineNumber);
        return null;
      }

    }
}
