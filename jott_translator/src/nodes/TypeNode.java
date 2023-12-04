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
            throw new SyntaxException("Invalid token " + t.getToken() + 
            ".\nExpected tokens: Double, Integer, String, Boolean, Void", t);
        }

        String token = t.getToken();
        tokens.remove(0);

        if (token.equals("Double") || token.equals("Integer") 
            || token.equals("String") || token.equals("Boolean") 
            || token.equals("Void")) {
            return new TypeNode(t);
        } else {
            throw new SyntaxException("Invalid token " + t.getToken() + 
            ".\nExpected tokens: Double, Integer, String, Boolean, Void", t);
        }
        
    }

    @Override
    public String convertToJott() {
        return this.type.getToken();
    }

    @Override
    public String convertToJava(String className) {
        switch (this.type.getToken()) {
            case "Integer":
                return "int";
            case "Double":
                return "double";
            case "String":
                return "String";
            case "Boolean":
                return "boolean";
            case "Void":
                return "void";
            default:
                return "";
        }
    }

    @Override
    public String convertToC() {
        switch (this.type.getToken()) {
            case "Integer":
                return "int";
            case "Double":
                return "double";
            case "String":
                return "char *";
            case "Boolean":
                return "bool";
            case "Void":
                return "void";
            default:
                return "";
        }
    }

    @Override
    public String convertToPython() {
        // no types
        return "";
    }

    @Override
    public boolean validateTree() {
        return true;
    }
    
}
