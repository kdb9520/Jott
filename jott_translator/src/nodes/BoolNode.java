package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class BoolNode extends ExpressionNode {
    private Token token;
    
    public BoolNode (Token t) {
        this.token = t;
    }

    public String getType() {
        return "Boolean";
    }

    public boolean validateTree() {
        // Correct by default since the parser checks that
        return true;
    }

    public String convertToJava(String classname) {
        // Java needs lowercase true/false, so just return those
        return token.getToken().toLowerCase();
    }

    public String convertToPython() {
        // Bools in Python are the same
        return this.token.getToken();
    }

    public String convertToC() {
        // NEED TO ADD #include <stdbool.h>
        // For this implementation to work

        return this.token.getToken().toLowerCase();
    }

    public String convertToJott() {
        return this.token.getToken();
    }

    public Token getToken() {
        return this.token;
    }

    static public BoolNode parseBoolNode(ArrayList<Token> tokens) throws SyntaxException {
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxException("Invalid token " + tokens.get(0).getToken() + 
            "with type " + tokens.get(0).getTokenType() +
            ".\nExpected Type: " + TokenType.ID_KEYWORD, tokens.get(0));
        }
        // Check to make sure the boolean text is in the correct format
        if(!tokens.get(0).getToken().equals("True") &&
            !tokens.get(0).getToken().equals("False")){  
            throw new SyntaxException("BoolNode has an invalid value that is not True or False: " + 
            tokens.get(0).getToken(),
            tokens.get(0));
        }

        return new BoolNode(tokens.remove(0));
    }
}
