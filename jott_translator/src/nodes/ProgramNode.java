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


public class ProgramNode implements JottTree {
    // Stubbing this out first
    ArrayList<FuncDefNode> functionDefinitions;

    public ProgramNode(ArrayList<FuncDefNode> funcDefArray){
        this.functionDefinitions = funcDefArray;
    }

    public boolean validateTree() {
        // TODO
        return false;
    }

    public String convertToJava(String classname) {
        // TODO
        return "not implemented";
    }

    public String convertToPython() {
        // TODO
        return "not implemented";
    }

    public String convertToC() {
        // TODO
        return "not implemented";
    }

    public String convertToJott() {
        String stringToReturn = "";
        String currentFunctionText;

        for(int funcIndex = 0; funcIndex < this.functionDefinitions.size(); funcIndex++){
            // Iterate through all the function definitions, and call their convert to Jott func
            currentFunctionText = functionDefinitions.get(funcIndex).convertToJott();
            stringToReturn = stringToReturn.concat(currentFunctionText);
        }

        return stringToReturn;
    }

    
    public static ProgramNode parseProgramNode(ArrayList<Token> tokens) throws SyntaxException {
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
                break;
            }
        }

        // Add a check to see if token list is empty? 

        return new ProgramNode(funcDefList);
    }
}
