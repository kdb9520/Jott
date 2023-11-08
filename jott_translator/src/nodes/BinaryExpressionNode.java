package nodes;

import provided.Token;
import provided.TokenType;



public class BinaryExpressionNode extends ExpressionNode {
    // This class exists mostly to be used with ExpressionNode for the case where 
    // you have a operator present. 

    // These nodes are the left and right side of the expression respectively 
    ExpressionNode leftNode;
    ExpressionNode rightNode;

    // This is the token holding the text of the operator in the center 
    Token opToken;

    public BinaryExpressionNode(ExpressionNode left, ExpressionNode right, Token operator){
        this.leftNode = left;
        this.rightNode = right;
        this.opToken = operator;
    }

    public TokenType getTokenType() {
        return leftNode.getTokenType();
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

    public Token getToken() {
        // return this.token;
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }

    public String convertToJott(){
        // This mostly just needs to call the convert to Jott of it's components
        String stringToReturn = "";
        // First, get the left side, then the operator, then the right
        stringToReturn = stringToReturn.concat(leftNode.convertToJott());
        stringToReturn = stringToReturn.concat(opToken.getToken());
        stringToReturn = stringToReturn.concat(rightNode.convertToJott());

        return stringToReturn;
    }
}
