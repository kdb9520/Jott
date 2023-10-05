import java.util.ArrayList;

import provided.Token;
import provided.TokenType;
import provided.JottTree;

public class FuncParamNode implements JottTree {
    private Token token;
    
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
        
        // Here to stop errors for now
        throw new SyntaxException("PLACEHOLDER Error Message", tokens.get(0));
    }
}
