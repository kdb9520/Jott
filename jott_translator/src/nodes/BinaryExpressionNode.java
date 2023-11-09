package nodes;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import provided.Token;
import provided.TokenType;



public class BinaryExpressionNode extends ExpressionNode {
    // This class exists mostly to be used with ExpressionNode for the case where 
    // you have a operator present. 

    // Static sets of operators used during validate tree
    static Set<String> mathOpSymbols = new HashSet<>(Arrays.asList("*", "/", "+", "-"));
    static Set<String> relOpSymbols = new HashSet<>(Arrays.asList(">", ">=", "<", "<=", "==", "!="));

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

    public String getType() {
        return leftNode.getType();
    }

    public boolean validateTree() throws SemanticException {
        // Check for division by 0
        if(opToken.getToken().equals("/") && rightNode.getToken().getToken().equals("0")){
            // If this is the case then you're attempting to divide by zero, so error out
            String errMsg = "Attempted to divide by Zero.";
            throw new SemanticException(errMsg, rightNode.getToken());
        }

        // Check for types not being the same
        if (!this.leftNode.getType().equals(this.rightNode.getType())) {
            // this is a case where the left and right node do not have the same type
            String errMsg = "Attempted Binary Operation with different types.";
            throw new SemanticException(errMsg, rightNode.getToken());
        }
        
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
