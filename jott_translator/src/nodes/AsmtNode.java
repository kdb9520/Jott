package nodes;

import java.beans.Expression;
import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class AsmtNode implements BodyStmtNode {
    
    private TypeNode type;
    private IDNode varName;
    private ExpressionNode exprValue;

    public AsmtNode(TypeNode type, IDNode varName, ExpressionNode exprValue){
        this.type = type;
        this.varName = varName;
        this.exprValue = exprValue;
    }

    static public AsmtNode parseAsmtNode(ArrayList<Token> tokens) throws SyntaxException {
        // Do the look ahead and see if the next token is an ID
        if(tokens.get(1).getTokenType() == TokenType.ID_KEYWORD){
            // If this is the case, then I have the type and the id
            TypeNode type = TypeNode.parseTypeNode(tokens);

            IDNode variableName = IDNode.parseIDNode(tokens);
            
            if(tokens.get(0).getTokenType() != TokenType.ASSIGN){
                throw new SyntaxException("Assignment Node does not have valid token type.", tokens.get(0));
            }
            tokens.remove(0);
            // Handle the expression Node 
            ExpressionNode expr = ExpressionNode.parseExpression(tokens);

            return new AsmtNode(type, variableName, expr);
        }
        else {
            // This case, there is no type so treat it as null
            IDNode variableName = IDNode.parseIDNode(tokens);
            
            if(tokens.get(0).getTokenType() != TokenType.ASSIGN){
                throw new SyntaxException("Assignment Node does not have valid token type.", tokens.get(0));
            }
            tokens.remove(0);
            // Handle the expression Node 
            ExpressionNode expr = ExpressionNode.parseExpression(tokens);

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
            stringToReturn.concat(type.convertToJott());
        }
        // Now no matter what, call the convert to Jott on the ID
        stringToReturn.concat(varName.convertToJott());
        stringToReturn.concat("=");
        // Call the expression node's convert 
        stringToReturn.concat(exprValue.convertToJott());
        stringToReturn.concat(";");
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
    public boolean validateTree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }

}
