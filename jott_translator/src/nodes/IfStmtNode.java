package nodes;
import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class IfStmtNode implements BodyStmtNode {
    private ExpressionNode expr;
    private BodyNode body;
    private ArrayList<ElifStmtNode> elif_nodes;
    private ElseStmtNode el;
    
    public IfStmtNode (ExpressionNode expr, BodyNode body, ArrayList<ElifStmtNode> elif_nodes, ElseStmtNode el) {
        this.expr = expr;
        this.body = body;
        this.elif_nodes = elif_nodes;
        this.el = el;
    }

    public boolean validateTree() {
        for (ElifStmtNode node : elif_nodes) {
            if (!node.validateTree()) {
                return false;
            }
        }
        return expr.validateTree() && body.validateTree() && el.validateTree();
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
        String output = "if[";
        output += expr.convertToJott();
        output  += "]{";
        output += body.convertToJott();
        output += "}";
        if (elif_nodes.size() != 0) {
            for (int nodeIndex = 0; nodeIndex < elif_nodes.size(); nodeIndex++){
                ElifStmtNode node = elif_nodes.get(nodeIndex);
                output += node.convertToJott();
            }
        }
        if (el != null) {
            output += el.convertToJott();
        }
        return output;
    }

    /// example if stmt token list ["if", "[", "expr", "]", "{", "body", "}", "elif", elif content(handled in elif), "else", else content(handled in else)]

    public static IfStmtNode parseIfStmtNode(ArrayList<Token> tokenList) throws SyntaxException, SemanticException {
        ArrayList<ElifStmtNode> elif_nodes = new ArrayList<ElifStmtNode>();
        if (tokenList.get(0).getTokenType() != TokenType.ID_KEYWORD){
            throw new SyntaxException("Token types don't match", tokenList.get(0));
        }
        if (!tokenList.get(0).getToken().equals("if")){
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
        while ((tokenList.get(0).getTokenType() == TokenType.ID_KEYWORD) && (tokenList.get(0).getToken().equals("elseif"))) {
            ElifStmtNode current_elif = ElifStmtNode.parseElifStmtNode(tokenList);
            elif_nodes.add(current_elif);
        }

        ElseStmtNode elseStmt = null;
        if (tokenList.get(0).getToken().equals("else")){
            elseStmt = ElseStmtNode.parseElseStmtNode(tokenList);
        }
        // There needs to be a way to handle the else statement here if it does not exist.
        // If it is null then treat it as not existing
        return new IfStmtNode(expr, body, elif_nodes, elseStmt);
    }
}