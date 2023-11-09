package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;
import validate.symbolTable;
import provided.JottTree;

public class BodyNode implements JottTree {

    private ArrayList<BodyStmtNode> bodyStmtNodes;
    private ReturnStmtNode returnStmt;

    public BodyNode (ArrayList<BodyStmtNode> bodyStmtNodes, ReturnStmtNode returnStmtNode) {
        this.bodyStmtNodes = bodyStmtNodes;
        this.returnStmt = returnStmtNode;
    }

    public String convertToC() {
        // TODO
        return "not implemented";
    }

    public String convertToJava(String className) {
        // TODO
        return "not implemented";
    }

    public String convertToPython() {
        // TODO
        return "not implemented";
    }

    public String convertToJott() {
        String output = "";
        for (BodyStmtNode body : this.bodyStmtNodes) {
            output += body.convertToJott();
            if(body instanceof FunctionCallNode){
                // This means you'd need to append a semicolon 
                output += ";";
            }
        }
        if(this.returnStmt != null){
            String returnString = this.returnStmt.convertToJott();
            output +=  " " + returnString;
        }
        return output;
    }

    public boolean validateTree() throws SemanticException {
        if (symbolTable.getWhileDepth() > 0) {
            if (this.returnStmt != null) {
                String errMsg = "Invalid return from While Loop.";
                throw new SemanticException(errMsg, this.returnStmt.getToken());
            }
        }
        if (this.returnStmt == null){
            if (!symbolTable.getVarType("Return").equals("Void")){
                throw new SemanticException("Missing return statement in non-void function", null);
            }
        }
        for (BodyStmtNode bodyStmtNode : bodyStmtNodes) {
            if (!bodyStmtNode.validateTree()) {
                return false;
            }
        }

        return this.returnStmt.validateTree();
    }

    public static BodyNode parseBodyNode(ArrayList<Token> tokenList) throws SyntaxException, SemanticException {
        // This needs to account for the fact that all of the body_stmt's start with an ID_KEYWORD
        // And that the return statement will sometimes be null
        ArrayList<BodyStmtNode> bodyStmtNodes = new ArrayList<BodyStmtNode>();
        while (((tokenList.get(0).getTokenType() == TokenType.ID_KEYWORD) && !(tokenList.get(0).getToken().equals("return"))) ||
        (tokenList.get(0).getTokenType() == TokenType.FC_HEADER)) {
            BodyStmtNode curr_BodyStmtNode = BodyStmtNode.parseBodyStmt(tokenList);
            bodyStmtNodes.add(curr_BodyStmtNode);
        }
        if ((tokenList.get(0).getTokenType() == TokenType.ID_KEYWORD) && (tokenList.get(0).getToken().equals("return"))) {
            ReturnStmtNode returnStmtNode = ReturnStmtNode.parseReturnStmtNode(tokenList);
            return new BodyNode(bodyStmtNodes, returnStmtNode);
        }
        return new BodyNode(bodyStmtNodes, null);
    }
}
