import provided.Token;
import provided.TokenType;

public class Justinp1 {

    public static void main(String[] args) {
        if (str.charAt(i) == '=') {
            if (str.charAt(i+1) == '=') {
                Token token = new Token("==", filename, lineNum, TokenType.REL_OP);
            } else {
                Token token = new Token("=", filename, lineNum, TokenType.ASSIGN);
            }
        } else if (str.charAt(i) == '>') {
            if (str.charAt(i+1) == '=') {
                Token token = new Token(">=", filename, lineNum, TokenType.REL_OP);
            } else {
                Token token = new Token(">", filename, lineNum, TokenType.REL_OP);
            }
        } else if (str.charAt(i) == '<') {
            if (str.charAt(i+1) == '=') {
                Token token = new Token("<=", filename, lineNum, TokenType.REL_OP);
            } else {
                Token token = new Token("<", filename, lineNum, TokenType.REL_OP);
            }
        } else if (str.charAt(i) == ';') {
            Token token = new Token(";", filename, lineNum, TokenType.SEMICOLON);
        }


    }
}
