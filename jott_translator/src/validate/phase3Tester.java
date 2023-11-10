package validate;

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
        testCases.add(new TestCase("HelloWorld", "helloWorld.jott", "helloWorldOutput.jott", false));
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
        }

        System.out.printf("Passed: %d/%d%n", passedTests, numTests);
    }
}
