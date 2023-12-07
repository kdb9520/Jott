package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;
import validate.symbolTable;
import provided.JottTree;

public class BodyNode implements JottTree {

    private ArrayList<BodyStmtNode> bodyStmtNodes;
    private ReturnStmtNode returnStmt;
    private int indentDepth;

    public BodyNode (ArrayList<BodyStmtNode> bodyStmtNodes, ReturnStmtNode returnStmtNode, int indentDepth) {
        this.bodyStmtNodes = bodyStmtNodes;
        this.returnStmt = returnStmtNode;
        this.indentDepth = indentDepth;
    }


    // Used as a helper function for IfStmtNode to check if 
    // an if has a return statement or not
    public ReturnStmtNode getReturnNode(){
        // Remember, this CAN be a null value and that needs to be checked
        return returnStmt;
    }

    public String convertToC() {
        String stringBuilder = "";

        for (BodyStmtNode node : bodyStmtNodes) {
            for (int i = 0; i < this.indentDepth; i++) {
                stringBuilder += "\t";
            }
            stringBuilder += node.convertToC();
            if(node instanceof FunctionCallNode) {
                // This means you'd need to append a semicolon
                // Needed because function call has a semicolon in the body_stmt case, but NOT 
                // the expr case, and since the func_call can't tell what's calling it's convert,
                // you have to do this here. 
                stringBuilder += ";\n";
            }
        }

        if (this.returnStmt != null) {
            stringBuilder += this.returnStmt.convertToC();
        }

        return stringBuilder;
    }

    public String convertToJava(String className) {
        String stringBuilder = "";

        for (BodyStmtNode node : bodyStmtNodes) {
            for (int i = 0; i < this.indentDepth; i++) {
                stringBuilder += "\t";
                
            }
            stringBuilder += node.convertToJava(className);
            if(node instanceof FunctionCallNode) {
                // This means you'd need to append a semicolon
                // Needed because function call has a semicolon in the body_stmt case, but NOT 
                // the expr case, and since the func_call can't tell what's calling it's convert,
                // you have to do this here. 
                stringBuilder += ";";
            }
        }

        if (this.returnStmt != null) {
            stringBuilder += this.returnStmt.convertToJava(className);
        }

        return stringBuilder;
    }

    public String convertToPython() {
        String stringBuilder = "";

        for (BodyStmtNode node : bodyStmtNodes) {
            for (int i = 0; i < this.indentDepth; i++) {
                stringBuilder += "\t";
            }
            stringBuilder += node.convertToPython();
            // Add in a new line to prevent python related errors
            stringBuilder += "\n";
        }

        if (this.returnStmt != null) {
            for (int i = 0; i < this.indentDepth; i++) {
                stringBuilder += "\t";
            }
            stringBuilder += this.returnStmt.convertToPython();
        }

        return stringBuilder;
    }

    public String convertToJott() {
        String output = "";
        for (BodyStmtNode body : this.bodyStmtNodes) {
            output += body.convertToJott();
            if(body instanceof FunctionCallNode){
                // This means you'd need to append a semicolon
                // Needed because function call has a semicolon in the body_stmt case, but NOT 
                // the expr case, and since the func_call can't tell what's calling it's convert,
                // you have to do this here. 
                output += ";";
            }
        }
        if(this.returnStmt != null){
            String returnString = this.returnStmt.convertToJott();
            output += returnString;
        }
        return output;
    }

    public boolean validateTree() throws SemanticException {

        // If any of the bodyStmtNodes have a return that's valid, switch this to true
        boolean validReturnInIf = false;
        // make sure all body statements are valid
        for (BodyStmtNode bodyStmtNode : bodyStmtNodes) {
            if (!bodyStmtNode.validateTree()) {
                throw new SemanticException("Invalid body stmt node", "");
            }

            // Check if the bodyStmtNode is an if, and if it is, check if it has a valid return
            if(bodyStmtNode instanceof IfStmtNode && !validReturnInIf){
                IfStmtNode ifNode = (IfStmtNode) bodyStmtNode;
                validReturnInIf = ifNode.getReturnPath();                
            }
        }


        // check that return types match funtion return 
        // need to fix
        if (this.returnStmt == null){
            if(validReturnInIf){
                // Have a valid return path in an if, so just return true
                return true;
            }
            else if (!symbolTable.getVarType("Return").equals("Void")){
                throw new SemanticException("Missing return statement in non-void function", "");
            }
        }

        // validate return statement
        if (this.returnStmt != null) {
            return this.returnStmt.validateTree();
        }

        // If you reached this then either your return is void or it exists and is valid
        return true;
    }

    public static BodyNode parseBodyNode(ArrayList<Token> tokenList) throws SyntaxException, SemanticException {
        symbolTable.incrementIndentDepth();
        int currentDepth = symbolTable.getIndentDepth();
        // This needs to account for the fact that all of the body_stmt's start with an ID_KEYWORD
        // And that the return statement will sometimes be null
        ArrayList<BodyStmtNode> bodyStmtNodes = new ArrayList<BodyStmtNode>();
        while (((tokenList.get(0).getTokenType() == TokenType.ID_KEYWORD) && !(tokenList.get(0).getToken().equals("return"))) ||
        (tokenList.get(0).getTokenType() == TokenType.FC_HEADER)) {
            BodyStmtNode curr_BodyStmtNode = BodyStmtNode.parseBodyStmt(tokenList);
            bodyStmtNodes.add(curr_BodyStmtNode);
        }
        if ((tokenList.get(0).getTokenType() == TokenType.ID_KEYWORD) && (tokenList.get(0).getToken().equals("return"))) {
            ReturnStmtNode returnStmtNode = ReturnStmtNode.parseReturnStmtNode(tokenList);
            symbolTable.decrementIndentDepth();
            return new BodyNode(bodyStmtNodes, returnStmtNode, currentDepth);
        }
        symbolTable.decrementIndentDepth();
        return new BodyNode(bodyStmtNodes, null, currentDepth);
    }
}
