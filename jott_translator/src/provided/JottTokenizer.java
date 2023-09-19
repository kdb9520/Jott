package provided;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author 
 **/

import java.util.ArrayList;
import java.util.Scanner;

public class JottTokenizer {

  /**
   * Takes in a filename and tokenizes that file into Tokens
   * based on the rules of the Jott Language
   * 
   * @param filename the name of the file to tokenize; can be relative or absolute
   *                 path
   * @return an ArrayList of Jott Tokens
   */
  public static ArrayList<Token> tokenize(String filename) {

    File file = new File(filename);
    Scanner sc;
    ArrayList<Token> tokens = new ArrayList<>();

    try {
      sc = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.err.println("File " + filename + " not found.");
      return null;
    }

    int lineNum = 0;

    while (sc.hasNext()) {
      String str = sc.nextLine();
      lineNum++;
      for (int i = 0; i < str.length(); i++) {
        System.out.println(str.charAt(i));
        // make new tokens and add them to the ArrayList token

        // If this is the case, dealing with a letter
        if (Character.isLetter(str.charAt(i))) {
          // Minimally, the current token will be made up of just this character
          String currentString = new String();
          // Using this to loop over the characters to add as many to the current id
          while (i < str.length()) {
            // Check if you're dealing with a character, if so, continue to loop
            char currentChar = str.charAt(i);
            if (Character.isLetter(currentChar) || Character.isDigit(currentChar)) {
              // If either is true, then the value is valid for an id/keyword
              // In that case append the character to the end of the current string
              currentString = currentString.concat(Character.toString(currentChar));

              // Iterate i to look at the next character
              i++;
            } else {
              // Found a non letter/digit so break the loop.
              // Also decrement so that this character can be looked at by the outer loop
              i--;
              break;
            }
          }

          System.out.println("The id token to add: " + currentString);
          // Now that all the characters with the current id are found, construct the
          // token
          Token currentToken = new Token(currentString, filename, lineNum, TokenType.ID_KEYWORD);
          // Append the token to the list
          tokens.add(currentToken);
        } else if (str.charAt(i) == '"') {
          String currentString = "\"";
          // Check to ensure that the string will end in a "
          boolean stringEndsWithQuote = false;

          // Increment i by 1 since the first " is already added to the currentString
          i++;

          while (i < str.length()) {
            char currentChar = str.charAt(i);
            if (currentChar == '"') {
              // Means that you reached the end of the string
              // Mark the string as ending properly then finish the loop
              stringEndsWithQuote = true;
              currentString = currentString.concat(Character.toString(currentChar));
              break;
            } else if (Character.isLetter(currentChar) || Character.isDigit(currentChar) || currentChar == ' ') {
              // If above is true, you still have a valid string
              currentString = currentString.concat(Character.toString(currentChar));

              // Iterate i to look at the next character
              i++;
            } else {
              // In this case you hit a invalid follow up in a string, break to hit the below
              // error
              String errMessage = "Invalid token \"" + currentChar + "\"";
              formattedTokenizerError(errMessage, lineNum, filename);
              return null;
            }

            // Figure out how to add a check to see if you didn't end the string before the
            // line ended
          }

          if (!stringEndsWithQuote) {
            // Means that you hit an error as the string never ended properly
            String errorMessage = "ERROR: A string token did not end in a \" character on line: " + lineNum;
            formattedTokenizerError(errorMessage, lineNum, filename);
            return null;
          }

          System.out.println("The string token to add: " + currentString);
          // No errors found for the string, so make a new token
          Token currentToken = new Token(currentString, filename, lineNum, TokenType.STRING);
          // Append the token to the list
          tokens.add(currentToken);
        }

      }
    }
    sc.close();

    return tokens;
  }

  private static void formattedTokenizerError(String errorMessage, int lineNumber, String filename){
    System.err.println("Syntax Error"); 
    System.err.println(errorMessage);
    System.err.println(filename + ":" + lineNumber);
}
}