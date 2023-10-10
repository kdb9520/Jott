import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class FunctionDefNode implements JottTree {

    private String funcName;
    private String funcDefParams;
    private String funcReturn;
    private String body;

    public FunctionDefNode (IDNode f_name, FunctionDefParamsNode f_d_p, FunctionReturnNode f_r, BodyNode b) {
        this.funcName = f_name;
        this.funcDefParams = f_d_p;
        this.funcReturn = f_r;
        this.body = b;
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
        String output = "def ";
        output += funcName.convertToJott();
        output += '[';
        output += funcDefParams.convertToJott();
        output += ']:';
        output += funcReturn.convertToJott();
        output += '{';
        output += body.convertToJott();
        output += '}'
        return output;
    }

    public String parseFunctionDefNode(ArrayList<Token> tokenList) {
        if (tokenList.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxException("Token type is not ID or Keyword");
        }
        tokenList.remove(0);
        funcName = IDNode.parseIDNode(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACKET) {
            throw new SyntaxException("Token type is not Left Bracket");
        }
        tokenList.reomve(0);
        funcDefParams = FunctionDefParamsNode.parseFunctionDefParamsNode(tokenList); // maybe
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACKET) {
            throw new SyntaxException("Token type is not Right Bracket");
        }
        tokenList.remove(0);
        if (tokenList.get(0).getTokenType() != TokenType.COLON) {
            throw new SyntaxException("Token type is not Colon");
        }
        tokenList.remove(0);
        funcReturn = FunctionReturnNode.parseFunctionReturnNode(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACE) {
            throw new SyntaxException("Token type is not Left Brace");
        }
        tokenList.remove(0);
        body = BodyNode.parseBodyNode(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACE) {
            throw new SyntaxException("Token type is not Right Brace");
        }
        tokenList.remove(0);

        return new FunctionDefNode(funcName, funcDefParams, funcReturn, body);
    }
}