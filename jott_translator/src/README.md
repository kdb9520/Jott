### Group 6 (Derek Pruski, Max O'Malley, Eligh Ros, Kellen Bell, Justin Ceiley)

## Phase 2 Jott Parser

Implementation of nodes that can be used to represent tokens in the form of a JottTree. The JottParser takes a list of tokens and scans through them in order to construct an accurate JottTree that can then be read and converted back into the original Jott code. In future parts implementation will be added to convert this tree into C, Python, and Java code.

**Be sure to view this file as a proper markdown file to get the correct formatting for commands.**  
Specifically, the instructions to compile and run will have \ characters that, if viewed as raw text, will make the commands to compile the code not work properly.

### Instructions to Compile and Run:
1. If it is desired to replace the parser tester file, then replace the JottParserTester.java inside of the src/testers folder before the following steps.  
2. If the JottParserTester.java in the src/testers is replaced, the following compilation instructions expect it to have the following line to ensure it imports the provided package properly: import provided.*;
3. The nodes, provided, and testers packages are all required to be compiled so that the tester can properly run.  One method of doing this is to go into the src folder in the phase2 folder, and run the following command on the command line: 
javac nodes/\*.java provided/\*.java testers/\*.java -d classes
4. This will create the classes folder inside of the src folder. 
5. Open up the created classes folder and paste a folder named parserTestCases inside of the classes folder.  That parserTestCases folder should contain the .jott files accessed by the JottParserTester.  
6. There is a provided parserTestCases folder at the top level of the zipped submission for phase 2.  This folder can be placed into the classes folder and will work with the existing JottPasterTester that is already in the code.  The parserTestCases can be replaced, but it should have the .jott files inside of it and have the same folder name. 
7. Navigate to the classes folder on the command line, and run the following command to run the JottParserTester: java testers/JottParserTester

