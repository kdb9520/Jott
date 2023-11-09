package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;
import validate.symbolTable;

public class IDNode extends ExpressionNode {
    private Token token;
    
    public IDNode (Token t) {
        this.token = t;
    }

    public Token getNodeToken() {
        // Getter for this node's token
        // for use in AsmtNode's populating of the symbolTable
        return token;
    }

    public String getType() {
        return symbolTable.getVarType(this.token.getToken());
    }

    public boolean validateTree() {
        // TODO
        return true;
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

    public Token getToken() {
        return this.token;
    }

    static public IDNode parseIDNode(ArrayList<Token> tokens) throws SyntaxException {
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxException("Invalid token " + tokens.get(0) + 
            "with type " + tokens.get(0).getTokenType() +
            ".\nExpected Type: " + TokenType.ID_KEYWORD, tokens.get(0));
        }
        return new IDNode(tokens.remove(0));
    }
}