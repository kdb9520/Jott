#include "concat.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void foo(int x) {
	printf("%d\n", x);

}
void baz() {
	printf("Hello World\n");

}
char * bar(double x) {
	if (x > 5.1) { 
		return "bar";

} 
	baz();
	return "foo";

}
int main() {
	double y = 1.0;
	int x = 5;
	while(x > 0) {
		foo(x);
		printf("%s\n", bar(y));
		x = x - 1;
		y = y + 1.1;
}
	baz();

	return 0;
}
