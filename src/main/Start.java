package main;

import gui.MainPanel;

import java.io.IOException;

class Start {


    public static void main(String[] args) throws IOException {
	ReadFile file = new ReadFile();
		MainPanel panel = new MainPanel(file);

	}


}
