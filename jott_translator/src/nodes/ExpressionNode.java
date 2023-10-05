import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

abstract class ExpressionNode implements JottTree {

    static ExpressionNode parseExpression(ArrayList<Token> tokens) {
        if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            return IDNode.parseIDNode(tokens);
        }
        if (tokens.get(0).getTokenType() == TokenType.NUMBER) {
            return NumberNode.parseNumberNode(tokens);
        }
        if (tokens.get(0).getTokenType() == TokenType.FC_HEADER) {
            return FunctionCallNode.parseFunctionCallNode(tokens);
        }
    }
    
}
