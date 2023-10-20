### Group 6 (Derek Pruski, Max O'Malley, Eligh Ros, Kellen Bell, Justin Ceiley)

## Phase 2 Jott Parser


**Be sure to view this file as a proper markdown file to get the correct formatting for commands.**  
Specifically, the instructions to compile and run will have \ characters that, if viewed as raw text, will make the commands to compile the code not work properly.

### Instructions to Compile and Run:
1. Go into the src folder in the jott_translator, and run the following command on the command line: 
javac nodes/\*.java provided/\*.java testers/\*.java -d classes
2. This will create the classes folder inside of the src folder. 
3. Open up the classes folder and paste a folder named parserTestCases folder inside of the classes folder.  That parserTestCases folder should contain the .jott files accessed by the JottParser.
4. Navigate to the classes folder on the command line, and run the following command to run the JottParserTester: java testers/JottParserTester
