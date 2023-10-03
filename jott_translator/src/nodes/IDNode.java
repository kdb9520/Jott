import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class IDNode implements JottTree {
    private Token token;
    
    public IDNode (Token t) {
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

    static public IDNode partseIDNode(ArrayList<Token> tokens) throws SyntaxException {
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxException("Invalid token " + tokens.get(0) + 
            "with type " + tokens.get(0).getTokenType() +
            ".\nExpected Type: " + TokenType.ID_KEYWORD);
        }
        return new IDNode(tokens.remove(0));
    }
}