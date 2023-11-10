package nodes;

import provided.Token;

public class SemanticException extends Exception {
    public SemanticException(String errorMessage, Token currentToken) {
        super(formatErrorMessage(errorMessage, currentToken));
    }

    public SemanticException(String errorMessage, String extraString) {
        super(formatErrorMessage(errorMessage, extraString));
    }


    static private String formatErrorMessage(String errorMessage, Token currentToken){
        if(currentToken == null){
            return "Semantic Error\n" + errorMessage;
        }
        else {
            return "Semantic Error\n" + errorMessage + "\n" + currentToken.getFilename() + ":" + currentToken.getLineNum();
        }
    }

    static private String formatErrorMessage(String errorMessage, String extraString){
        String msg = "Semantic Error\n" + errorMessage;
        if (!extraString.equals("")) {
            msg += ":  " + extraString;
        }
        return msg;
    }
}
