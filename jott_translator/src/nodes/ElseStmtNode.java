package nodes;
import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class ElseStmtNode implements JottTree {
    private BodyNode body;
    
    public ElseStmtNode (BodyNode body) {
        this.body = body;
    }

    // Used as a helper function for IfStmtNode to check if 
    // this else has a return statement in its body or not
    public ReturnStmtNode getReturnNode(){
        // Remember, this CAN be a null value and that needs to be checked
        return body.getReturnNode();
    }

    public boolean validateTree() throws SemanticException {
        return body.validateTree();
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
        String output = "else";
        output  += " {\n";
        output += body.convertToJott();
        output += "}\n";
        return output;
    }

    /// example if stmt token list ["if", "[", "expr", "]", "{", "body", "}", "elif", elif content(handled in elif), "else", else content(handled in else)]

    public static ElseStmtNode parseElseStmtNode(ArrayList<Token> tokenList) throws SyntaxException, SemanticException{
        if (tokenList.get(0).getTokenType() != TokenType.ID_KEYWORD){
            throw new SyntaxException("Token types don't match", tokenList.get(0));
        }
        if (!tokenList.get(0).getToken().equals("else")){
            throw new SyntaxException("Token string does not match", tokenList.get(0));
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
        return new ElseStmtNode(body);
    }
}