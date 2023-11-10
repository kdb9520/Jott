package validate;

import provided.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import nodes.*;

public class phase3Main {
    
    public static boolean processJott(String jottFilename, String outputFilename) {
        // This needs to somehow run all the phases up to this point.
        // Tokenize (call tokenizer)
        // Parse (call parser)
        // Run validateTree function
        // In phase 4 we can have another param that is the langauge to write to. 
        // For now treat it as Jott always

        ArrayList<Token> tokenList = JottTokenizer.tokenize(jottFilename);
        if(tokenList == null){
            // That means an error happened, the tokenizer printed already so just stop
            return false;
        }

        // Now you have the tokens, so get the node tree
        JottTree nodeTree = JottParser.parse(tokenList);
        if(nodeTree == null){
            // Hit some kind of error that already was printed during tree processing
            return false;
        }

        boolean valid;
        try {
            valid = nodeTree.validateTree();
        } catch (SemanticException e) {
            System.err.println(e.getMessage());
            valid = false;
        }
        if(valid == true) {
            // write to output file
            try{
                // For now this will only write Jott. Will need to be changed to 
                // write out other languages later
                FileWriter currentWriter = new FileWriter(outputFilename);
                currentWriter.write(nodeTree.convertToJott());
                currentWriter.close();
                // Will need to be removed later but is here to confirm for now
                System.out.println("Successfully printed Jott to file: " + outputFilename);
                return true;
            }
            catch(IOException e){
                System.err.println("Attempt to write to " + outputFilename + " failed due to the following: ");
                System.err.println(e);
                return false;
            }
        }
        else {
            // Means valid equals false so return false
            return false;
        }
    }


    public static void main(String[] args) {
        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter input file name");
        String input_filename = sc.nextLine();
        System.out.println("Enter output file name");
        String output_filename = sc.nextLine();
        sc.close();
        */

        
        if(args.length != 2){
            // INSERT HELP STATEMENT IF THE LENGTH OF ARGS IS WRONG HERE
            System.err.println("phase3Main requires two command line args to run: ");
            System.err.println("java validate/phase3Main inputJottFilename OutputFilename");

            return;
        }

        // Instead of the above I'm going to assume input for the command line args
        String input_filename = args[0];
        String output_filename = args[1];

        processJott(input_filename, output_filename);
        // This will deal with .io and then call processJott
    }
}
