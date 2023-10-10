import java.util.ArrayList;

import provided.Token;
import provided.TokenType;
import provided.JottTree;

public class FuncParamNode implements JottTree {
    private Token token;
    private ArrayList<ExpressionNode> paramsList;


    /*
     * What this needs is an array list of tokens as the professor suggested
     * 
     */
    
    public FuncParamNode (Token t) {
        // Confirm what format this needs 
        this.token = t;
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
        return this.token.getToken();
    }

    static public FuncParamNode parseFuncParamNode(ArrayList<Token> tokens) throws SyntaxException {
        ArrayList<ExpressionNode> params = new ArrayList<ExpressionNode>(); 
        
        // Check if the current token is an expression 
        // If it is, make an expressionNode based on whatever its value is, by calling parseExpressionNode


        // Start a while loop which continues while the next token is a , 
        // 


        // if(tokenList.get(0).getTokenType() == )


        // Here to stop errors for now
        throw new SyntaxException("PLACEHOLDER Error Message", tokens.get(0));
    }
}
