public class ifStmtReturns {
public static int foo() {
	int x = 5;
	if (x > 5) { 
		x = x - 1;
		return x;
} 
else {
		return x;

}

}
public static void main(String args[]) {
	foo();

}

}