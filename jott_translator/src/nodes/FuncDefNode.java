package nodes;
import java.util.ArrayList;

import provided.Token;
import provided.TokenType;
import provided.JottTree;

import validate.symbolTable;

public class FuncDefNode implements JottTree {

    private IDNode funcName;
    private FuncDefParamsNode funcDefParams;
    private FunctionReturnNode funcReturn;
    private BodyNode body;

    public FuncDefNode (IDNode f_name, FuncDefParamsNode f_d_p, FunctionReturnNode f_r, BodyNode b) {
        this.funcName = f_name;
        this.funcDefParams = f_d_p;
        this.funcReturn = f_r;
        this.body = b;
    }

    public String convertToC() {
        // TODO
        return "not implemented";
    }

    public String convertToJava(String className) {
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

    public boolean validateTree(){
        // FuncDef is what defines the type of the parameters, and thus the errors for it have already been checked
        return true;
    }

    public static FuncDefNode parseFuncDefNode(ArrayList<Token> tokenList) throws SyntaxException, SemanticException {
        if (tokenList.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxException("Token type is not ID or Keyword", tokenList.get(0));
        }
        if (!tokenList.get(0).getToken().equals("def")) {
            throw new SyntaxException("Token is not keyword 'def'", tokenList.get(0));
        }
        tokenList.remove(0);
        IDNode funcName = IDNode.parseIDNode(tokenList);

        // Add code for adding the function to the symbol table
        Token funcNameToken = funcName.getNodeToken();
        if(symbolTable.hasFunc(funcNameToken.getToken())){
            // If this is true, then throw an error as you're trying to add a duplicate function
            // I'm pretty sure this should cause an error?
            String errMsg = "Attempted to add duplicate function name: " + funcNameToken.getToken();
            throw new SemanticException(errMsg, funcNameToken);
        }
        else if(funcNameToken.getToken().equals("print") ||
        funcNameToken.getToken().equals("concat") ||
        funcNameToken.getToken().equals("length")){
            // If this is true then you are trying to name a function the same as a built in
            // one, which is an error.
            String errMsg = "Attempted to name function same as a builtin function: " + funcNameToken.getToken();
            throw new SemanticException(errMsg, funcNameToken);
        }
        else {
            // If you hit here then you have a function that can be properly added
            symbolTable.addFunc(funcNameToken.getToken());
            
            // Once you've added the function, set the active function here
            symbolTable.setFunc(funcNameToken.getToken());
        }


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
        FunctionReturnNode funcReturn = FunctionReturnNode.parseFunctionReturnNode(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.L_BRACE) {
            throw new SyntaxException("Token type is not Left Brace", tokenList.get(0));
        }
        tokenList.remove(0);
        BodyNode body = BodyNode.parseBodyNode(tokenList);
        if (tokenList.get(0).getTokenType() != TokenType.R_BRACE) {
            throw new SyntaxException("Token type is not Right Brace it's " + tokenList.get(0).getTokenType(), tokenList.get(0));
        }
        tokenList.remove(0);

        return new FuncDefNode(funcName, funcDefParams, funcReturn, body);
    }
}