package validate;

import java.util.HashMap;

/**
 * This class is meant to be used across the nodes to properly validate in phase 3
 * 
 * @author Max O'Malley
 */

public class symbolTable {
    public HashMap<String, HashMap<String, String>> functions;
    public String current_function;

    // constructor
    public symbolTable() {
        this.functions = new HashMap<String, HashMap<String, String>>();
        this.current_function = null;
    }

    // check if a func with this name has already been added to the functions hashmap
    public Boolean hasFunc(String func_name) {
        if (functions.containsKey(func_name)) {
            return true;
        }
        else {
            return false;
        }
    }

    // takes a function name and creates an empty hashmap to store to the functions hashmap
    public void addFunc(String func_name) {
        HashMap<String, String> curr_hash = new HashMap<String, String>();
        functions.put(func_name, curr_hash);
    }

    // sets current_function value to given func_name
    public void setFunc(String func_name) {
        current_function = func_name;
    }

    // checks if hashmap of current_function contains given var_name
    public Boolean hasVar(String var_name) {
        HashMap<String, String> curr_hash = functions.get(current_function);
        if (curr_hash.containsKey(var_name)) {
            return true;
        }
        else {
            return false;
        }
    }

    // adds new variable to current_function hashmap with key of var_name and value equal to string of type
    public void setVar(String var_name, String type) {
        HashMap<String, String> curr_hash = functions.get(current_function);
        curr_hash.put(var_name, type);
    }

    // gets the variable type for variable in current_function hashmap with given var_name
    public String getVarType(String var_name) {
        HashMap<String, String> curr_hash = functions.get(current_function);
        String type = curr_hash.get(var_name);
        return type;
    }

    // returns the entire hashmap of the current_function (shouldn't need to use this)
    public HashMap<String, String> getCurrentFunctionHashMap() {
        return functions.get(current_function);
    }

    // updates replaces current_functions hashmap with given hashmap (shouldn't need to use this)
    public void updateCurrentFunctionHashMap(HashMap<String, String> curr_hash) {
        functions.put(current_function, curr_hash);
    }

    // returns the entire symbol table (shouldn't need to use this)
    public HashMap<String, HashMap<String, String>> getSymTab() {
        return functions;
    }
    // Have a current function value 
    // That current function value is set by the FuncDefNode
    // So then when a variable is added to the symbol table, regardless of where it's 
    // called from it'll know what row of the symbol table to add it to. 

    // Have a symbol table add based on the current 
}
