import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class NumberNode extends ExpressionNode {
    private Token token;
    
    public NumberNode (Token t) {
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

    static public NumberNode parseNumberNode(ArrayList<Token> tokens) {
        if (tokens.get(0).getTokenType() != TokenType.NUMBER) {
            // throw syntax exception
        }
        return new NumberNode(tokens.remove(0));
    }
}