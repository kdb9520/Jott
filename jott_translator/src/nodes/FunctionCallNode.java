package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;
import validate.symbolTable;

public class FunctionCallNode extends ExpressionNode implements BodyStmtNode {
    private IDNode funcName;
    // private FuncDefParamsNode params;
    // should be ParamsNode
    ParamsNode params;
    
    public FunctionCallNode (IDNode f_name, ParamsNode f_p) { // same thing should be ParamsNode
        this.funcName = f_name;
        this.params = f_p;
    }

    public TokenType getTokenType() {
        // TODO: This might have to return the return
        //  type of the function from the symtable
        // String returnType = getReturnType();
        return null;
    }

    public String getReturnType(){
        symbolTable.setFunc(this.funcName.getToken().getToken());
        return symbolTable.getVarType("Return");
    }

    public boolean validateTree() {
        symbolTable.setFunc(this.funcName.getToken().getToken());
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
        // todo: idk 
        // return this.token;
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }

    public String convertToJott() {
        String output = "::";
        output += funcName.convertToJott();
        output  += "[";
        output += params.convertToJott();
        output += "]";
        return output;
    }

    /*
     * The parse functions exist to check if the list of tokens has the needed tokens to 
     * create a valid Node, in this case being a valid functionCallNode
     * 
     */
    public static FunctionCallNode parseFunctionCallNode(ArrayList<Token> tokenList) throws SyntaxException{
        if (tokenList.get(0).getTokenType() != TokenType.FC_HEADER){
            throw new SyntaxException("Token types don't match", tokenList.get(0));
        }
        tokenList.remove(0);
        IDNode f_name = IDNode.parseIDNode(tokenList);
        // if (tokenList.get(0).getTokenType() != TokenType.L_BRACE){
        //     throw new SyntaxException("Token types is not LBrace", tokenList.get(0));
        // }
        // I think it should be bracket ???
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACKET){
            throw new SyntaxException("Token types is not LBracket", tokenList.get(0));
        }
        tokenList.remove(0);
        // FuncDefParamsNode f_p = FuncDefParamsNode.parseFuncDefParamsNode(tokenList);
        // should be calling parseParamsNode
        ParamsNode f_p = ParamsNode.parse_ParamsNode(tokenList);
        // if (tokenList.get(0).getTokenType() != TokenType.R_BRACE){
        //     throw new SyntaxException("Token types is not RBrace", tokenList.get(0));
        // }
        // same thing should be bracket
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACKET){
            throw new SyntaxException("Token types is not RBracket", tokenList.get(0));
        }
        tokenList.remove(0);
        return new FunctionCallNode(f_name, f_p);
    }
}