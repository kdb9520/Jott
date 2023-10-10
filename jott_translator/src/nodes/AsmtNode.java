import java.beans.Expression;
import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class AsmtNode implements JottTree {
    
    private IDNode type;
    private IDNode varName;
    private ExpressionNode exprValue;

    public AsmtNode(IDNode type, IDNode varName, ExpressionNode exprValue){
        this.type = type;
        this.varName = varName;
        this.exprValue = exprValue;
    }

    static public AsmtNode parseAsmtNode(ArrayList<Token> tokens) throws SyntaxException {
        IDNode firstId = IDNode.parseIDNode(tokens);
        if(tokens.get(0).getTokenType() == TokenType.ID_KEYWORD){
            // Then you're dealing with the first assignment case 
            IDNode secondId = IDNode.parseIDNode(tokens);
            
            if(tokens.get(0).getTokenType() != TokenType.ASSIGN){
                throw new SyntaxException("Assignment Node does not have valid token type.", tokens.get(0));
            }
            tokens.remove(0);

            // Handle the expression Node 
            ExpressionNode expr = ExpressionNode.parseExpression(tokens);

            return new AsmtNode(firstId, secondId, expr);
        }
        else {
            // Second case where there is no type 
                        if(tokens.get(0).getTokenType() != TokenType.ASSIGN){
                throw new SyntaxException("Assignment Node does not have valid token type.", tokens.get(0));
            }
            tokens.remove(0);

            // Handle the expression Node 
            ExpressionNode expr = ExpressionNode.parseExpression(tokens);

            return new AsmtNode(null, firstId, expr);
        }
        

    }

    @Override
    public String convertToJott() {
        // When making this convert, check to see if the type value exists.
        // If it does then include it in the converted string, if not, then 
        // just make the string without it

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
