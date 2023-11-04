package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class TypeNode implements JottTree {

    private Token type;

    public TypeNode(Token t) {
        this.type = t;
    }

    public Token getNodeToken() {
        // Getter for this node's token
        // for use in AsmtNode's populating of the symbolTable
        return type;
    }

    static public TypeNode parseTypeNode(ArrayList<Token> tokens) throws SyntaxException {
        Token t = tokens.get(0);

        if (t.getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxException("Invalid token " + t + 
            "with type " + t.getTokenType() +
            ".\nExpected Type: " + TokenType.ID_KEYWORD, t);
        }

        String token = t.getToken();
        tokens.remove(0);

        if (token.equals("Double") || token.equals("Integer") 
            || token.equals("String") || token.equals("Boolean") 
            || token.equals("Void")) {
            return new TypeNode(t);
        } else {
            throw new SyntaxException("Invalid token " + t + 
            "with token " + token +
            ".\nExpected tokens: Double, Integer, String, Boolean, Void", t);
        }
        
    }

    @Override
    public String convertToJott() {
        return this.type.getToken();
    }

    @Override
    public String convertToJava(String className) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToJava'");
    }

    @Override
    public String convertToC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToC'");
    }

    @Override
    public String convertToPython() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToPython'");
    }

    @Override
    public boolean validateTree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }
    
}
