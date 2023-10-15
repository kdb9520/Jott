package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

import provided.JottTree;

public class BodyNode implements JottTree {

    private ArrayList<BodyStmtNode> bodyStmts;
    private ReturnStmtNode returnStmt;

    public BodyNode (ArrayList<BodyStmtNode> bodyStmtNodes, ReturnStmtNode returnStmtNode) {
        this.bodyStmts = bodyStmtNodes;
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
        for (BodyStmtNode body : this.bodyStmts) {
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

    public static BodyNode parseBodyNode(ArrayList<Token> tokenList) {
        // This needs to account for the fact that all of the body_stmt's start with an ID_KEYWORD
        // And that the return statement will sometimes be null
        while (tokenList.get(0).getTokenType() != TokenType.)
    }
}
