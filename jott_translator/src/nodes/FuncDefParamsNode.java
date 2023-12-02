package nodes;
import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

import provided.JottTree;

import validate.symbolTable;

public class FuncDefParamsNode implements JottTree {

    private ArrayList<IDNode> paramIds;
    private ArrayList<TypeNode> paramTypes;

    public FuncDefParamsNode (ArrayList<IDNode> idNodes, ArrayList<TypeNode> typeNodes) {
        // <func_def_params> -> <id>: <type> <function_def_params_t>* | epsilon

        this.paramIds = idNodes;
        this.paramTypes = typeNodes;
    }

    public String convertToC() {
        String stringBuilder = "(";

        for (int i = 0; i < this.paramIds.size(); i++) {
            IDNode param = this.paramIds.get(i);
            TypeNode type = this.paramTypes.get(i);

            stringBuilder += type.convertToC() + param.convertToC();
            stringBuilder += ", ";
        }

        stringBuilder = stringBuilder.substring(0, stringBuilder.length()-1);
        stringBuilder += ")";

        return stringBuilder;
    }

    public String convertToJava(String className) {
        String stringBuilder = "(";

        for (int i = 0; i < this.paramIds.size(); i++) {
            IDNode param = this.paramIds.get(i);
            TypeNode type = this.paramTypes.get(i);

            stringBuilder += type.convertToJava(className) + param.convertToJava(className);
            stringBuilder += ", ";
        }

        stringBuilder = stringBuilder.substring(0, stringBuilder.length()-1);
        stringBuilder += ")";

        return stringBuilder;
    }

    public String convertToPython() {
        String stringBuilder = "()";

        for (IDNode idNode : paramIds) {
            stringBuilder += idNode.convertToPython();
            stringBuilder += ", ";
        }
        stringBuilder = stringBuilder.substring(0, stringBuilder.length()-1);
        stringBuilder += ")";

        return stringBuilder;
    }

    public String convertToJott() {
        String output = "";

        for (int i = 0; i < this.paramIds.size(); i++) {
            output += this.paramIds.get(i).convertToJott() + ":" + this.paramTypes.get(i).convertToJott() + ", ";
        }
        if (output.length() != 0) {
            output = output.substring(0, output.length()-2);
        }

        // I don't know if this is actually of the proper format?
        return output;
    }

    public boolean validateTree(){
        // FuncDefParams is what defines the type of the parameters, and thus the errors for it have already been checked
        return true;
    }

    public static FuncDefParamsNode parseFuncDefParamsNode(ArrayList<Token> tokenList) throws SyntaxException {
        ArrayList<IDNode> paramIds = new ArrayList<IDNode>();
        ArrayList<TypeNode> paramTypes = new ArrayList<TypeNode>();

        if (tokenList.get(0).getTokenType() == TokenType.R_BRACKET) {
            return new FuncDefParamsNode(paramIds, paramTypes);
        }

        

        while (true) {
            
            if (tokenList.get(0).getTokenType() != TokenType.ID_KEYWORD) {
                throw new SyntaxException("Token type is not ID or Keyword", tokenList.get(0));
            }
            IDNode id = IDNode.parseIDNode(tokenList);
            paramIds.add(id);
        
            if (tokenList.get(0).getTokenType() != TokenType.COLON) {
                throw new SyntaxException("Token type is not a colon", tokenList.get(0));
            }
            tokenList.remove(0);

            if (tokenList.get(0).getTokenType() != TokenType.ID_KEYWORD || tokenList.get(0).getToken().equals("Void")) {
                throw new SyntaxException("Token type is not ID or Keyword", tokenList.get(0));
            }
            TypeNode type = TypeNode.parseTypeNode(tokenList);
            paramTypes.add(type);

            // Add the parameter to the symbol table using the id and type here 
            symbolTable.addParam(id.getNodeToken().getToken(), type.getNodeToken().getToken());

            if (tokenList.get(0).getTokenType() != TokenType.COMMA) {
                if (tokenList.get(0).getTokenType() != TokenType.R_BRACKET) {
                    throw new SyntaxException("Token type is not Comma", tokenList.get(0));
                } else {
                    break;
                }
            }
            tokenList.remove(0);
        }

        return new FuncDefParamsNode(paramIds, paramTypes);


        // do checks
        // pop tokens
        // repeat
        
        // return new FuncDefParamsNode
    }
}