public class validLoop {
public static int foo(int x) {
	return x + 1;

}
public static void main(String args[]) {
	int x = 0;
	while(x < 10) {
		x = foo(x);
		System.out.println(x);
}

}

}