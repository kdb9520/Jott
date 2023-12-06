#include <stdio.h>
#include <stdbool.h>

int main() {
	char * s = ("foo", "bar");
	printf(s);
	char * s1 = "foo";
	char * s2 = ("bar", s1);
	printf(s2);
	printf(("foo", ("bar", "baz")));

	return 0;
}
