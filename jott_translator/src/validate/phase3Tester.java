package validate;

import java.io.IOException;
import java.util.ArrayList;

import javax.crypto.spec.PBEKeySpec;

import provided.*;

public class phase3Tester {
    // Basically this is going to replicate what the prof did in his own testers

    ArrayList<TestCase> testCases;

    private static class TestCase{
        String testName;
        String inputFileName;
        String outputFileName;
        boolean error;

        // These will be the prefixes to the input/output so you can decide where they are.
        String inputPrefix = "StarterCode/phase3testcases/";
        String outputPrefix = "output/";


        public TestCase(String testName, String input, String output, boolean error) {
            this.testName = testName;
            this.inputFileName = inputPrefix + input;
            this.outputFileName = outputPrefix + output;
            this.error = error;
        }
    }

    public phase3Tester(){
        // constructor is blank for now

    }

    private void createTestCases(){
        // So this will do the functionality of creating all the test cases 
        this.testCases = new ArrayList<>();
        // These are the tests that we know pass right now
        testCases.add(new TestCase("HelloWorld", "helloWorld.jott", "helloWorldOutput.jott", false));
        testCases.add(new TestCase("function not defined", "funcNotDefined.jott", "funcNotDefinedOutput.jott", true));
        testCases.add(new TestCase("main having a non void return should error", "mainReturnNotInt.jott", "mainReturnNotIntOutput.jott", true));
        testCases.add(new TestCase("jott file needs a main function", "missingMain.jott", "missingMainOutput.jott", true));
        testCases.add(new TestCase("Functions with a return type need a return", "missingReturn.jott", "missingReturnOutput.jott", true));
        testCases.add(new TestCase("provided example code 1", "providedExample1.jott", "providedExample1Output.jott", false));
        testCases.add(new TestCase("returning not allowed in void func", "returnId.jott", "returnIdOutput.jott", true));
        testCases.add(new TestCase("Check when void function has a return", "voidReturn.jott", "voidReturnOutput.jott", true));
        testCases.add(new TestCase("while is not a valid id", "whileKeyword.jott", "whileKeywordOutput.jott", true));

        // These are the test cases we know fail right now
        testCases.add(new TestCase("Parameter types need to match", "funcCallParamInvalid.jott", "funcCallParamInvalidOutput.jott", true));
        testCases.add(new TestCase("Function Needs Return in all if cases", "noReturnIf.jott", "noReturnIfOutput.jott", true));
        testCases.add(new TestCase("noReturnWhile Test", "noReturnWhile.jott", "noReturnWhileOutput.jott", true));
        testCases.add(new TestCase("valid loop test case", "validLoop.jott", "validLoopOutput.jott", false));
    
        // Files that we've not tested till just now
        testCases.add(new TestCase("Types don't match in while loops binary expression", "funcReturnInExpr.jott", "funcReturnInExprOutput.jott", true));
        
        testCases.add(new TestCase("Large Valid Jott File", "largerValid.jott", "largerValidOutput.jott", false));
        testCases.add(new TestCase("Missing parameters in func call", "missingFuncParams.jott", "missingFuncParamsOutput.jott", true));
    
        // This one says the return type is null when it should be seeing a Double return
        testCases.add(new TestCase("Wrong return type in function", "funcWrongParamType.jott", "funcWrongParamTypeOutput.jott", true));
    
        // If statement fails due to body not checking if it's body_statements have any return 
        testCases.add(new TestCase("Passing If statement", "ifStmtReturns.jott", "ifStmtReturnsOutput.jott", false));
    }


    private boolean runTest(TestCase test){
        System.out.println("\nRunning Test: " + test.testName);
        boolean testResult;
        testResult = phase3Main.processJott(test.inputFileName, test.outputFileName);

        if(testResult && !test.error){
            // Means that you successfully return as expected 
            System.out.println("Test Successfully returned true as expected\n");
            return true;

        }
        else if(!testResult && test.error){
            // Means you got an error and expected one.
            // Will need to check later if it is the expected error, however
            System.out.println("Test returned an error as expected\n");
            return true;
        }
        else {
            // Did not recieve an expected result
            System.out.printf("\t\tTest returned unexpected result, expected error: %b\n", test.error);
            System.out.println("TEST FAILED");
            return false;
        }     
    }


    public static void main(String[] args) {
        // Okay so run through each case and try to test it 
        System.out.println("Attempting to run phase 3 test cases");
        phase3Tester tester = new phase3Tester();

        int numTests = 0;
        int passedTests = 0;

        tester.createTestCases();
        for(phase3Tester.TestCase currentTest : tester.testCases){
            // This should iterate over all the test cases 
            numTests++;
            try{
                if(tester.runTest(currentTest)){
                    // Then the test ran successfully, increment the passed test count
                    passedTests++;
                }
            }
            catch (Exception e){
                System.err.printf("Unexpected error happened in test case: %s\n", currentTest.testName);
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.printf("Passed: %d/%d%n", passedTests, numTests);
    }
}
