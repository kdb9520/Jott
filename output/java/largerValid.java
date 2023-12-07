public class largerValid {
public static void foo(int x) {
	System.out.println(x);
}
public static void baz() {
	System.out.println("Hello World");
}
public static String bar(double x) {
	if (x > 5.1) { 
return "bar";
} 
	baz();return "foo";

}
public static void main(String args[]) {
	double y = 1.0;
	int x = 5;
	while(x > 0) {
		foo(x);		System.out.println(bar(y));		x = x - 1;
		y = y + 1.1;
}
	baz();
}

}