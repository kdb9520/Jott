import java.util.ArrayList;

import provided.JottTree;
import provided.Token;

public class FunctionReturnNode implements JottTree {

    private TypeNode type;

    public FunctionReturnNode(TypeNode type) {
        this.type = type;
    }

    static public FunctionReturnNode parseFunctionReturnNode(ArrayList<Token> tokens) throws SyntaxException {
        TypeNode type = TypeNode.parseTypeNode(tokens);
        return new FunctionReturnNode(type);
    }

    @Override
    public String convertToJott() {
        return this.type.convertToJott();
    }

    @Override
    public String convertToJava(String className) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToJava'");
    }

    @Override
    public String convertToC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToC'");
    }

    @Override
    public String convertToPython() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToPython'");
    }

    @Override
    public boolean validateTree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }
    
}
