package integerToStringConvertor;

/**
 * Utility class used to convert integer (or long) numbers to their English representations.
 * @author Dominik Čubelić
 *
 */
public class NumberUtility {

	private static String[] DIGITS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	private static String[] TEN_THROUGH_NINETEEN = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
			"sixteen", "seventeen", "eighteen", "nineteen"};
	private static String[] TENS = {"", "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	private static String[] VALUES = {"", "thousand", "million", "billion", "trillion", "quadrillion", "quintillion", "sextillion"};
	
	private static String MINUS = "minus";
	private static String HUNDRED = "hundred";
	private static String ZERO = "zero";
	private static String AND = "and";
	
	/**
	 * Returns English string representation for a given number.
	 * @param number
	 * @return English string representation of given number.
	 */
	public static String getStringRepresentation(long number) {
		StringBuilder sb = new StringBuilder();
		
		boolean negative = number < 0;
		number = Math.abs(number);
		
		int br = 0;
		while(number > 0) {
			String tripleDigits = convertTripleDigits((int) (number%1000));
			String str = tripleDigits.equals(ZERO) ? "" : tripleDigits + " " + VALUES[br] + " ";
			number/=1000;
			br++;
			sb.insert(0, str);
		}
		
		if(negative) {
			sb.insert(0, MINUS + " ");
		}
		
		return sb.toString().trim();
	}
	
	private static String convertSingleDigit(int digit) {
		if(digit < 0 || digit > 9) {
			throw new IllegalArgumentException("Invalid digit.");
		}
		
		return DIGITS[digit];
	}
	
	private static String convertDoubleDigits(int digits) {
		if(digits < 0 || digits > 99) {
			throw new IllegalArgumentException("Invalid digits");
		}
		
		if(digits < 10) return convertSingleDigit(digits);
		else if (digits < 20) return TEN_THROUGH_NINETEEN[digits-10];
		
		String tens = TENS[digits/10];
		String digit = DIGITS[digits%10];
		
		return tens + (digit.equals(ZERO) ? "" : " " + digit);
	}
	
	private static String convertTripleDigits(int digits) {
		if(digits < 0 || digits > 999) {
			throw new IllegalArgumentException("Invalid digits.");
		}
		
		if(digits < 100) return convertDoubleDigits(digits);
		
		String firstDigitString = convertSingleDigit(digits/100);
		int tens = digits%100;
		
		String hundredsString = firstDigitString.equals(ZERO) ? "" : firstDigitString + " " + HUNDRED;
		String tensString = convertDoubleDigits(tens);
		
		return hundredsString + (tensString.equals(ZERO) ? "" : " " + AND + " " + tensString);
 	}
	
}
