import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

abstract class ExpressionNode implements JottTree {

    static ExpressionNode parseExpression(ArrayList<Token> tokens) throws SyntaxException{
        // First, check the cases that cannot involve an op.
        if(tokens.get(0).getToken().equals("True") || 
            tokens.get(0).getToken().equals("False")){
            // This is the case where you JUST have a boolean
            return BoolNode.parseBoolNode(tokens);
        } 

        ExpressionNode left;
        if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            return IDNode.parseIDNode(tokens);
        }
        if (tokens.get(0).getTokenType() == TokenType.NUMBER) {
            return NumberNode.parseNumberNode(tokens);
        }
        if (tokens.get(0).getTokenType() == TokenType.FC_HEADER) {
            return FunctionCallNode.parseFunctionCallNode(tokens);
        }
        else {
            throw new SyntaxException("ExpressionNode does not have a valid token type", 
            tokens.get(0));
        }

        // Look ahead 1 to see if you have a Math_op or rel_op, if so, then you have a binary expression
    }
    
}
