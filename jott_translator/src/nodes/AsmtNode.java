package nodes;

/**
 * This class handles the Assignment Node functionality from the Jott grammar.
 * Deals with the cases where you have an id or type with look ahead.  
 * 
 * @author Max O'Malley 
 */

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

import validate.symbolTable;

public class AsmtNode implements BodyStmtNode {
    
    private TypeNode type;
    private IDNode varName;
    private ExpressionNode exprValue;

    public AsmtNode(TypeNode type, IDNode varName, ExpressionNode exprValue){
        this.type = type;
        this.varName = varName;
        this.exprValue = exprValue;
    }

    static public AsmtNode parseAsmtNode(ArrayList<Token> tokens) throws SyntaxException, SemanticException {
        // Do the look ahead and see if the next token is an ID
        if(tokens.get(1).getTokenType() == TokenType.ID_KEYWORD){
            // If this is the case, then I have the type and the id
            TypeNode type = TypeNode.parseTypeNode(tokens);

            IDNode variableName = IDNode.parseIDNode(tokens);

            // Additionally code to add the current variable to the symbolTable
            Token varToken = variableName.getNodeToken();
            Token typeToken = type.getNodeToken();
            if(!symbolTable.hasVar(varToken.getToken())){
                // If this is the case then add the variable to the symbol table.
                // If the exprValue is the correct type can get checked in validate.

                // check if id is a keyword
                if (symbolTable.isKeyword(variableName.getToken().getToken())) {
                    String errMsg = "Invalid variable name: " + variableName.getToken().getToken();
                    throw new SemanticException(errMsg + "\nVariable name cannot be a keyword", variableName.getToken());
                }

                // check if var name is upper case
                if (Character.isUpperCase(variableName.getToken().toString().charAt(0))) {
                    String errMsg = "Invalid variable name: cannot start with a capital letter";
                    throw new SemanticException(errMsg, variableName.getToken());
                }

                // Check to make sure you're not in an if/while loop
                if(symbolTable.getIfWhileDepth() != 0){
                    // If this is true then error out because you tried to declare 
                    // inside of an if/while
                    String errMsg = "Attempted to declare variable inside of an if or while loop, which isn't allowed";
                    throw new SemanticException(errMsg, variableName.getToken());
                }

                symbolTable.addVar(varToken.getToken(), typeToken.getToken());
            }
            else {
                // If this is the case then you have a Semantic Error as you're trying to 
                // redeclare the variable.
                String errorMsg = "Attempted to redeclare already declared variable: " + varToken.getToken();
                throw new SemanticException(errorMsg, varToken);
            }
            
            if(tokens.get(0).getTokenType() != TokenType.ASSIGN){
                throw new SyntaxException("Assignment Node does not have valid token type.", tokens.get(0));
            }
            tokens.remove(0);
            // Handle the expression Node 
            ExpressionNode expr = ExpressionNode.parseExpression(tokens);

            if(tokens.get(0).getTokenType() != TokenType.SEMICOLON){
                throw new SyntaxException("asgn node is missing semicolon.", tokens.get(0));
            }
            tokens.remove(0);
            
            return new AsmtNode(type, variableName, expr);
        }
        else {

            // This case, there is no type so treat it as null
            IDNode variableName = IDNode.parseIDNode(tokens);


            Token varToken = variableName.getNodeToken();
            if(!symbolTable.hasVar(varToken.getToken())){
                // If this is the case then add the variable to the symbol table.
                // If the exprValue is the correct type can get checked in validate.
                String errorMsg = "Attempted to use undeclared variable: " + varToken.getToken();
                throw new SemanticException(errorMsg, varToken);
            }
            
            if(tokens.get(0).getTokenType() != TokenType.ASSIGN){
                throw new SyntaxException("Assignment Node does not have valid token type.", tokens.get(0));
            }
            tokens.remove(0);
            // Handle the expression Node 
            ExpressionNode expr = ExpressionNode.parseExpression(tokens);

            if(tokens.get(0).getTokenType() != TokenType.SEMICOLON){
                throw new SyntaxException("asgn node is missing semicolon.", tokens.get(0));
            }
            tokens.remove(0);  

            return new AsmtNode(null, variableName, expr);
        }
    }

    @Override
    public String convertToJott() {
        // When making this convert, check to see if the type value exists.
        // If it does then include it in the converted string, if not, then 
        // just make the string without it
        String stringToReturn = "";
        if(type != null){
            // Means that there is a type to add
            stringToReturn = stringToReturn.concat(type.convertToJott());
            stringToReturn += " ";
        }
        // Now no matter what, call the convert to Jott on the ID
        stringToReturn = stringToReturn.concat(varName.convertToJott());
        stringToReturn = stringToReturn.concat("=");
        // Call the expression node's convert 
        stringToReturn = stringToReturn.concat(exprValue.convertToJott());
        stringToReturn = stringToReturn.concat(";");
        // The string's constructed
        return stringToReturn;

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
    public boolean validateTree() throws SemanticException {
        String tokenText = this.varName.getNodeToken().getToken();
        if (!symbolTable.hasVar(tokenText)) {
            throw new SemanticException("Left hand assignment variable is not defined", this.varName.getToken());
        }
        String varType = symbolTable.getVarType(tokenText);
        String exprType = this.exprValue.getType();

        // Justin: This causes an issue because if the exprValue is a functionCallNode, then the functionCallNode doesn't have token associated with it
        // Max: What this would actually need to do if you're dealing with a function, is check the func's
        // return type to see if it matches the type of the var.
        // We'll also need a special case for binary expression node, right?
        // Cause that won't be an individual entry in the table anyways. 
        // validate right hand IDKeyword exists in symbol table
        
        // Have an extra check to make sure that if you have an id, it's variable exists
        if (this.exprValue instanceof IDNode){
            if (!symbolTable.hasVar(this.exprValue.getToken().getToken())){
                throw new SemanticException("Right hand variable not defined", this.exprValue.getToken());
            }
        }

        // validate that same types are used in expressions
        // NEED TO USE .equals FOR STRING COMPARISON!!!!!!!!!!!!
        if (!varType.equals(exprType)) {
            String errMsg = "Attempted to use non-matching types in an expression.";
            throw new SemanticException(errMsg, this.exprValue.getToken());
        }

        if (!this.exprValue.validateTree()) {
            throw new SemanticException("Invalid expression in AsmtNode", "");
        }
        return true;
    }

}
