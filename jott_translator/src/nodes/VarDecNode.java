package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

import validate.symbolTable;

public class VarDecNode implements BodyStmtNode {

    private TypeNode type;
    private IDNode id;

    public VarDecNode(TypeNode type, IDNode id) {
        this.type = type;
        this.id = id;
    }

    static public VarDecNode parseVarDecNode(ArrayList<Token> tokens) throws SyntaxException, SemanticException {
        TypeNode typeToken = TypeNode.parseTypeNode(tokens);
        IDNode idToken = IDNode.parseIDNode(tokens);

        // Additionally code to add the current variable to the symbolTable
        Token currentIdToken = idToken.getNodeToken();
        Token currentTypeToken = typeToken.getNodeToken();
        if(!symbolTable.hasVar(currentIdToken.getToken())){
            // If this is the case then add the variable to the symbol table.
            symbolTable.addVar(currentIdToken.getToken(), currentTypeToken.getToken());
        }
        else {
            // If this is the case then you have a Semantic Error as you're trying to 
            // redeclare the variable.
            String errorMsg = "Attempted to redeclare already declared variable: " + currentIdToken.getToken();
            throw new SemanticException(errorMsg, currentIdToken);
        }

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
