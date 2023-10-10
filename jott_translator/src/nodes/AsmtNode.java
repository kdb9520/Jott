import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class AsmtNode implements JottTree {
    
    static public AsmtNode parseAsmtNode(ArrayList<Token> tokens) throws SyntaxException {

        if (tokens.get(0).getTokenType() != TokenType.ASSIGN) {
            throw new SyntaxException("Assignment Node does not have valid token type.", tokens.get(0));
        } 

        

    }

    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
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
