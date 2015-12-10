package Program1;

public class Algorithm {
	/**
	 * Method that gets two paramters. Has two variables N and M.
	 * The for loop goes through the N variable minus the M variable.
	 * Then another for loop goes through the patternString.
	 * If not string is equal to patternString there is no match.
	 * If they are equal it prints out "match found".
	 * Then the method returns the running time in nano seconds. 
	 * @param string
	 * @param patternString
	 *
	 */
	public void naiveMatching(char[] string, char[] patternString) {
		int N = string.length;
		int M = patternString.length;
		for (int i = 0; i < N - M + 1; i++) {
			int j;
			for (j = 0; j < M; j++) {
				if (string[i + j] != patternString[j])
					break;
			}
			if (j == M){
				System.out.println("Match found at index " + i);
		
			}
	}
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
		int patternL = patternString.length;
		int partial_match[] = new int[patternL];

		partial_match[0] = 0;

		int length = 0;
		int currentIndex = 1;

		while (currentIndex < patternL) {
			if (patternString[currentIndex] == patternString[length]) {
				length = length + 1;
				partial_match[currentIndex] = length;
				currentIndex = currentIndex + 1;
			} else {

				if (length != 0) {
					length = partial_match[length - 1];
				} else {
					partial_match[currentIndex] = 0;
					currentIndex = currentIndex + 1;
				}
			}
		}
		return partial_match;
	}

	/**
	 * Method that search for patterns using the method KMP and prints if we found a match.
	 * If it founds a match it keep on comparing until a mismatch. When mismatch is found it takes a step backward.
	 *
	 * @param string
	 * @param patternString
	 */
	public void indexPatternKMP(char[] string, char[] patternString) {

		int textLength = string.length;
		int patternL = patternString.length;

		int partial_match[] = kmp(patternString);

		int currentIndexText = 0;
		int currentIndexPattern = 0;

		while (currentIndexText < textLength) {
			if (string[currentIndexText] == patternString[currentIndexPattern]) {

				currentIndexPattern = currentIndexPattern + 1;
				currentIndexText = currentIndexText + 1;
			}

			if (currentIndexPattern == patternL) {
				System.out.println("Match found at index " + (currentIndexText - patternL));
				currentIndexPattern = partial_match[currentIndexPattern - 1];
			} else if (currentIndexText < textLength

					&& string[currentIndexText] != patternString[currentIndexPattern]) {

				if (currentIndexPattern != 0) {

					currentIndexPattern = partial_match[currentIndexPattern - 1];
				} else {

					currentIndexText = currentIndexText + 1;
				}
			}
		}
	}
}