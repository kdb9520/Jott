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

    public String convertToJava() {
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
        String returnString = this.returnStmt.convertToJott();
        output +=  " " + returnString;
        return output;
    }

    public static BodyNode parseBodyNode(ArrayList<Token> tokenList) {
        while (tokenList.get(0).getTokenType() != TokenType.)
    }
}
