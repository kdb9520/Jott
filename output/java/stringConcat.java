public class stringConcat {
public static void main(String args[]) {
	String s = ("foo", "bar");
	System.out.println(s);	String s1 = "foo";
	String s2 = ("bar", s1);
	System.out.println(s2);	System.out.println(("foo", ("bar", "baz")));
}

}