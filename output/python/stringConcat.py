def main():
	s = concat("foo", "bar")

	print(s)
	s1 = "foo"

	s2 = concat("bar", s1)

	print(s2)
	print(concat("foo", concat("bar", "baz")))

main()