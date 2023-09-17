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
   * @param filename the name of the file to tokenize; can be relative or absolute path
   * @return an ArrayList of Jott Tokens
   */
  public static ArrayList<Token> tokenize(String filename){
		
    File file = new File(filename);
    Scanner sc;
    ArrayList<Token> tokens = new ArrayList<>();

    try {
      sc = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.err.println("File " + filename + " not found.");
      return null;
    }
    
    while (sc.hasNext()) {
      String str = sc.nextLine();
      for (int i = 0; i < str.length(); i++) {
        System.out.println(str.charAt(i));
        // make new tokens and add them to the ArrayList token
      }
    }
    sc.close();
    
    return tokens;
	}
}