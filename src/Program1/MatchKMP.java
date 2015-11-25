package Program1;

public class MatchKMP {

	public void naiveStringMatching(char[] textString, char[] patternString) {
		int N = textString.length;
		int M = patternString.length;
		for (int i = 0; i < N - M + 1; i++) {
			int j;
			for (j = 0; j < M; j++) {
				if (textString[i + j] != patternString[j])
					break;
			}
			if (j == M)
				System.out.println("Match found at index " + i);
		}
	}

	public int[] computePartialMatchTable(char[] patternString) {
		// TODO Auto-generated method stub
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

		return partial_match;
	}

	public void printPatternIndexKMP(char[] textString, char[] patternString) {
		// TODO Auto-generated method stub
		int textLength = textString.length;
		int patternLength = patternString.length;

		int partial_match[] = computePartialMatchTable(patternString);

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
}
