import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class FunctionReturnNode implements JottTree {

    private IDNode type;

    public FunctionReturnNode(IDNode type, IDNode id) {
        this.type = type;
    }

    static public FunctionReturnNode parseFunctionReturnNode(ArrayList<Token> tokens) throws SyntaxException {
        //todo
        throw new UnsupportedOperationException("Unimplemented method 'parseFunctionReturnNode'");
    }

    @Override
    public String convertToJott() {
        //todo
        throw new UnsupportedOperationException("Unimplemented method 'convertToJott'");
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
