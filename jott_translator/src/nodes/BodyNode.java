package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

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
        }
        if(this.returnStmt != null){
            String returnString = this.returnStmt.convertToJott();
            output +=  " " + returnString;
        }
        return output;
    }

    public boolean validateTree() {
        // TODO
        return false;
    }

    public static BodyNode parseBodyNode(ArrayList<Token> tokenList) throws SyntaxException {
        // This needs to account for the fact that all of the body_stmt's start with an ID_KEYWORD
        // And that the return statement will sometimes be null
        ArrayList<BodyStmtNode> bodyStmtNodes = new ArrayList<BodyStmtNode>();
        while ((tokenList.get(0).getTokenType() == TokenType.ID_KEYWORD) && !(tokenList.get(0).getToken().equals("return"))) {
            BodyStmtNode curr_BodyStmtNode = BodyStmtNode.parseBodyStmt(tokenList);
            bodyStmtNodes.add(curr_BodyStmtNode);
            tokenList.remove(0); // JUSTIN have to remove semi-colon after body statment
        }
        if ((tokenList.get(0).getTokenType() == TokenType.ID_KEYWORD) && (tokenList.get(0).getToken().equals("return"))) {
            ReturnStmtNode returnStmtNode = ReturnStmtNode.parseReturnStmtNode(tokenList);
            return new BodyNode(bodyStmtNodes, returnStmtNode);
        }
        return new BodyNode(bodyStmtNodes, null);
    }
}
