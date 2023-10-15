import java.util.ArrayList;

import provided.Token;
import provided.TokenType;
import provided.JottTree;

// This likely should get changed to a normal Jott Tree, as this is NOT an expressionNode
public class ElifStmtNode implements JottTree {
    private ExpressionNode expr;
    private BodyStmtNode body;
    
    public ElifStmtNode (ExpressionNode expr, BodyStmtNode body) {
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
        String output = "elif[";
        output += expr.convertToJott();
        output  += "]{";
        output += body.convertToJott();
        output += "}";
        return output;
    }

    /// example if stmt token list ["if", "[", "expr", "]", "{", "body", "}", "elif", elif content(handled in elif), "else", else content(handled in else)]

    public static ElifStmtNode parseElifStmtNode(ArrayList<Token> tokenList) throws SyntaxException {
        if (tokenList.get(0).getTokenType() != TokenType.ID_KEYWORD){
            throw new SyntaxException("Token types don't match", tokenList.get(0));
        }
        if (tokenList.get(0).getToken() != "elif"){
            throw new SyntaxException("Token string does not match", tokenList.get(0));
        }
        tokenList.remove(0);
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACKET){
            throw new SyntaxException("Token types is not LBrace", tokenList.get(0));
        }
        tokenList.remove(0);
        ExpressionNode expr = ExpressionNode.parseExpression(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACKET){
            throw new SyntaxException("Token types is not RBrace", tokenList.get(0));
        }
        tokenList.remove(0);
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACE){
            throw new SyntaxException("Token types is not RBrace", tokenList.get(0));
        }
        tokenList.remove(0);
        BodyStmtNode body = BodyStmtNode.parseBodyStmt(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACE){
            throw new SyntaxException("Token types is not RBrace", tokenList.get(0));
        }
        tokenList.remove(0);
        return new ElifStmtNode(expr, body);
    }
}