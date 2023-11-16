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

        // check if id is a keyword
        if (symbolTable.isKeyword(currentIdToken.getToken())) {
            String errMsg = "Invalid variable name: " + currentIdToken.getToken();
            throw new SemanticException(errMsg + "\nVariable name cannot be a keyword", currentIdToken);
        }

        // vars cannot start with a capital letter
        if (Character.isUpperCase(currentIdToken.getToken().charAt(0))) {
            String errMsg = "Invalid variable name: cannot start with a capital letter";
            throw new SemanticException(errMsg, currentIdToken);
        }

        if(!symbolTable.hasVar(currentIdToken.getToken())){

            // Check to make sure you're not in an if/while loop
            if(symbolTable.getIfWhileDepth() != 0){
                // If this is true then error out because you tried to declare 
                // inside of an if/while
                String errMsg = "Attempted to declare variable inside of an if or while loop, which isn't allowed";
                throw new SemanticException(errMsg, currentIdToken);
            }

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
        } else {
            tokens.remove(0);
        }

        return new VarDecNode(typeToken, idToken);
    }

    @Override
    public String convertToJott() {
        return this.type.convertToJott() + " " + this.id.convertToJott() + ";\n";
    }

    @Override
    public String convertToJava(String className) {
        // type id;
        return this.type.convertToJava(className) +  this.id.convertToJava(className) + ";";
    }

    @Override
    public String convertToC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToC'");
    }

    @Override
    public String convertToPython() {
        // can't have a declaration without assignment in python
        return this.id.convertToPython();
    }

    @Override
    public boolean validateTree() throws SemanticException {

        // Justin: pretty sure the current validateTree implementation doesn't make sense, since varDec isn't actually assigning to a right side
        // String leftTypeString = this.type.toString();
        // String rightTypeString = this.id.getType();
        
        // if (!leftTypeString.equals(symbolTable.getVarType(rightTypeString))) {
        //     throw new SemanticException("Left side has different type than right side", this.id.getToken());    
        // }

        if (!this.type.validateTree()) {
            throw new SemanticException("Invalid type node in Var Dec Node", "");
        }
        if (!this.id.validateTree()) {
            throw new SemanticException("Invalid ID node in var dec node", "");
        }

        return true;
    }
    
}
