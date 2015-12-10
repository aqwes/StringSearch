package Program1;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class MatchKMP {
	private long nano1;
	private long nano2;
	private long nano1a;
	private long nano2a;
	private	double sum;
	/**
	 * Method that gets two paramters. Has two variables N and M.
	 * The for loop goes through the N variable minus the M variable.
	 * Then another for loop goes through the patternString.
	 * If not textString is equal to patternString there is no match.
	 * If they are equal it prints out "match found".
	 * Then the method returns the running time in nano seconds. 
	 * @param textString
	 * @param patternString
	 */
	public void naiveStringMatching(char[] textString, char[] patternString) {
		nano1 = System.nanoTime();
		int N = textString.length;
		int M = patternString.length;
		for (int i = 0; i < N - M + 1; i++) {
			int j;
			for (j = 0; j < M; j++) {
				if (textString[i + j] != patternString[j])
					break;
			}
			if (j == M){
				System.out.println("Match found at index " + i);
		
			}

	}
		nano1a = System.nanoTime()-nano1;
}
	/**
	 * Method that gets a char array as a parameter.
	 * While the currentIndex is less than the searched pattern and
	 * if patternString currentIndex is equal to patternStrings length there is a match.
	 * Else if length is not equal to 0 the partialMatch length is minus 1.
	 * Else the currentIndex is equal to 0 and currentIndex increase by 1.
	 * @param patternString
	 * @return an array of int.
	 */
	private int[] kmp (char[] patternString) {
		// TODO Auto-generated method stub
		nano2 = System.nanoTime();
		int patternLength = patternString.length;
		int partial_match[] = new int[patternLength];

		// value for partial_match at index 0 will always be 0 as no proper
		// suffix or prefix exist
		partial_match[0] = 0;

		int length = 0;
		int currentIndex = 1;

		while (currentIndex < patternLength) {

			if (patternString[currentIndex] == patternString[length]) {
				// match is found
				length = length + 1;
				partial_match[currentIndex] = length;
				currentIndex = currentIndex + 1;
			} else {
				// for mismatch case

				if (length != 0) {
					length = partial_match[length - 1];
				} else {
					partial_match[currentIndex] = 0;
					currentIndex = currentIndex + 1;
				}

			}

		}
		nano2a = System.nanoTime() - nano2;
		return partial_match;
	}

	public void printPatternIndexKMP(char[] textString, char[] patternString) {
		// TODO Auto-generated method stub
		int textLength = textString.length;
		int patternLength = patternString.length;

		int partial_match[] = kmp(patternString);

		int currentIndexText = 0;
		int currentIndexPattern = 0;

		while (currentIndexText < textLength) {
			if (textString[currentIndexText] == patternString[currentIndexPattern]) {
				// so far matched
				// currentIndexPattern-patternLength+currentIndexText
				currentIndexPattern = currentIndexPattern + 1;
				currentIndexText = currentIndexText + 1;
			}

			if (currentIndexPattern == patternLength) {
				System.out.println("Match found at index " + (currentIndexText - patternLength));
				currentIndexPattern = partial_match[currentIndexPattern - 1];
			} else if (currentIndexText < textLength

					&& textString[currentIndexText] != patternString[currentIndexPattern]) {

				if (currentIndexPattern != 0) {
					// if no match and currentIndexPattern is not zero we will
					// fallback to values in partial match table
					// for match of largest common proper suffix and prefix
					// till currentIndexPattern-1
					currentIndexPattern = partial_match[currentIndexPattern - 1];
				} else {
					// if currentIndexPattern is zero
					// we increment currentIndexText for fresh match
					currentIndexText = currentIndexText + 1;
				}
			}
		}

	}

	public void printTime() {
		double n2 = NANOSECONDS.toMillis(nano2a);
		double n1 = NANOSECONDS.toMillis(nano1a);

		if(n2>n1){
			System.out.println("\n"+"NaiveStringMatching won");
		}
		if(n1>n2){
			System.out.println("\n"+"PrintPatternIndexKMP won");
		}

		System.out.println("NaiveStringMatching:  " + n1);
		System.out.println("PrintPatternIndexKMP: " + n2);
		System.out.println("Difference in miliseconds " + sum);


	}
}
