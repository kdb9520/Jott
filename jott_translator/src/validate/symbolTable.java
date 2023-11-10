package validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is meant to be used across the nodes to properly validate in phase 3
 * 
 * @author Max O'Malley, Eligh Ros
 */

public class symbolTable {
    // Includes the functions, their variables and params.
    // Has the return type of each 
    public static HashMap<String, HashMap<String, String>> functions;

    // This holds the parameters and associated types for each function
    public static HashMap<String, HashMap<String, String>> funcParams;

    // Holds key value pairs of func_name:num_of_params
    public static HashMap<String, Integer> funcParamCount;

    public static String current_function;
// Variable that, if it is not zero, says you are inside of an if or while statement.
    // Used for ensuring variables aren't declared in a while loop/if.
    public static int ifWhileDepth;
    public static int whileDepth; // Kellen

    // initializer.  Needs to be called by something before the symbol
    // table can be used across the program
    public static void initSymbolTable() {
        // Initialize the hash maps
        functions = new HashMap<String, HashMap<String, String>>();
        funcParams = new HashMap<String, HashMap<String, String>>();
        funcParamCount = new HashMap<String, Integer>();
        current_function = null;
        ifWhileDepth = 0;

        // check in while -- Kellen
        whileDepth = 0;

        // Add the built in function here
        // Length takes in a string as its only param
        addFunc("length");
        setFunc("length");
        addParam("stringToCheck", "String");
        addVar("Return", "Integer");

        // Print takes in a string as its only param
        addFunc("print");
        setFunc("print");
        addParam("stringToPrint", "String");
        addVar("Return", "Void");

        // Concat takes 2 strings and returns a string
        addFunc("concat");
        setFunc("concat");
        addParam("first", "String");
        addParam("second", "String");
        addVar("Return", "String");
    }

    // check if a func with this name has already been added to the functions hashmap
    public static Boolean hasFunc(String func_name) {
        if (functions.containsKey(func_name)) {
            return true;
        }
        else {
            return false;
        }
    }

    // takes a function name and creates an empty hashmap to store to the functions hashmap
    public static void addFunc(String func_name) {
        // There needs to be a check added here to make sure that no built in functions get added
        // You CANNOT add print, concat, or length
        HashMap<String, String> curr_hash = new HashMap<String, String>();
        functions.put(func_name, curr_hash);

        HashMap<String, String> currentParamMap = new HashMap<String, String>();
        funcParams.put(func_name, currentParamMap);

        // Add the param count here
        funcParamCount.put(func_name, 0);
    }

    // sets current_function value to given func_name
    public static void setFunc(String func_name) {
        current_function = func_name;
    }

    // checks if hashmap of current_function contains given var_name
    public static Boolean hasVar(String var_name) {
        HashMap<String, String> curr_hash = functions.get(current_function);
        if (curr_hash.containsKey(var_name)) {
            return true;
        }
        else {
            return false;
        }
    }

    // adds new parameter to current_function hashmap with key of var_name and value equal to string of type
    public static void addParam(String var_name, String type) {
        HashMap<String, String> currentVarTable = functions.get(current_function);
        currentVarTable.put(var_name, type);

        // Get the param count for the current func and increment it
        funcParamCount.put(current_function, funcParamCount.get(current_function) + 1);

        // The param has been put in the variable table, so put it in the param table now
        HashMap<String, String> currentParamTable = funcParams.get(current_function);
        currentParamTable.put(var_name, type);
    }

    // returns an ArrayList containing the types of all params for curr_function, length of list is how many params should be present
    public static ArrayList<String> getParamTypes() {
        HashMap<String, String> currentParams = funcParams.get(current_function);
        // This array list should have a sequence of the return types of the params.
        ArrayList<String> returnTypes = new ArrayList<String>(currentParams.values());
        System.out.println("Calling getParamTypes, results in list of length " + returnTypes.size() + " and this output: ");
        int paramIndex = -1;
        for(String currentType : returnTypes){
            paramIndex++;
            System.out.println("Param Has Type " + currentType + " at index " + paramIndex);
        }
        return returnTypes;
    }


    // Gets the param count for the current function
    public static int getParamCount(){
        return funcParamCount.get(current_function);
    }

    // adds new variable to current_function hashmap with key of var_name and value equal to string of type
    public static void addVar(String var_name, String type) {
        HashMap<String, String> curr_hash = functions.get(current_function);
        curr_hash.put(var_name, type);
    }

    // gets the variable type for variable in current_function hashmap with given var_name
    public static String getVarType(String var_name) {
        HashMap<String, String> curr_hash = functions.get(current_function);
        String type = curr_hash.get(var_name);
        return type;
    }

    // Should get the return type for the current function
    public static String getFuncReturnType(){
        return getVarType("Return");
    }

    public static void incrementIfWhileDepth(){
        ifWhileDepth++;
    }

    public static void decrementIfWhileDepth(){
        ifWhileDepth--;
    }

    public static int getIfWhileDepth(){
        return ifWhileDepth;
    }

    // Kellen
    public static void incrementWhileDepth(){
        whileDepth++;
    }

    public static void decrementWhileDepth(){
        whileDepth--;
    }

    public static int getWhileDepth(){
        return whileDepth;
    }

    // returns the entire hashmap of the current_function (shouldn't need to use this)
    public static HashMap<String, String> getCurrentFunctionHashMap() {
        return functions.get(current_function);
    }

    // updates replaces current_functions hashmap with given hashmap (shouldn't need to use this)
    public static void updateCurrentFunctionHashMap(HashMap<String, String> curr_hash) {
        functions.put(current_function, curr_hash);
    }

    // returns the entire symbol table (shouldn't need to use this)
    public static HashMap<String, HashMap<String, String>> getSymTab() {
        return functions;
    }
    public static void printSymTab() {
        System.out.println("\nStarting SymbolTable Print:\n");
        for (Map.Entry<String, HashMap<String, String>> funcs : functions.entrySet()) {
            String curr_func = funcs.getKey();
            HashMap<String, String> curr_map = funcs.getValue();
            System.out.println("Function \"" + curr_func + "\" contains vars:");
            for (Map.Entry<String, String> vars : curr_map.entrySet()) {
                String var = vars.getKey();
                String type = vars.getValue();
                System.out.println("\tVar name: " + var + ", Var type: " + type);
            }
        }
        System.out.println("\nEnding Symbol Table Print:\n");
    }
    // Have a current function value 
    // That current function value is set by the FuncDefNode
    // So then when a variable is added to the symbol table, regardless of where it's 
    // called from it'll know what row of the symbol table to add it to. 

    // Have a symbol table add based on the current 

    // Function return node needs to have some way of telling what the return type of
    // A given function is.
}
