package validate;

import provided.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import nodes.*;

public class phase3Main {
    
    
    public static void processJott(String jottFilename) {
        // This needs to somehow run all the phases up to this point.
        // Tokenize (call tokenizer)
        // Parse (call parser)
        // Run validateTree function
        // In phase 4 we can have another param that is the langauge to write to. 
        // For now treat it as Jott always

        ArrayList<Token> tokenList = JottTokenizer.tokenize(jottFilename);
        if(tokenList == null){
            // Do some sort of error message here
            System.out.println("");
        }
    }


    public static void main(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file name");
        String filename = sc.nextLine();
        sc.close();
        processJott(filename);
        // This will deal with .io and then call processJott
    }
}
