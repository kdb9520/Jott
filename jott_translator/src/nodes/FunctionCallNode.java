import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class FunctionCallNode extends ExpressionNode implements BodyStmtNode {
    private IDNode funcName;
    private FuncParamNode params;
    
    public FunctionCallNode (IDNode f_name, FuncParamNode f_p) {
        this.funcName = f_name;
        this.params = f_p;
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
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACE){
            throw new SyntaxException("Token types is not LBrace", tokenList.get(0));
        }
        tokenList.remove(0);
        FuncParamNode f_p = FuncParamNode.parseFuncParamNode(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACE){
            throw new SyntaxException("Token types is not RBrace", tokenList.get(0));
        }
        tokenList.remove(0);
        return new FunctionCallNode(f_name, f_p);
    }
}