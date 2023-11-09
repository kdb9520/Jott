package nodes;

/**
 * Abstract Expression node meant to be used anytime an expr is called in the 
 * Jott grammar.  Extended by anything that expr calls inside of the language.
 * Makes use of BinaryExpressionNode in the cases where there is an <op> in the expr.
 * 
 * @author Max O'Malley 
 */

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

abstract class ExpressionNode implements JottTree {

    public abstract TokenType getTokenType();

    public abstract Token getToken();

    static ExpressionNode parseExpression(ArrayList<Token> tokens) throws SyntaxException, SemanticException {
        // First, check the cases that cannot involve an op.
        if(tokens.get(0).getToken().equals("True") || 
            tokens.get(0).getToken().equals("False")){
            // This is the case where you JUST have a boolean
            return BoolNode.parseBoolNode(tokens);
        } 
        if(tokens.get(0).getTokenType() == TokenType.STRING){
            // Have a string literal, which means just return that node 
            return StringLitNode.parseStringLitNode(tokens);
        }

        // Now there may be cases where you have an op 
        ExpressionNode left;
        if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            left = IDNode.parseIDNode(tokens);
        }
        else if (tokens.get(0).getTokenType() == TokenType.NUMBER) {
            left = NumberNode.parseNumberNode(tokens);
        }
        else if (tokens.get(0).getTokenType() == TokenType.FC_HEADER) {
            left = FunctionCallNode.parseFunctionCallNode(tokens);
        }
        else {
            // If none of the previous ifs are true, then the expr node isn't valid 
            throw new SyntaxException("ExpressionNode does not have a valid token type", 
            tokens.get(0));
        }

        // Look at the current token now, if it is a math_op or rel_op, you have a binary expression
        // Otherwise just return left
        if(tokens.get(0).getTokenType() == TokenType.REL_OP ||
        tokens.get(0).getTokenType() == TokenType.MATH_OP){
            // In this case you have binary expression
            Token operator = tokens.remove(0);

            // Get the right side node, then return a binaryExpressionNode
            ExpressionNode right = parseExpression(tokens);

            // check that right token doesn't chain ops
            // check that there is more than just one token left
            if (right instanceof BinaryExpressionNode) {
                String errMsg = "Invalid chaining of Binary Operations";
                throw new SemanticException(errMsg, operator);
            }

            // Put return with binaryExpressionNode here
            return new BinaryExpressionNode(left, right, operator);
        }
        else {
            return left;
        }
    }
    
}
