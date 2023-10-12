/**
 * A class representing the Program part of the Jott grammar.  Meant to be 
 * the upper most node in the JottTree structure.
 * 
 * @author Max O'Malley
 */

import provided.JottTree;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class ProgramNode implements JottTree {
    // Stubbing this out first


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
        return "PLACEHOLDER, WIP FUNCTION";
    }

    
    public static ProgramNode parseProgramNode(ArrayList<Token> tokens){

        // TO DO
        return null;
    }
}
