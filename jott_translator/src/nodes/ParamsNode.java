package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class ParamsNode implements JottTree {
    private ArrayList<ExpressionNode> exprNodes = new ArrayList<>();
    public ParamsNode (ArrayList<ExpressionNode> exprs) {
        this.exprNodes = exprs;
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
        String output = "";
        while (this.exprNodes.size() > 0){
            output += this.exprNodes.remove(0).convertToJott();
            if (this.exprNodes.size() > 0){
                output  += ", ";
            }
        }
        return output;
    }

    public static ParamsNode parse_ParamsNode(ArrayList<Token> tokenList) throws SyntaxException{
        // Make empty list of expressions
        ArrayList<ExpressionNode> exprs = new ArrayList<>();
        // Check to see if there are any params 
        if (tokenList.get(0).getTokenType() == TokenType.R_BRACKET) {
            return new ParamsNode(exprs);
        }
        exprs.add(ExpressionNode.parseExpression(tokenList));
        while(tokenList.get(0).getTokenType() == TokenType.COMMA){
            tokenList.remove(0);
            exprs.add(ExpressionNode.parseExpression(tokenList));
        }
        return new ParamsNode(exprs);
    }
}