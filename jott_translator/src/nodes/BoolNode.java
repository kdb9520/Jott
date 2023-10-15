import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class BoolNode {
    private Token token;
    
    public BoolNode (Token t) {
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

    static public BoolNode parseBoolNode(ArrayList<Token> tokens) throws SyntaxException {
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxException("Invalid token " + tokens.get(0) + 
            "with type " + tokens.get(0).getTokenType() +
            ".\nExpected Type: " + TokenType.ID_KEYWORD, tokens.get(0));
        }
        // Check to make sure the boolean text is in the correct format
        if(!tokens.get(0).getToken().equals("True") || 
            !tokens.get(0).getToken().equals("False")){  
            throw new SyntaxException("BoolNode has an invalid value that is not True or False: " + 
            tokens.get(0).getToken(),
            tokens.get(0));
        }

        return new BoolNode(tokens.remove(0));
    }
}
