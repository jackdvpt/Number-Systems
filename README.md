# Number-Systems
This is a library of functions which allows the representations of non-negative integers in an arbitary base b, where 2 <= b <= 10.

removeLeadingzeroes

- Given a valid, non-empty input, this returns the input with all leading zeroes removed.
- If the input contains only zeroes then the method returns 0

isValid

- Given that value contains at least a character and base this method returns true if value is a valid representation of an integer in the base

GetSmallestPossibleBase

- Given an non empty vinput return the smallest possible base for which the given value is a valid representations

parity

- Given value, return 0 if the sum of all digits is even and 1 if its odd

Compare
- Given value 1 and value2 are in the same base
  - returns 1 if value1 > value2
  - returns 0 if value1 == value2
  - returns -1 if value1 < value2

ConvertromDecimal
- given a number return the string representation of the integer in the base

Increment

- The value is increased by 1 in the given base

Decrement
- Return the number with its value decreased by 1

increased
- Increase the passed value by incval

decrease
- Decrease the passed value by decVal
