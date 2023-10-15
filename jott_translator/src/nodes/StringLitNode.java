import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class StringLitNode extends ExpressionNode {
    // This is the string literal node

    // This token holds the value of the string, and is of tokenType STRING
    private Token token;

    public StringLitNode (Token stringToken){
        this.token = stringToken;
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
        // This should be as simple as returning the string token for now
        return this.token.getToken();
    }
}
