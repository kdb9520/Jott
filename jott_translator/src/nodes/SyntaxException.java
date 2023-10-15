package nodes;

import provided.Token;

public class SyntaxException extends Exception {
    public SyntaxException(String errorMessage, Token currentToken) {
        super(formatErrorMessage(errorMessage, currentToken));
    }


    static private String formatErrorMessage(String errorMessage, Token currentToken){
        return "Syntax Error\n" + errorMessage + "\n" + currentToken.getFilename() + ":" + currentToken.getLineNum();
    }
}
