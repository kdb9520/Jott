def foo(x,):
	print(x)

def baz():
	print("Hello World")

def bar(x,):
	if x > 5.1: 
		return "bar"


	baz()
	return "foo"

def main():
	y = 1.0

	x = 5

	while x > 0 :
		foo(x)
		print(bar(y))
		x = x - 1

		y = y + 1.1


	baz()

main()