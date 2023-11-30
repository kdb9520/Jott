package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

import validate.symbolTable;

public class While_LoopNode implements BodyStmtNode {
    private Token token;
    ExpressionNode exprNode;
    BodyNode bodyNode;

    
    public While_LoopNode (ExpressionNode expr, BodyNode body) {
        this.exprNode = expr;
        this.bodyNode = body;
    }

    public boolean validateTree() throws SemanticException {
        // call expr and body validateTree()
        if (!this.exprNode.validateTree()) {
            throw new SemanticException("Invalid expression node in While loop", "Expression type = " + this.exprNode.getType());
        }
        if (!this.bodyNode.validateTree()) {
            throw new SemanticException("Invalid body node in While loop", "");
        }
        return exprNode.validateTree() && bodyNode.validateTree();
    }

    public String convertToJava(String classname) {
        String output = "while";
        output += '(';
        output += exprNode.convertToJava(classname);
        output += ")";
        output += " {\n";
        output += bodyNode.convertToJava(classname);
        output += "}\n";
        return output;
    }

    public String convertToPython() {
        // TODO
        String output = "while";
        output += ' ';
        output += exprNode.convertToPython();
        output += " :\n";
        output += bodyNode.convertToPython();
        return output;
    }

    public String convertToC() {
        String output = "while";
        output += '(';
        output += exprNode.convertToC();
        output += ")";
        output += " {\n";
        output += bodyNode.convertToC();
        output += "}\n";
        return output;
    }

    public String convertToJott() {
        String output = "while";
        output += '[';
        output += exprNode.convertToJott();
        output += "]";
        output += " {\n";
        output += bodyNode.convertToJott();
        output += "}\n";
        return output;
    }

    static public While_LoopNode parseWhile_LoopNode(ArrayList<Token> tokens) throws SyntaxException, SemanticException {
        // Increment the depth since we're entering a while loop
        symbolTable.incrementIfWhileDepth();
        symbolTable.incrementWhileDepth();
        
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxException("Invalid token ", tokens.get(0));
        }
        if (!tokens.get(0).getToken().equals("while")){
            throw new SyntaxException("Invalid token: ", tokens.get(0));
        }
        tokens.remove(0);
        if (tokens.get(0).getTokenType() != TokenType.L_BRACKET) {
            throw new SyntaxException("Invalid token ", tokens.get(0));
        }
        tokens.remove(0);
        ExpressionNode expr = ExpressionNode.parseExpression(tokens);
        if (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            throw new SyntaxException("Invalid token", tokens.get(0));
        }
        tokens.remove(0);
        if (tokens.get(0).getTokenType() != TokenType.L_BRACE) {
            throw new SyntaxException("Invalid token ", tokens.get(0));
        }
        tokens.remove(0);
        BodyNode body = BodyNode.parseBodyNode(tokens);
        if (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
            throw new SyntaxException("Invalid token ", tokens.get(0));
        }
        tokens.remove(0);

        // Decrement the while counter now that you're leaving the while loop
        symbolTable.decrementIfWhileDepth();
        symbolTable.decrementWhileDepth();
        return new While_LoopNode(expr, body);
    }
}