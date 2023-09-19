import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import provided.Token;
import provided.TokenType;

public class DerekP1 {
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
          Token comma = new Token(str, filename, i, TokenType.COMMA);
          tokens.add(comma);
        } else if (str.charAt(i) == ']') {
          Token rbracket = new Token(str, filename, i, TokenType.R_BRACKET);
          tokens.add(rbracket);
        } else if (str.charAt(i) == '[') {
          Token lbracket = new Token(str, filename, i, TokenType.L_BRACKET);
          tokens.add(lbracket);
        } else if (str.charAt(i) == '}') {
          Token rbrace = new Token(str, filename, i, TokenType.R_BRACE);
          tokens.add(rbrace);
        } else if (str.charAt(i) == '{') {
          Token lbrace = new Token(str, filename, i, TokenType.L_BRACE);
          tokens.add(lbrace);
        }
      }
    }
    sc.close();

    return null;
  }
}
