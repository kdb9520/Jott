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
        // Will need to look into string formatting of Jott Strings compared to other langs later
        return this.token.getToken();
    }

    static public StringLitNode parseStringLitNode(ArrayList<Token> tokens) throws SyntaxException {
        if(tokens.get(0).getTokenType() != TokenType.STRING){
            throw new SyntaxException("Invalid token " + tokens.get(0) + 
            "with type " + tokens.get(0).getTokenType() +
            ".\nExpected Type: " + TokenType.STRING, tokens.get(0));
        }

        // If all the above passes, then just return a new stringLitNode
        return new StringLitNode(tokens.remove(0));
    }

}
