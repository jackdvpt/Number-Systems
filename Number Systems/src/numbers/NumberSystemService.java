package numbers;

import java.util.Random;

public class NumberSystemService {
    /**
     *
     * Precondition: value is purely numeric
     * @param value
     * @return the value with leading zeroes removed.
     * Should return "0" for input being "" or containing all zeroes
     */
    public static String removeLeadingZeroes(String value) {
        int i = 0;

		/*
		 * as long as there are characters left and the current
		 * character is a '0', move forward
		 */
        while(i < value.length() && value.charAt(i) == '0') {
            i++;
        }

        if(i == value.length()){ //if all zeroes
            return "0";
        }

        return value.substring(i); //if none or some zeroes
    }

    /**
     *
     * Precondition: value contains at least one character
     * @param value
     * @param base (2 <= base <= 10)
     * @return true if each character of value is between 0 and base-1
     */
    public static boolean isValid(String value, int base) {
        for(int i=0; i < value.length(); i++)
            if(!(value.charAt(i) >= '0' && value.charAt(i) <= (char)('0' + base - 1)))
                return false;
        return true;
    }

    /**
     * Pre-condition: value is non-empty, purely numerical, has no leading zeroes
     * @param value
     * @return the smallest possible base for which this number is valid
     * return 2 if value is equal to "0"
     */
    public static int getSmallestPossibleBase(String value) {
        if(value.equals("0")){ //0 is valid in any base. smallest of which is 2.
            return 2;
        }
        char max = '0';
        for(int i=0; i < value.length(); i++){
            if(value.charAt(i) > max){
                max = value.charAt(i);
            }
        }

		/*
		 * for any base b, max character is b - 1
		 * therefore, for the max character m, smallest possible base is m + 1
		 */
        return (int)(max - '0' + 1);
    }


    /**
     * Pre-condition: value is valid in given base, decimal conversion
     * is at most 2147483647 (maximum value that can be held in an int)
     * @param value
     * @return equivalent decimal value
     */
    public static int getDecimalValue(String value, int base) {
        int dec = 0;
        int power = 1;
        for(int i=value.length() - 1; i >= 0; i--) {
            char current = value.charAt(i);
            dec += power * (int)(current - '0');
            power *= base;
        }
        return dec;
    }

    /**
     * Pre-condition: value represents a positive integer
     * @param value
     * @return 0 if sum of digits is even, and 1 if its odd
     */
    public static int parity(String value) {
        int total = 0;
        for(int i=0; i < value.length(); i++)
            total+=value.charAt(i) - '0'; //add all digits together
        return total % 2; //if even - 0, if odd - 1
    }

    /**
     * Pre-condition: both value1 and value2 are in the same base
     * (which base is irrelevant) and DO NOT contain leading zeroes.
     *
     * Some examples,
     * compare("2342", 3412") = -1
     * compare("12342", 3412") = 1
     * compare("2342", 2342") = 0
     * compare("42", 322") = -1
     *
     * @param value1
     * @param value2
     * @return
     */
    public static int compare(String value1, String value2) {
		/*
		 * in case inputs are allowed to have leading zeroes
		 *
		 * String value1 = removeLeadingZeroes(value1);
		 * String value2 = removeLeadingZeroes(value2);
		 */
        if(value1.length() > value2.length()){ //clearly first is bigger
            return 1;
        }
        if(value1.length() < value2.length()){ //clearly second is bigger
            return -1;
        }

        for(int i=0; i < value1.length(); i++) { //go character by character
            if(value1.charAt(i) > value2.charAt(i)){ //found a bigger in first
                return 1;
            }
            if(value1.charAt(i) < value2.charAt(i)){ //found a smaller in first
                return -1;
            }
        }
        //found no difference
        return 0;
    }

    /**
     * Pre-condition: number >= 0, base (2 <= base <= 10)
     * @param number
     * @param base
     * @return a String representing the decimal number converted in given base
     */
    public static String convertFromDecimal(int number, int base) {
        if(number == 0)
            return "0";
        String result = "";
        while(number > 0) {
            int remainder = number % base;
            result = remainder + result;
            number = number / base;
        }
        return result;
    }

    /**
     * helper method for increment and decrement
     * @param s
     * @param index
     * @param c
     * @return the string s, with character at index idx replaced by c
     */
    public static String update(String s, int idx, char c) {
        return s.substring(0, idx) + c + s.substring(idx + 1);
    }

    /**
     * Precondition: value represents a valid
     * non-negative value in given base, and doesn't have leading zeroes
     * and base (2 <= base <= 10)
     * @param value
     * @param base
     * @return String representing (value+1) in given base
     */
    public static String increment(String value, int base) {
        int i=value.length() - 1; //least significant digit index
        boolean carryLeft = true;
        while(i >= 0 && carryLeft == true) { //till the most significant digit or till there's a carry
            if(value.charAt(i) < (char)('0' + base - 1)) { //can be increased by 1 without overflowing
                value = update(value, i, (char)(value.charAt(i)+1));
                carryLeft = false; //to exit the loop
            }
            else
                value = update(value, i, '0'); //max digit - reset it
            if(carryLeft == true)
                i--;
        }
        if(i < 0) //all the way to the left so carry was still there
            value = '1'+value;

        return value;
    }

    /**
     * Precondition: value represents a valid
     * positive value in given base, and doesn't have leading zeroes
     * and base (2 <= base <= 10)
     * @param value
     * @param base
     * @return String representing (value-1) in given base
     */
    public static String decrement(String value, int base) {
        int i=value.length()-1; //least significant digit
        boolean carryLeft = true;

        while(i >= 0 && carryLeft == true) { //till the most significant digit or till there's a carry
            if(value.charAt(i) > '0') { //can be decreased
                value = update(value, i, (char)(value.charAt(i)-1)); //decrease by 1
                carryLeft = false;
            }
            else
                value = update(value, i, (char)('0'+base-1)); //already '0'. make it max and carry on
            if(carryLeft == true)
                i--;
        }

        return removeLeadingZeroes(value);
    }

    /**
     * Precondition: value represents a valid
     * non-negative value in given base, and doesn't have leading zeroes.
     * incVal >= 0, and base (2 <= base <= 10)
     * @param value
     * @param incVal
     * @param base
     * @return String representing (value+incVal) in given base
     */
    public static String increase(String value, int incVal, int base) {
        /**
         * increasing by incVal is equivalent to incrementing by 1
         * incVal times. Of course, this is not very efficient.
         *
         * a second, more efficient version, is also provided.
         * similar version can be created for decrement
         */
        for(int i=0; i < incVal; i++)
            value = increment(value, base);
        return value;
    }

    /**
     * helper method for increaseEfficient
     * @param c1
     * @param c2
     * @param carry
     * @param base
     * @return the result of adding the three digits, represented
     * by c1, c2, and carry, all in the same base
     */
    public static String add(char c1, char c2, char carry, int base) {
        int dec1 = getDecimalValue(c1+"", base);
        int dec2 = getDecimalValue(c2+"", base);
        int dec3 = getDecimalValue(carry+"", base);

        int tempResult = dec1+dec2+dec3;

        return convertFromDecimal(tempResult, base);
    }

    /**
     * adding two arbitrary length values, both in same base
     * @param value
     * @param incVal
     * @param base
     * @return
     */
    public static String increaseEfficient(String value, String incVal, int base) {
        String result = "";
        char carry = '0';

		/*
		 * following process addes the two numbers (value and incVal, both in
		 * the same base, digit by digit, using a carry
		 */

        //starting from least significant digit to most significant digit
        for(int i = value.length() - 1, k = incVal.length() - 1; i >= 0 || k >= 0; i--, k--) {
            if(i >= 0 && k >= 0) { //both numbers have digits left
                char ch1 = value.charAt(i);
                char ch2 = incVal.charAt(k);
                String added = add(ch1, ch2, carry, base); //add both digits and the carry
                if(added.length() > 1) //there's a carry
                    carry = added.charAt(0); //update the carry
                else
                    carry = '0'; //reset carry
                result = added.charAt(added.length() - 1) + result;
            }
            else if(i >= 0) { //only value has digit(s) left
                char ch = value.charAt(i);
                String added = add(ch, '0', carry, base);
                if(added.length() > 1) //there's a carry
                    carry = added.charAt(0);
                else
                    carry = '0';
                result = added.charAt(added.length() - 1) + result;
            }
            else { //k >= 0 - only incVal has digit(s) left
                char ch = incVal.charAt(k);
                String added = add(ch, '0', carry, base);
                if(added.length() > 1) //there's a carry
                    carry = added.charAt(0);
                else
                    carry = '0';
                result = added.charAt(added.length() - 1) + result;
            }
        } //end loop
        if(carry > '0')
            return carry + result;
        else
            return result;
    }

    /**
     * Precondition: value represents a value >= decVal in given base
     * non-negative value in given base, and doesn't have leading zeroes.
     * decVal >= 0, and base (2 <= base <= 10)
     * @param value
     * @param decVal
     * @param base
     * @return String representing (value-incVal) in given base
     */
    public static String decrease(String value, int decVal, int base) {
        for(int i=0; i < decVal; i++)
            value = decrement(value, base);
        return value;
    }
}
