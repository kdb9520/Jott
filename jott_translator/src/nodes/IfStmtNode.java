package nodes;
import java.util.ArrayList;

import provided.Token;
import provided.TokenType;
import validate.symbolTable;

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

    public boolean validateTree() throws SemanticException {
        // First, check if the if's body has a return statement in it 
        ReturnStmtNode bodyReturn = body.getReturnNode();
        boolean ifHasReturn = false;
        // This will sum up the amount of elifNodes with a return 
        // When ifHasReturn is false, this needs to be 0.
        // When ifHasReturn is true, this needs to be equal to elif_nodes.length()
        int elifReturnCount = 0;
        if(bodyReturn != null){
            // What I need to do here is set some sort of flag that says if the if has a return.
            // When the if has a return, all elifs and else must have one and an else must exist.
            // When the if doesn't have a return, NONE of the elif/else can have a return.
            ifHasReturn = true;
        }

        for (ElifStmtNode node : elif_nodes) {
            if (!node.validateTree()) {
                return false;
            }
            // Also check to see if it has a body with a return
            ReturnStmtNode currentReturn = node.getReturnNode();
            if(currentReturn != null){
                // If this is the case then increment
                elifReturnCount++;
            }
        }

        // Now, branch based on if there is an else statement
        if(el == null){
            // Means that you have no else statement.  So check if there are any returns
            if(ifHasReturn || elifReturnCount > 0){
                // If either of the above is true, then you have an error
                String errString = "If block contains a return statement without an else with a return statement.";
                // This assumes expr.getToken() is implemented
                throw new SemanticException(errString, expr.getToken());
            }

            // Otherwise, just return the validate results of the children
            return expr.validateTree() && body.validateTree();
        }
        else {
            // Means there is an else statement, so check to make sure the returns properly exist.
            ReturnStmtNode elseReturn = el.getReturnNode();

            if(ifHasReturn && elifReturnCount == elif_nodes.size() && elseReturn != null){
                // If all of the above is true, then you can return as the returns are properly formatted.
                return expr.validateTree() && body.validateTree() && el.validateTree();
            }
            else{
                // In this case not all of the if/elif/else have a return statement, so error
                String errString = "Not all if/elif/else have a return statement.";
                throw new SemanticException(errString, expr.getToken());
            }
        }
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
        // Increment the while depth before adjusting the body node
        symbolTable.incrementIfWhileDepth();
        
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

        // Decrement the depth since you're at the end of the if
        symbolTable.decrementIfWhileDepth();
        return new IfStmtNode(expr, body, elif_nodes, elseStmt);
    }
}