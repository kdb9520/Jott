package nodes;

/**
 * A class representing the Program part of the Jott grammar.  Meant to be 
 * the upper most node in the JottTree structure.
 * 
 * @author Max O'Malley
 */

import provided.JottTree;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

import validate.symbolTable;


public class ProgramNode implements JottTree {
    // Stubbing this out first
    ArrayList<FuncDefNode> functionDefinitions;

    public ProgramNode(ArrayList<FuncDefNode> funcDefArray){
        this.functionDefinitions = funcDefArray;
    }

    public boolean validateTree() throws SemanticException {
        // check for valid main function

        // verify existence of main
        if (symbolTable.hasFunc("main")) {
            symbolTable.setFunc("main");
            String mainReturnType = symbolTable.getVarType("Return");

            // verify main function return type is void
            if (!mainReturnType.equals("Void")) {
                String errMessage = "Main function does not have return type of 'Void'";
                throw new SemanticException(errMessage, "");
            }

            // verify main function takes no parameters
            int numParams = symbolTable.getParamCount();
            if (numParams != 0) {
                String errMessage = "Main function has " + numParams + " parameters but should take 0";
                throw new SemanticException(errMessage, "");
            }
        } 
        else {
            String errMessage = "Program must have main function";
            throw new SemanticException(errMessage, "");
        }

        for (FuncDefNode funcDefNode : functionDefinitions) {
            if (!funcDefNode.validateTree()) {
                throw new SemanticException("Invalid function definition.", "");
            }
        }
        return true;
    }

    public String convertToJava(String classname) {
        String stringBuilder = "public class ";
        stringBuilder += classname + " {\n";

        for (FuncDefNode funcDefNode : functionDefinitions) {
            stringBuilder += funcDefNode.convertToJava(classname) + "\n";
        }

        stringBuilder += "\n}";

        return stringBuilder;
    }

    public String convertToPython() {
        String stringBuilder = "";

        for (FuncDefNode funcDefNode : functionDefinitions) {
            stringBuilder += funcDefNode.convertToPython() + "\n";

        }

        // I'm pretty sure we have to manually call the main function for python
        stringBuilder += "main()";

        return stringBuilder;
    }

    public String convertToC() {
        String stringBuilder = "";

        stringBuilder += "#include <stdio.h>\n";
        stringBuilder += "#include <string.h>\n";
        stringBuilder += "#include <stdbool.h>\n\n";

        for (FuncDefNode funcDefNode : functionDefinitions) {
            stringBuilder += funcDefNode.convertToC() + "\n";
        }

        return stringBuilder;
    }

    public String convertToJott() {
        String stringToReturn = "";
        String currentFunctionText;

        for(int funcIndex = 0; funcIndex < this.functionDefinitions.size(); funcIndex++){
            // Iterate through all the function definitions, and call their convert to Jott func
            currentFunctionText = functionDefinitions.get(funcIndex).convertToJott();
            stringToReturn = stringToReturn.concat(currentFunctionText);
            stringToReturn = stringToReturn.concat("\n");
        }

        return stringToReturn;
    }

    
    public static ProgramNode parseProgramNode(ArrayList<Token> tokens) throws SyntaxException, SemanticException {
        // Create an empty func def array in case you're adding something to it
        ArrayList<FuncDefNode> funcDefList = new ArrayList<FuncDefNode>();
        FuncDefNode currentFunc;
        while (!tokens.isEmpty()) {
            // Now that we know there's a function definition, add it to the list 
            if((tokens.get(0).getTokenType() == TokenType.ID_KEYWORD) && 
            (tokens.get(0).getToken().equals("def"))){
                currentFunc = FuncDefNode.parseFuncDefNode(tokens);
                funcDefList.add(currentFunc);
            }
            else{
                // If you hit this case, it means there's a statement outside of a func, which is an error
                throw new SyntaxException("Statements cannot be outside of function definitions", 
                tokens.get(0));
            }
        }

        // Add a check to see if token list is empty? 
        symbolTable.printSymTab();

        return new ProgramNode(funcDefList);
    }
}
