import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class While_LoopNode implements JottTree {
    private Token token;
    ExpressionNode exprNode;
    BodyNode bodyNode;

    
    public While_LoopNode (ExpressionNode expr, BodyNode body) {
        this.exprNode = expr;
        this.bodyNode = body;
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
        String output = "while";
        output += '[';
        output += exprNode.convertToJott();
        output += "]";
        output += '{';
        output += exprNode.convertToJott();
        output += '}';
        return output;
    }

    static public While_LoopNode parseWhile_LoopNode(ArrayList<Token> tokens) throws SyntaxException {
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxException("Invalid token ", tokens.get(0));
        }
        if (tokens.get(0).getToken() != "while"){
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

        return new While_LoopNode(expr, body);
    }
}