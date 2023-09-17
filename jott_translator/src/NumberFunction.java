
import java.util.ArrayList;

import provided.Token;

public class NumberFunction {

    public static ArrayList<Token> read_numToken(String str) {
        
        ArrayList<Token> tokens = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) { // loop through string
            String build_token = new String(); // initialize string to build token
            boolean first_decimal = true; // set boolean to check if multiple decimals are found in the same string
            if ((Character.isDigit(str.charAt(i))) || (str.charAt(i) == '.')) { // if first char of token is digit or decimal enter digit loop
                while (i < str.length()) { // check so that you don't go out of bounds
                    if (Character.isDigit((str.charAt(i)))) { // if digit, add to token string
                        build_token += str.charAt(i);
                    }
                    else if (str.charAt(i) == '.' && first_decimal) { // if first decimal in current token
                        if ((i != 0)) {
                            if (Character.isDigit(str.charAt(i - 1))) { // check if there was a number beforehand
                                build_token += str.charAt(i);
                                first_decimal = false;
                            }
                            else if (i < str.length() - 1) { // else check if there is a number afterwards
                                if (Character.isDigit(str.charAt(i + 1))) {
                                    build_token += str.charAt(i);
                                    first_decimal = false;
                                }
                            }
                            else { // else error out because there is a decimal by itself
                                System.out.println("Error: Cannot take a decimal by itself");
                                break;
                            }
                        }
                        else if (i < str.length() - 1) { // if there is no existing character before the decimal
                                if (Character.isDigit(str.charAt(i + 1))) { // check if the decimal is followed by a number
                                    build_token += str.charAt(i);
                                    first_decimal = false;
                                }
                                else { // else error out because there is a decimal by itself
                                    System.out.println("Error: Cannot take a decimal by itself");
                                    break;
                                }
                        }
                    }
                    else if (str.charAt(i) == '.' && !first_decimal) { // if there is a decimal but it isn't the first
                        if (i < str.length() - 1) {
                            if (Character.isDigit(str.charAt( + 1))) { // check if followed by a digit, if so then 
                                                                       // complete current token and start a new one with this decimal
                                i--;
                                break;
                            }
                            else { // else return error for trying to put 2 decimals in the same number
                                System.out.println("Error: Cannot have 2 decimals in one number");
                                build_token = "";
                                break;
                            }
                        }
                        else { // else return error for trying to put 2 decimals in the same number
                            System.out.println("Error: Cannot have 2 decimals in one number");
                            build_token = "";
                            break;
                        }
                    }
                    else if (str.charAt(i) == ' '){ // if space ignore
                        break;
                    }
                    else { // if non-digit or decimal then go back to loop with current character to try something else
                        i--;
                        break;
                    }
                    i++;
                }
            }
            if (!build_token.isEmpty()) { // if build token isn't empty and loop ends then add token
                    System.out.println(build_token);
                    Token curr_token = new Token(build_token, str, 0, null);
                    tokens.add(curr_token);

            }
        }
        return tokens;
    }

    public static void main(String[] args) {
        read_numToken("97.23.34");
        read_numToken("97.23.");
        read_numToken("00.21");
        read_numToken("109230a");
        read_numToken("24180.249810298 .001");
        read_numToken("..1");
        read_numToken(".1");

    }
    
}
