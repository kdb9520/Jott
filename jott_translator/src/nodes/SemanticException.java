package nodes;

import provided.Token;

public class SemanticException extends Exception {
    public SemanticException(String errorMessage, Token currentToken) {
        super(formatErrorMessage(errorMessage, currentToken));
    }


    static private String formatErrorMessage(String errorMessage, Token currentToken){
        if(currentToken == null){
            return "Semantic Error:\n" + errorMessage;
        }
        else {
            return "Semantic Error:\n" + errorMessage + "\n" + currentToken.getFilename() + ":" + currentToken.getLineNum();
        }
    }
}
