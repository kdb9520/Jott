import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class FunctionCallNode extends ExpressionNode {
    private IDNode funcName;
    private FuncParamNode params;
    
    public FunctionCallNode (IDNode f_name, FuncParamNode f_p) {
        this.funcName = f_name;
        this.params = f_p;
    }

    public boolean validateTree() {
        // TODO
        return false;
    }

    public String convertToJava(String classname) {
        // TODO
        return "not implemented";
    }

    public String convertToPython() {
        // TODO
        return "not implemented";
    }

    public String convertToC() {
        // TODO
        return "not implemented";
    }

    public String convertToJott() {
        String output = "::";
        output += funcName.convertToJott();
        output  += "[";
        output += params.convertToJott();
        output += "]";
        return output;
    }
}