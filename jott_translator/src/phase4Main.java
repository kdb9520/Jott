import validate.*;

import provided.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import nodes.*;

public class phase4Main {
    
    public static boolean processJott(String jottFilename, String outputFilename, String outputLanguage) {
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
                if (outputLanguage == "Jott") {
                    currentWriter.write(nodeTree.convertToJott());
                }
                else if (outputLanguage == "Python") {
                    currentWriter.write(nodeTree.convertToPython());
                }
                else if (outputLanguage == "Java") {
                    String java_class = jottFilename.substring(0, jottFilename.length() - 5);
                    currentWriter.write(nodeTree.convertToJava(java_class));
                }
                else if (outputLanguage == "C") {
                    currentWriter.write(nodeTree.convertToC());
                }
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
        if(args.length != 3){
            System.err.println("phase3Main requires three command line args to run: ");
            System.err.println("java validate/phase3Main inputJottFilename OutputFilename outputLanguage");
            return;
        }

        // Instead of the above I'm going to assume input for the command line args
        String input_filename = args[0];
        String output_filename = args[1];
        String output_language = args[2];

        processJott(input_filename, output_filename, output_language);
        // This will deal with .io and then call processJott
    }
}
