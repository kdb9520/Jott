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
        ArrayList<ExpressionNode> exprs = new ArrayList<>();
        exprs.add(ExpressionNode.parseExpression(tokenList));
        while(tokenList.get(0).getTokenType() != TokenType.COMMA){
             if (tokenList.get(0).getTokenType() != TokenType.COMMA){
                    throw new SyntaxException("Params Node does not have valid token Type", tokenList.get(0));
                }
        tokenList.remove(0);
        ExpressionNode expr = ExpressionNode.parseExpression(tokenList);
        exprs.add(expr);
        tokenList.remove(0);
        }
        return new ParamsNode(exprs);
    }
}