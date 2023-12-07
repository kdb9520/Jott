#include "concat.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

int main() {
	char * s = concat("foo", "bar");
	printf("%s", s);
	char * s1 = "foo";
	char * s2 = concat("bar", s1);
	printf("%s", s2);
	printf("%s", concat("foo", concat("bar", "baz")));

	return 0;
}
