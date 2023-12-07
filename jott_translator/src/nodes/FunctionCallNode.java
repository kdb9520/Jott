package nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            String concatParams = params.convertToJava(classname);
            List<String> newParams = Arrays.asList(concatParams.split(","));
            // take each node and add them
            stringBuilder += newParams.get(0).trim();
            stringBuilder += ".concat(";
            stringBuilder += newParams.get(1).trim();
            // Add the ending parens given you're returning immediately
            stringBuilder += ")";
            return stringBuilder;
        }
        else if (funcName.equals("length")){
            // Need to do something similar to concat, as the format is
            // String.length()
            // First, grab the params, then get the first one to get the string to get the length of
            String concatParams = params.convertToJava(classname);
            List<String> newParams = Arrays.asList(concatParams.split(","));
            stringBuilder += newParams.get(0).trim();
            stringBuilder += ".length()";
            return stringBuilder;
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
        String funcName = this.funcName.getToken().getToken();
        if (funcName.equals("concat")) {
            // convert all params to expr nodes
            String concatParams = params.convertToPython();
            List<String> newParams = Arrays.asList(concatParams.split(","));
            // take each node and add them
            for (int s = 0; s < newParams.size(); s++){
                stringBuilder += newParams.get(s).trim();
                if (s + 1 < newParams.size()){
                    stringBuilder += " + ";
                }
            } 
            return stringBuilder;
        }
        else if (funcName.equals("length")) {
            stringBuilder += "len";
            stringBuilder += "(";
            stringBuilder += this.params.convertToPython();
            stringBuilder += ")";                
            return stringBuilder;
        }
        else{
            stringBuilder += this.funcName.convertToPython();
            stringBuilder += "(";
            stringBuilder += this.params.convertToPython();
            stringBuilder += ")";
        }
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
            p = p.substring(0, p.length() - 1);
            p += "\\n\"";
            return "printf(" + p + ")";
        }
        
        boolean isNumber = Character.isDigit(p.charAt(0));
        if (isNumber) {
            boolean isDouble = p.split(".").length > 1;

            if (isDouble) {
                return "printf(\"%f\\n\", " + p + ")";
            }

            return "printf(\"%d\\n\", " + p + ")";
        }

        boolean isVar = p.split(" ").length == 1;
        boolean isFunc = p.split("\\(").length > 1;
        if (isVar || isFunc) {
            String type;

            if (isFunc) {
                String funcName = p.split("\\(")[0];
                if (funcName.equals("strlen")) {
                    type = "Integer";
                } else if (funcName.equals("concat")) {
                    type = "String";
                } else {
                    type = symbolTable.getFunctionReturnType(funcName);
                }
            } else {
                if (symbolTable.hasVar(p)) { type = symbolTable.getVarType(p); }
                // error here bc params aren't being picked up for some reason
                else { type = "None"; }
            }

            switch (type) {
                case "Integer":
                case "Boolean":
                    return "printf(\"%d\\n\", " + p + ")";
                case "Double":
                    return "printf(\"%f\\n\", " + p + ")";
                case "String":
                    return "printf(\"%s\\n\", " + p + ")";
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