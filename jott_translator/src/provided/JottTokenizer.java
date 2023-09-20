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

        String build_token = new String(); // initialize string to build token
        boolean first_decimal = true; // set boolean to check if multiple decimals are found in the same string

        while (str.charAt(i) == ' ') { // loop on white spaces
          continue;
        }
        if (str.charAt(i) == '#') { // handle comments throw away everything until /n
          while (str.charAt(i) != '\n') {
            System.out.println(str.charAt(i));
            if (i + 1 < str.length()) {
              i++;
            } else {
              break;
            }
          }
        }
        // handle single comma, brack, and brace cases
        if (str.charAt(i) == ',') {
          Token comma = new Token(",", filename, lineNum, TokenType.COMMA);
          tokens.add(comma);
        } else if (str.charAt(i) == ']') {
          Token rbracket = new Token("]", filename, lineNum, TokenType.R_BRACKET);
          tokens.add(rbracket);
        } else if (str.charAt(i) == '[') {
          Token lbracket = new Token("[", filename, lineNum, TokenType.L_BRACKET);
          tokens.add(lbracket);
        } else if (str.charAt(i) == '}') {
          Token rbrace = new Token("}", filename, lineNum, TokenType.R_BRACE);
          tokens.add(rbrace);
        } else if (str.charAt(i) == '{') {
          Token lbrace = new Token("{", filename, lineNum, TokenType.L_BRACE);
          tokens.add(lbrace);
        }
        ///////////////////////////////////////////////////////////////////////// Derek
        else if (str.charAt(i) == '=') {
          if (i + 1 < str.length() && str.charAt(i + 1) == '=') {
            i++; // increases iterator to unread part of string
            Token token = new Token("==", filename, lineNum, TokenType.REL_OP);
            tokens.add(token);
          } else {
            Token token = new Token("=", filename, lineNum, TokenType.ASSIGN);
            tokens.add(token);
          }
        } else if (str.charAt(i) == '>') {
          if (i + 1 < str.length() && str.charAt(i + 1) == '=') {
            i++; // increases iterator to unread part of string
            Token token = new Token(">=", filename, lineNum, TokenType.REL_OP);
            tokens.add(token);
          } else {
            Token token = new Token(">", filename, lineNum, TokenType.REL_OP);
            tokens.add(token);
          }
        } else if (str.charAt(i) == '<') {
          if (i + 1 < str.length() && str.charAt(i + 1) == '=') {
            i++; // increases iterator to unread part of string
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
        ////////////////////////////// Justin
        else if (str.charAt(i) == ':') {
          build_token += str.charAt(i);

          // if it has another colon it is a function header
          if (str.charAt(i + 1) == ':') {
            build_token += str.charAt(i + 1);
            Token fcHeader = new Token(build_token, filename, lineNum, TokenType.FC_HEADER);
            tokens.add(fcHeader);
            build_token = "";
          }

          // if it doesn't have a colon it is simply a colon
          else {
            Token colon = new Token(build_token, filename, lineNum, TokenType.COLON);
            tokens.add(colon);
            build_token = "";
          }
        }

        // if there is a ! it is potentially a relOp
        else if (str.charAt(i) == '!') {
          build_token += str.charAt(i);

          // must have an = to be valid
          if (str.charAt(i + 1) == '=') {
            build_token += str.charAt(i + 1);
            Token nEqRelOp = new Token(build_token, filename, i, TokenType.REL_OP);
            tokens.add(nEqRelOp);
            build_token = "";
          }

          // not valid case
          else {
            build_token = "";
            String errMessage = "Invalid token \"" + str.charAt(i + 1) + "\"";
            formattedTokenizerError(errMessage, lineNum, filename);
          }
        }
        ////////////////////////////////////////////// Kellen

        // If this is the case, dealing with a letter
        else if (Character.isLetter(str.charAt(i))) {
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
        } 
        else if (str.charAt(i) == '"') {
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
        //////////////////////////////////////////// Max
        else if ((Character.isDigit(str.charAt(i))) || (str.charAt(i) == '.')) { // if first char of token is digit or
                                                                            // decimal enter digit loop
          while (i < str.length()) { // check so that you don't go out of bounds
            if (Character.isDigit((str.charAt(i)))) { // if digit, add to token string
              build_token += str.charAt(i);
            } else if (str.charAt(i) == '.' && first_decimal) { // if first decimal in current token
              if ((i != 0)) {
                if (Character.isDigit(str.charAt(i - 1))) { // check if there was a number beforehand
                  build_token += str.charAt(i);
                  first_decimal = false;
                } else if (i < str.length() - 1) { // else check if there is a number afterwards
                  if (Character.isDigit(str.charAt(i + 1))) {
                    build_token += str.charAt(i);
                    first_decimal = false;
                  }
                } else { // else error out because there is a decimal by itself
                  System.out.println("Error: Cannot take a decimal by itself");
                  break;
                }
              } else if (i < str.length() - 1) { // if there is no existing character before the decimal
                if (Character.isDigit(str.charAt(i + 1))) { // check if the decimal is followed by a number
                  build_token += str.charAt(i);
                  first_decimal = false;
                } else { // else error out because there is a decimal by itself
                  System.out.println("Error: Cannot take a decimal by itself");
                  break;
                }
              }
            } else if (str.charAt(i) == '.' && !first_decimal) { // if there is a decimal but it isn't the first
              if (i < str.length() - 1) {
                if (Character.isDigit(str.charAt(+1))) { // check if followed by a digit, if so then
                                                         // complete current token and start a new one with this decimal
                  i--;
                  break;
                } else { // else return error for trying to put 2 decimals in the same number
                  System.out.println("Error: Cannot have 2 decimals in one number");
                  build_token = "";
                  break;
                }
              } else { // else return error for trying to put 2 decimals in the same number
                System.out.println("Error: Cannot have 2 decimals in one number");
                build_token = "";
                break;
              }
            } else if (str.charAt(i) == ' ') { // if space ignore
              break;
            } else { // if non-digit or decimal then go back to loop with current character to try
                     // something else
              i--;
              break;
            }
            i++;
          }
        }
        if (!build_token.isEmpty()) { // if build token isn't empty and loop ends then add token
          System.out.println(build_token);
          Token curr_token = new Token(build_token, filename, lineNum, TokenType.NUMBER);
          tokens.add(curr_token);

        }
        ///////////////////////////////////////////////// Eligh

      }
    }
    sc.close();

    return tokens;

  }

  private static void formattedTokenizerError(String errorMessage, int lineNumber, String filename) {
    System.err.println("Syntax Error");
    System.err.println(errorMessage);
    System.err.println(filename + ":" + lineNumber);
  }
}