import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class NumberNode implements JottTree {
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

    static public IDNode parseIDNode(ArrayList<Token> tokens) {
        if (tokens.get(0).getTokenType() != TokenType.NUMBER) {
            // throw syntax exception
        }
        return new IDNode(tokens.remove(0));
    }
}