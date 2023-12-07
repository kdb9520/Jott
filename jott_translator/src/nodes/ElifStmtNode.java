package nodes;
import java.util.ArrayList;

import provided.Token;
import provided.TokenType;
import provided.JottTree;

// This likely should get changed to a normal Jott Tree, as this is NOT an expressionNode
public class ElifStmtNode implements JottTree {
    private ExpressionNode expr;
    private BodyNode body;
    
    public ElifStmtNode (ExpressionNode expr, BodyNode body) {
        this.expr = expr;
        this.body = body;
    }


    // Used as a helper function for IfStmtNode to check if 
    // this elif has a return statement in its body or not
    public ReturnStmtNode getReturnNode(){
        // Remember, this CAN be a null value and that needs to be checked
        return body.getReturnNode();
    }


    public boolean validateTree() throws SemanticException {
        return expr.validateTree() && body.validateTree();
    }

    public String convertToJava(String classname) {
        String output = "else if (";
        output += expr.convertToJava(classname);
        output  += ") {\n";
        output += body.convertToJava(classname) + "\n";
        output += "}\n";
        return output;
    }

    public String convertToPython() {
        String output = "elif ";
        output += expr.convertToPython();
        output  += ": \n";
        output += body.convertToPython() + "\n";
        return output;
    }

    public String convertToC() {
        String output = "else if (";
        output += expr.convertToC();
        output  += ") {\n";
        output += body.convertToC() + "\n";
        output += "}\n";
        return output;
    }

    public String convertToJott() {
        String output = "elseif[";
        output += expr.convertToJott();
        output  += "]{\n";
        output += body.convertToJott() + "\n";
        output += "}\n";
        return output;
    }

    /// example if stmt token list ["if", "[", "expr", "]", "{", "body", "}", "elif", elif content(handled in elif), "else", else content(handled in else)]

    public static ElifStmtNode parseElifStmtNode(ArrayList<Token> tokenList) throws SyntaxException, SemanticException {
        if (tokenList.get(0).getTokenType() != TokenType.ID_KEYWORD){
            throw new SyntaxException("Token types don't match", tokenList.get(0));
        }
        if (!tokenList.get(0).getToken().equals("elseif")){
            throw new SyntaxException("Token string does not match", tokenList.get(0));
        }
        tokenList.remove(0);
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACKET){
            throw new SyntaxException("Token types is not LBracket", tokenList.get(0));
        }
        tokenList.remove(0);
        ExpressionNode expr = ExpressionNode.parseExpression(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACKET){
            throw new SyntaxException("Token types is not RBracket", tokenList.get(0));
        }
        tokenList.remove(0);
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACE){
            throw new SyntaxException("Token types is not LBrace", tokenList.get(0));
        }
        tokenList.remove(0);
        BodyNode body = BodyNode.parseBodyNode(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACE){
            throw new SyntaxException("Token types is not RBrace", tokenList.get(0));
        }
        tokenList.remove(0);
        return new ElifStmtNode(expr, body);
    }
}