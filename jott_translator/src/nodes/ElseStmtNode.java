import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class ElseStmtNode extends ExpressionNode {
    private ExpressionNode expr;
    private BodyStmtNode body;
    
    public ElseStmtNode (ExpressionNode expr, BodyStmtNode body) {
        this.expr = expr;
        this.body = body;
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
        String output = "else[";
        output += expr.convertToJott();
        output  += "]{";
        output += body.convertToJott();
        output += "}";
        return output;
    }

    /// example if stmt token list ["if", "[", "expr", "]", "{", "body", "}", "elif", elif content(handled in elif), "else", else content(handled in else)]

    public static parseElseStmtNode(ArrayList<> tokenList):
        if (tokenList.get(0).getTokenType() != TokenType.ID_KEYWORD){
            throw new SyntaxException("Token types don't match");
        }
        if (tokenList.get(0).getToken() != "else"){
            throw new SyntaxException("Token string does not match")
        }
        tokenList.pop();
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACKET){
            throw new SyntaxException("Token types is not LBrace", tokenList.get(0));
        }
        tokenList.pop();
        ExpressionNode expr = ExpressionNode.parseExpressionNode(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACKET){
            throw new SyntaxException("Token types is not RBrace");
        }
        tokenList.pop(0);
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACE){
            throw new SyntaxException("Token types is not RBrace");
        }
        tokenList.pop(0);
        BodyStmtNode body = BodyStmtNode.parseBodyNode(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACE){
            throw new SyntaxException("Token types is not RBrace");
        }
        tokenList.pop(0);
        return new ElseStmtNode(expr, body);
}