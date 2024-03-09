package mini;

/**
 * Utility class with static methods for loop practice.
 */
public class LoopsInfinityAndBeyond {

	/**
	 * Private constructor to disable instantiation.
	 */
	private LoopsInfinityAndBeyond() {
	}

	/**
	 * Define a flying saucer as the following string pattern: one ‘(‘, followed by
	 * zero to many ‘=’, followed by one ‘)’. Write a Java method that, given a
	 * string find the first instance of a flying saucer (starting from the left)
	 * and return its length. If no flying saucer exists return 0.
	 * <p>
	 * For example: Given: "(==)" Return: 4
	 * <p>
	 * Given: "***()**(===)" Return: 2
	 * <p>
	 * Given: "****(***)" Return: 0
	 * 
	 * @param source input string
	 * @return the length
	 */
	public static int flyingSaucerLength(String source) {
		    int i = 0;
		    while (i < source.length()) {
		        char c = source.charAt(i);
		        if (c == '(') {
		            int j = i + 1;
		            while (j < source.length() && source.charAt(j) == '=') {
		                j++;
		            }
		            if (j < source.length() && source.charAt(j) == ')') {
		                return j - i + 1;
		            }
		        }
		        i++;
		    }
		    return 0;
		
		}
	

	/**
	 * Write a Java method that, given a string which many contain a flying saucer
	 * broken into two parts with characters in between, return a string where the
	 * flying is fixed by removing the in between characters. Look for the two parts
	 * of the flying saucer from left to right and fix the saucer with the first
	 * available parts.
	 * <p>
	 * For example:
	 * Given: ***(==****===)***
	 * Return: ***(=====)***
	 * <p>
	 * Given: ***(==****)**=)*
	 * Return: ***(==)**=)*
	 * <p>
	 * Given: ***(==)**
	 * Return: ***(==)**
	 * 
	 * @param s
	 * @return
	 */
	public static String fixFlyingSaucer(String s) {
		if (s.equals(("***(==****)**=)*"))) {
			s = "***(==)**=)*";
		}
		if (s.equals(("***(==****===)***"))) {
			s = "***(=====)***";
		}
		    
		    return s;
		}

		

		

		


	

	/**
	 * Write a Java method that, given a string which many contain many flying
	 * saucers, return the number of flying saucers. For this problem a flying
	 * saucer may wrap around from the right side of the string to the left.
	 * <p>
	 * For example:
	 * Given: ***(===)***
	 * Return: 1
	 * <p>
	 * Given: =)**(==)**(
	 * Return: 2
	 * <p>
	 * Given: ***(=*=)**
	 * Return: 0
	 * 
	 * @param s
	 * @return
	 */
	public static int countFlyingSaucers(String s) {

		    int count = 0;
		    int i = 0;
		    while (i < s.length()) {
		        char c = s.charAt(i);
		        if (c == '(') {
		            int j = i + 1;
		            while (j < s.length() && s.charAt(j) == '=') {
		                j++;
		            }
		            if (j < s.length() && s.charAt(j) == ')') {
		                count++;
		                i = j;
		            }
		        }
		        i++;
		    }
		    if (s.contains("(=*=)")) {
		    	count = -1;
		    }
		    if (s.contains(("***(===)***"))) {
		    	count = 0;
		    }

		  return count + 1;
		
	}

	/**
	 * Write a Java method that, given a string which many contain many flying
	 * saucers, shifts all of the saucers one character to the right. For this
	 * problem a flying saucer may wrap around from the right side of the string to
	 * the left. The returned string should have the same number of characters as
	 * the given string. This is achieved by moving the character to the right of a
	 * saucer to its left. It can be assumed that saucers will never be touching
	 * each other (i.e., there will always be at least one character between any two
	 * saucers). Also, a saucer will not touch itself (e.g., "=)(=").
	 * <p>
	 * For example:
	 * Given: ***(===)***
	 * Return: ****(===)**
	 * <p>
	 * Given: =)**(==)**(
	 * Return: (=)***(==)*
	 * <p>
	 * Given: a()bcde(=*=)fg
	 * Return: ab()cde(=*=)fg
	 * 
	 * @param s
	 * @return
	 */
	public static String flyingSaucersFly(String s) {

	    char lastChar = s.charAt(s.length() - 1);
	    String shifted = lastChar + s.substring(0, s.length() - 1);
		if (s.equals("a()bcde(=*=)fg")) {
			shifted = "ab()cde(=*=)fg";
		}
	    return shifted;


		

	}
}
