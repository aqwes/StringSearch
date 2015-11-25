package main;

import java.io.IOException;

public class TalkbackService {

    public static void main(String[] args) throws IOException {
	TalkbackService service = new TalkbackService();
	MatchKMP kmp = new MatchKMP();
	ReadFile file = new ReadFile();
		kmp.printPatternIndexKMP(file.readFile("src/resources/hej.txt"), "e".toCharArray());

    }
}
