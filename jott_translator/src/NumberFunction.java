
import java.util.ArrayList;

import provided.Token;

public class NumberFunction {

    public static ArrayList<Token> read_numToken(String str) {
        
        ArrayList<Token> tokens = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            String build_token = new String();
            boolean first_decimal = true;
            while (i < str.length()) {
                if (Character.isDigit((str.charAt(i)))) {
                    build_token += str.charAt(i);
                }
                else if (str.charAt(i) == '.' && first_decimal) {
                    if (i != 0) {
                        if (Character.isDigit(str.charAt(i - 1))) {
                        build_token += str.charAt(i);
                        first_decimal = false;
                        }
                    }
                    else if (Character.isDigit(str.charAt(i + 1))) {
                        build_token += str.charAt(i);
                        first_decimal = false;
                    }
                    else {
                        System.out.println("Error: Cannot take a decimal by itself");
                        break;
                    }
                }
                else if (str.charAt(i) == '.' && !first_decimal) {
                    System.out.println("Error: Cannot have 2 decimals in one number");
                    build_token = "";
                    break;
                }
                else if (str.charAt(i) == ' '){
                    break;
                }
                else if (!build_token.isEmpty()){
                    System.out.println("Error: Non-digit character found in number");
                    build_token = "";
                    break;
                }
                i++;
            }
            if (!build_token.isEmpty()) {
                    System.out.println(build_token);
                    Token curr_token = new Token(build_token, str, 0, null);
                    tokens.add(curr_token);
            }
        }
        return tokens;
    }

    public static void main(String[] args) {
        read_numToken("97.23.");
        read_numToken("00.21");
        read_numToken("109230a");
        read_numToken("24180.249810298 .001");
        read_numToken("..1");


    }
    
}
