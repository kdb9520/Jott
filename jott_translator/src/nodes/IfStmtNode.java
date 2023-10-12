import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class IfStmtNode extends ExpressionNode {
    private ExpressionNode expr;
    private BodyStmtNode body;
    private ArrayList<ElifStmtNode> elif_nodes;
    private ElseStmtNode el;
    
    public IfStmtNode (ExpressionNode expr, BodyStmtNode body, ArrayList<ElifStmtNode> elif_nodes, ElseStmtNode el) {
        this.expr = expr;
        this.body = body;
        this.elif_nodes = elif_nodes;
        this.el = el;
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
        String output = "if[";
        output += expr.convertToJott();
        output  += "]{";
        output += body.convertToJott();
        output += "}";
        if (elif_nodes.size() != 0) {
            foreach (node in elif_nodes) {
                output += node.convertToJott();
            }
        }
        if (el != null) {
            output += el.convertToJott();
        }
        return output;
    }

    /// example if stmt token list ["if", "[", "expr", "]", "{", "body", "}", "elif", elif content(handled in elif), "else", else content(handled in else)]

    public static IfStmtNode parseIfStmtNode(ArrayList<Token> tokenList) throws SyntaxException {
        ArrayList<ElifStmtNode> elif_nodes = new ArrayList<ElifStmtNode>();
        if (tokenList.get(0).getTokenType() != TokenType.ID_KEYWORD){
            throw new SyntaxException("Token types don't match");
        }
        if (tokenList.get(0).getToken() != "if"){
            throw new SyntaxException("Token string does not match")
        }
        tokenList.remove(0);
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACKET){
            throw new SyntaxException("Token types is not LBrace", tokenList.get(0));
        }
        tokenList.remove(0);
        ExpressionNode expr = ExpressionNode.parseExpression(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACKET){
            throw new SyntaxException("Token types is not RBrace");
        }
        tokenList.remove(0);
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACE){
            throw new SyntaxException("Token types is not RBrace");
        }
        tokenList.remove(0);
        BodyStmtNode body = BodyStmtNode.parseBodyNode(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACE){
            throw new SyntaxException("Token types is not RBrace");
        }
        tokenList.remove(0);
        while ((tokenList.get(0).getTokenType() == TokenType.ID_KEYWORD) && (tokenList.get(0).getToken() == "elif")) {
            ElifStmtNode current_elif = ElifStmtNode.parseElifStmtNode(tokenList);
            elif_nodes.add(current_elif);
        }
        if (tokenList.get(0).getToken() == "else"){
            ElseStmtNode el = ElseStmtNode.parseElseStmtNode(tokenList);
        }
        return new IfStmtNode(expr, body, elif_nodes, el);
    }
}