package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import validate.symbolTable;

public class FunctionReturnNode implements JottTree {

    private TypeNode type;

    public FunctionReturnNode(TypeNode type) {
        this.type = type;
    }

    static public FunctionReturnNode parseFunctionReturnNode(ArrayList<Token> tokens) throws SyntaxException {
        TypeNode type = TypeNode.parseTypeNode(tokens);
        symbolTable.addVar("Return", type.getNodeToken().getToken());
        return new FunctionReturnNode(type);
    }

    @Override
    public String convertToJott() {
        return this.type.convertToJott();
    }

    @Override
    public String convertToJava(String className) {
        return this.type.convertToJava(className);
    }

    @Override
    public String convertToC() {
        return this.type.convertToC();
    }

    @Override
    public String convertToPython() {
        return this.type.convertToPython();
    }

    @Override
    public boolean validateTree() {
        // we always have a return type or void
        return true;
    }
    
}
