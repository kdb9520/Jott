### Group 6 (Derek Pruski, Max O'Malley, Eligh Ros, Kellen Bell, Justin Ceiley)

## Phase 4 Jott Validate Tree

Implementation of nodes that can be used to represent tokens in the form of a JottTree. 
The JottParser takes a list of tokens and scans through them in order to construct 
an accurate JottTree that can then be read and converted back into the original Jott code. 
The code can also be converted into C, Python, and Java depending on command line arguments given.

For phase 3 the validateTree function was implemented for all of the nodes.  That function does 
some of the job of checking various sematic rules inside of Jott,but several rules were also 
checked inside of the different parse functions due to being easier to check there.  

There is also a symbolTable.java file that is populated during the parsing with all of the 
function and variable names.  This is used throughout the validate to confirm the existance 
of various variables and functions.  

The code used to run the phase 3 and phase 4 functionality are inside of the phase4Main.java file. 
The specific instructions of how to compile and run from the command line 
are detailed below. 

The phase4Main.java expects to take 3 inputs as command line arguments, 
which are the jott file to read, the file to output to, 
and the language to output (which can be C, Java, Python, or Jott).
The file to output to will have the language specified output to it. 

**Be sure to view this file as a proper markdown file to get the correct formatting for commands.**  
Specifically, the instructions to compile and run will have \ characters that, if viewed as raw text, will make the commands to compile the code not work properly.

### Instructions to Compile and Run: 
1. An empty folder named "classes" needs to be created inside of the src folder before the below commands to compile and run can function properly.
2. The nodes, provided, and validate packages are all required to be compiled so that the phase 3 main can properly run.  One method of doing this is to go into the src folder in the phase3 folder, and run the following command on the command line: 
javac nodes/*.java provided/*.java phase4Main.java -d classes
3. This will create the classes folder inside of the src folder. 
4. Ensure that you have the concat.c and concat.h files in the folder you intend to output your C code to.  We have provided these files in the output/c folder.  It is recommended that you output your C files there.
5. Navigate to the top level src folder on the command line, and run the following command in that folder to run phase: java phase4Main [inputJottFilename] [outputJottFilename]
6. The inputJottFilename and outputJottFilename need to include either the relative path or the absolute path to the files to be run.
