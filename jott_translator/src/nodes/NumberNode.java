package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class NumberNode extends ExpressionNode {
    private Token token;
    
    public NumberNode (Token t) {
        this.token = t;
    }

    public boolean validateTree() {
        // This is always valid by itself, and will be valid by default
        return true;
    }

    public TokenType getTokenType() {
        return this.token.getTokenType();
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

    static public NumberNode parseNumberNode(ArrayList<Token> tokens) throws SyntaxException{
        if (tokens.get(0).getTokenType() != TokenType.NUMBER) {
            // throw syntax exception
            throw new SyntaxException("Invalid token " + tokens.get(0) + 
            "with type " + tokens.get(0).getTokenType() +
            ".\nExpected Type: " + TokenType.NUMBER, tokens.get(0));
        }
        return new NumberNode(tokens.remove(0));
    }

    public Token getToken() {
        return this.token;
    }
}