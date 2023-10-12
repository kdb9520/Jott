import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class VarDecNode implements JottTree {

    private IDNode type;
    private IDNode id;

    public VarDecNode(IDNode type, IDNode id) {
        this.type = type;
        this.id = id;
    }

    static public VarDecNode parseVarDecNode(ArrayList<Token> tokens) throws SyntaxException {
        IDNode typeToken = IDNode.parseIDNode(tokens);
        IDNode idToken = IDNode.parseIDNode(tokens);

        if (tokens.get(0).getTokenType() != TokenType.SEMICOLON) {
            throw new SyntaxException("Variable Declaration missing semicolon.", tokens.get(0));
        }

        return new VarDecNode(typeToken, idToken);
    }

    @Override
    public String convertToJott() {
        return this.type.convertToJott() + " " + this.id.convertToJott() + ";";
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
