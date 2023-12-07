#include "concat.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

int foo(int x) {
	return x + 1;

}
int main() {
	int x = 0;
	while(x < 10) {
		x = foo(x);
		printf("%d\n", x);
}

	return 0;
}
