import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class ReturnStmtNode extends ExpressionNode {
    private Token token;
    ExpressionNode exprNode;

    
    public ReturnStmtNode (ExpressionNode expr) {
        this.exprNode = expr;
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
        String output = "return";
        output += exprNode.convertToJott();
        output += ';';
        return output;
    }

    static public ReturnStmtNode parseReturnStmtNode(ArrayList<Token> tokens) throws SyntaxException {
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxException("Invalid token ", tokens.get(0));
        }
        if (tokens.get(0).getToken() != "return"){
            throw new SyntaxException("Invalid return: ", tokens.get(0));
        }
        tokens.remove(0);
        ExpressionNode expr = ExpressionNode.parseExpression(tokens);
        if (tokens.get(0).getTokenType() != TokenType.SEMICOLON) {
            throw new SyntaxException("Invalid return statement missing ;", tokens.get(0));
        }
        tokens.remove(0);
        return new ReturnStmtNode(expr);
    }
}