package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;
import validate.symbolTable;

public class ParamsNode implements JottTree {
    private ArrayList<ExpressionNode> exprNodes = new ArrayList<>();
    public ParamsNode (ArrayList<ExpressionNode> exprs) {
        this.exprNodes = exprs;
    }

    public boolean validateTree() throws SemanticException {
        // The issue here seems to be that if there's something that breaks when you try to 
        ArrayList<String> params = symbolTable.getParamTypes();
        for (int i = 0; i < exprNodes.size(); i++) {
            String exprType = exprNodes.get(i).getType();
            if (!exprType.equals(params.get(i))){
                throw new SemanticException("Invalid param type.", exprNodes.get(i).getToken());
            }
        }
        return true;
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

    public static ParamsNode parse_ParamsNode(ArrayList<Token> tokenList) throws SyntaxException, SemanticException {
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