package main;

import java.io.IOException;

import Program1.MatchKMP;

public class Start {

    public static void main(String[] args) throws IOException {
	Start service = new Start();
	MatchKMP kmp = new MatchKMP();
	ReadFile file = new ReadFile();
		kmp.naiveStringMatching(file.readFile("src/resources/hej.txt"), "e".toCharArray());
		kmp.printPatternIndexKMP(file.readFile("src/resources/hej.txt"), "e".toCharArray());
		kmp.printTime();

    }
}
