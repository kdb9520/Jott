package nodes;
import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

import provided.JottTree;

public class FuncDefParamsNode implements JottTree {

    private ArrayList<IDNode> paramIds;
    private ArrayList<TypeNode> paramTypes;

    public FuncDefParamsNode (ArrayList<IDNode> idNodes, ArrayList<TypeNode> typeNodes) {
        // <func_def_params> -> <id>: <type> <function_def_params_t>* | epsilon

        this.paramIds = idNodes;
        this.paramTypes = typeNodes;
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
        String output = "";

        for (int i = 0; i < this.paramIds.size(); i++) {
            output += this.paramIds.get(i) + ":" + this.paramTypes.get(i) + ", ";
        }
        if (output.length() != 0) {
            output = output.substring(0, output.length()-2);
        }

        // I don't know if this is actually of the proper format?
        return output;
    }

    public boolean validateTree(){
        // TODO
        return false;
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

            if (tokenList.get(0).getTokenType() != TokenType.ID_KEYWORD) {
                throw new SyntaxException("Token type is not ID or Keyword", tokenList.get(0));
            }
            TypeNode type = TypeNode.parseTypeNode(tokenList);
            paramTypes.add(type);

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