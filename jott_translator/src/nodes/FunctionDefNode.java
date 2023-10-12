import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

import provided.JottTree;

public class FunctionDefNode implements JottTree {

    private IDNode funcName;
    private FuncDefParamsNode funcDefParams;
    private FuncReturnNode funcReturn;
    private BodyNode body;

    public FunctionDefNode (IDNode f_name, FuncDefParamsNode f_d_p, FuncReturnNode f_r, BodyNode b) {
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
        output += "]:";
        output += funcReturn.convertToJott();
        output += '{';
        output += body.convertToJott();
        output += '}';
        return output;
    }

    public static FunctionDefNode parseFunctionDefNode(ArrayList<Token> tokenList) {
        if (tokenList.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxException("Token type is not ID or Keyword", tokenList.get(0));
        }
        tokenList.remove(0);
        IDNode funcName = IDNode.parseIDNode(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACKET) {
            throw new SyntaxException("Token type is not Left Bracket", tokenList.get(0));
        }
        tokenList.remove(0);
        FuncDefParamsNode funcDefParams = FuncDefParamsNode.parseFuncDefParamsNode(tokenList); // maybe
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACKET) {
            throw new SyntaxException("Token type is not Right Bracket", tokenList.get(0));
        }
        tokenList.remove(0);
        if (tokenList.get(0).getTokenType() != TokenType.COLON) {
            throw new SyntaxException("Token type is not Colon", tokenList.get(0));
        }
        tokenList.remove(0);
        FuncReturnNode funcReturn = FuncReturnNode.parseFunctionReturnNode(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACE) {
            throw new SyntaxException("Token type is not Left Brace", tokenList.get(0));
        }
        tokenList.remove(0);
        BodyNode body = BodyNode.parseBodyNode(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACE) {
            throw new SyntaxException("Token type is not Right Brace", tokenList.get(0));
        }
        tokenList.remove(0);

        return new FunctionDefNode(funcName, funcDefParams, funcReturn, body);
    }
}