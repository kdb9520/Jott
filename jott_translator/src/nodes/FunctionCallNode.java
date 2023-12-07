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

    public String getType() {
        return this.getReturnType();
    }

    public String getReturnType(){
        return symbolTable.getFunctionReturnType(this.funcName.getToken().getToken());
    }

    public boolean validateTree() throws SemanticException{

        // set current function for params
        this.params.setFunc(this.funcName.getToken().getToken());
        return this.params.validateTree();
    }

    public String convertToJava(String classname) {
        String stringBuilder = "";

        String funcName = this.funcName.getToken().getToken();

        if (funcName.equals("print")) {
            stringBuilder += "System.out.println";
        }
        else if (funcName.equals("concat")) {
            //
        }
        else {
            stringBuilder += this.funcName.convertToJava(classname);
        }
        stringBuilder += "(";
        stringBuilder += this.params.convertToJava(classname);
        stringBuilder += ")";
        return stringBuilder;
    }

    public String convertToPython() {
        String stringBuilder = "";

        stringBuilder += this.funcName.convertToPython();
        stringBuilder += "(";
        stringBuilder += this.params.convertToPython();
        stringBuilder += ")";
        return stringBuilder;
    }

    public String convertToC() {
        String stringBuilder = "";

        String funcName = this.funcName.getToken().getToken();

        if (funcName.equals("print")) {
            return printC(stringBuilder);
        } else if (funcName.equals("concat")) {
            stringBuilder += "concat";
        } else if (funcName.equals("length")) {
            stringBuilder += "strlen";
        } else {
            stringBuilder += this.funcName.convertToC();
        }
        
        stringBuilder += "(";
        stringBuilder += this.params.convertToC();
        stringBuilder += ")";
        return stringBuilder;
    }

    private String printC(String stringBuilder) {
        // convert the params
        String p = this.params.convertToC().trim();

        boolean isStringLit = p.split("\"").length == 2 && p.split("\"")[0].equals("");
        if (isStringLit) {
            return "printf(" + p + ")";
        }
        
        boolean isNumber = Character.isDigit(p.charAt(0));
        if (isNumber) {
            boolean isDouble = p.split(".").length > 1;

            if (isDouble) {
                return "printf(\"%f\", " + p + ")";
            }

            return "printf(\"%d\", " + p + ")";
        }

        boolean isVar = p.split(" ").length == 1;
        boolean isFunc = p.split("\\(").length > 1;
        if (isVar || isFunc) {
            String type;

            if (isFunc) {
                type = symbolTable.getFunctionReturnType(p.split("\\(")[0]);
            } else {
                type = symbolTable.getVarType(p);
            }

            switch (type) {
                case "Integer":
                case "Boolean":
                    return "printf(\"%d\", " + p + ")";
                case "Double":
                    return "printf(\"%f\", " + p + ")";
                case "String":
                    return "printf(\"%s\", " + p + ")";
                case "Void":
                default:
                    return "something broke";
            }
        }

        return stringBuilder;
    }

    public Token getToken() {
        return this.funcName.getToken();
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
    public static FunctionCallNode parseFunctionCallNode(ArrayList<Token> tokenList) throws SyntaxException, SemanticException{
        if (tokenList.get(0).getTokenType() != TokenType.FC_HEADER){
            throw new SyntaxException("Token types don't match", tokenList.get(0));
        }
        tokenList.remove(0);
        IDNode f_name = IDNode.parseIDNode(tokenList);

        // make sure the function exists already
        if (!symbolTable.hasFunc(f_name.getToken().getToken())) {
            String errMsg = "Call to unknown function " + f_name.getToken().getToken();
            throw new SemanticException(errMsg, f_name.getToken());
        }

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