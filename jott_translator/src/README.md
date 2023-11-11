### Group 6 (Derek Pruski, Max O'Malley, Eligh Ros, Kellen Bell, Justin Ceiley)

## Phase 3 Jott Validate Tree

Implementation of nodes that can be used to represent tokens in the form of a JottTree. 
The JottParser takes a list of tokens and scans through them in order to construct 
an accurate JottTree that can then be read and converted back into the original Jott code. 
In future parts implementation will be added to convert this tree 
into C, Python, and Java code.

For phase 3 the validateTree function was implemented for all of the nodes.  That function does 
some of the job of checking various sematic rules inside of Jott,but several rules were also 
checked inside of the different parse functions due to being easier to check there.  

There is also a symbolTable.java file that is populated during the parsing with all of the 
function and variable names.  This is used throughout the validate to confirm the existance 
of various variables and functions.  

The code that should be ran to have the phase 3 functionality is the phase3Main.java inside of 
the validate folder.  The specific instructions of how to compile and run from the command line 
are detailed below. 

The phase3Main.java expects to take 2 inputs which are the jott file to read and the file to output to.
The file to output to will have Jott output to it automatically for this phase. 
The choice of which language to output will be added in the next phase. 

**Be sure to view this file as a proper markdown file to get the correct formatting for commands.**  
Specifically, the instructions to compile and run will have \ characters that, if viewed as raw text, will make the commands to compile the code not work properly.

### Instructions to Compile and Run: 
1. The nodes, provided, and testers packages are all required to be compiled so that the tester can properly run.  One method of doing this is to go into the src folder in the phase3 folder, and run the following command on the command line: 
javac nodes/\*.java provided/\*.java validate/\*.java -d classes
2. This will create the classes folder inside of the src folder. 
3. Navigate to the classes folder on the command line, and run the following command in that folder to run phase: java validate/phase3Main [inputJottFilename] [outputJottFilename]
4. The inputJottFilename and outputJottFilename need to include either the relative path or the absolute path to the files to be run.  
