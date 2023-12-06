#include <stdio.h>
#include <stdbool.h>

int foo(int x) {
return x + 1;

}
int main() {
	int x = 0;
	while(x < 10) {
		x = foo(x);
		printf(x);
}

	return 0;
}
