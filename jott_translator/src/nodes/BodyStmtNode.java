package nodes;

/**
 * An interface representing the body statement, primarily used to give 
 * access to the static function parseBodyStmt for use in other nodes. 
 * 
 * @author Max O'Malley
 */

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public interface BodyStmtNode extends JottTree {
    
    static BodyStmtNode parseBodyStmt(ArrayList<Token> tokens) throws SyntaxException{
        // Similarly to the base expression node, this needs to check if the current token
        // would match any of the possible body statement cases
        if(tokens.get(0).getTokenType() == TokenType.FC_HEADER){
            // Means that you're dealing with a function header
            return FunctionCallNode.parseFunctionCallNode(tokens);

            
        }
        // The other cases ALL start with an id_keyword
        // Both var dec and asmt could start with a type.  Check if its var dec by using look ahead? 
        // I could look ahead 2 to see if there is a ; 
        if(tokens.get(0).getTokenType() == TokenType.ID_KEYWORD){ // should this be an else if???
            // Now determine which of the other options is the case 
            String currentTokenText = tokens.get(0).getToken();
            if(currentTokenText.equals("if")){
                // Dealing with an if statement
                return IfStmtNode.parseIfStmtNode(tokens);
            }
            else if(currentTokenText.equals("while")){
                // Dealing with a while loop
                return While_LoopNode.parseWhile_LoopNode(tokens);
            }
            
            // If neither of the above is the case then either have a var dec or asmt
            if(tokens.get(2).getTokenType() == TokenType.SEMICOLON){
                // Look ahead to see if the semi colon exists, if it does you have a var dec
                return VarDecNode.parseVarDecNode(tokens);
            }
            else if(tokens.get(2).getTokenType() == TokenType.ASSIGN || 
                tokens.get(1).getTokenType() == TokenType.ASSIGN){
                // Check if either above look ahead is assignment.  If either is true you have an asmt case
                return AsmtNode.parseAsmtNode(tokens);
            }
            else{
                // If none of the above is true, then you've run out of valid id_keyword options
                throw new SyntaxException("Body Statement Node does not match any expected id_keyword values", 
                tokens.get(0));
            }
        }
        else {
            throw new SyntaxException("Body Statement Node does not have a valid token type", 
            tokens.get(0));
        }
    }
}
