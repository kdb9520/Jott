package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;
import validate.symbolTable;

public class ReturnStmtNode extends ExpressionNode {
    private Token token;
    ExpressionNode exprNode;

    
    public ReturnStmtNode (ExpressionNode expr) {
        this.exprNode = expr;
    }

    public boolean validateTree() throws SemanticException{
        // check if return type = expected return type
        if (!this.exprNode.getType().equals(symbolTable.getVarType("Return"))) {
            String actual = exprNode.getType();
            String expected = symbolTable.getFuncReturnType();
            String errMsg = "Invalid return type: " + actual + "\nExpected: " + expected;
            throw new SemanticException(errMsg, this.exprNode.getToken());
        }
        return true;
    }

    public String getType() {
        return this.exprNode.getType();
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
        String output = "return ";
        output += exprNode.convertToJott();
        output += ";\n";
        return output;
    }

    static public ReturnStmtNode parseReturnStmtNode(ArrayList<Token> tokens) throws SyntaxException, SemanticException {
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxException("Invalid token ", tokens.get(0));
        }
        if (!tokens.get(0).getToken().equals("return")){
            throw new SyntaxException("Invalid return: ", tokens.get(0));
        }
        tokens.remove(0);
        ExpressionNode expr = ExpressionNode.parseExpression(tokens);
        if (tokens.get(0).getTokenType() != TokenType.SEMICOLON) {
            throw new SyntaxException("Invalid return statement missing ; token type is: " + tokens.get(0).getTokenType(), tokens.get(0));
        }
        tokens.remove(0);
        return new ReturnStmtNode(expr);
    }

    public Token getToken() {
        return this.token;
    }
}