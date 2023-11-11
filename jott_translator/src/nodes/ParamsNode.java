package nodes;

import java.util.ArrayList;
import java.util.Arrays;

import provided.JottTree;
import provided.Token;
import provided.TokenType;
import validate.symbolTable;

public class ParamsNode implements JottTree {
    private ArrayList<ExpressionNode> exprNodes = new ArrayList<>();
    private String functionName;
    public ParamsNode (ArrayList<ExpressionNode> exprs) {
        this.exprNodes = exprs;
    }

    public boolean validateTree() throws SemanticException {
        if (this.functionName.equals("print")){ 
            if (exprNodes.size() != 1){
                throw new SemanticException("Invalid number of params provided for function print. ", exprNodes.get(0).getToken());
            }
            ArrayList<String> validExprs = new ArrayList<>(Arrays.asList("String", "Integer", "Double", "Boolean"));
            if (!validExprs.contains(exprNodes.get(0).getType())){
                throw new SemanticException("Invalid Expression Type: ", exprNodes.get(0).getToken());
            }
        }
        else{
        ArrayList<String> params = symbolTable.getParamTypesCurrentFunction(this.functionName);
        int expected_param_count = symbolTable.getParamCountForGiven(this.functionName);
        int actual_param_count = params.size();
        if(expected_param_count != actual_param_count) {
            throw new SemanticException("Number of params given does not equal number of params expected", "");
        }
            for (int i = 0; i < exprNodes.size(); i++) {
                String exprType = exprNodes.get(i).getType();
                if (!exprType.equals(params.get(i))){
                    throw new SemanticException("Invalid param type.", exprNodes.get(i).getToken());
                }
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

    public void setFunc(String funcName){
        this.functionName = funcName;
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