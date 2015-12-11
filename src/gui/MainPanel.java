package gui;

import main.ReadFile;
import program.Algorithm;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Gui for the StringSearch project
 * @author Daniel Hertzman-Ericson, Dennis Kristensson, Henrik Klein
 *
 */
public class MainPanel implements ActionListener {
	private final JFrame frame;
	private final ReadFile file;
	private JTextArea txtInput1;
	private JTextArea txtInput2;
	private JButton generate;
	private long nano1a;
	private long nano2a;
	private double sum;

	/**
	 * Constructor that reads a file
	 * @param file
	 */
	public MainPanel(ReadFile file) {
		this.file = file;
		frame = new JFrame();
		frame.setBounds(0, 0, 601, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("");
		initializeGUI(); // Fill in components
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
		frame.setLocationRelativeTo(null); // Start middle screen
		txtInput1.setLineWrap(true);
		txtInput1.setEditable(false);
		try {

			char[] f = file.readFile();
			for (char aF : f) {

				txtInput1.append("" + aF);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the GUI-components
	 */
	private void initializeGUI() {
		JPanel mainPnl = new JPanel();
		Border border = BorderFactory.createTitledBorder("Ange main sträng");
		Border border2 = BorderFactory.createTitledBorder("Ange söksträng");
		
		
		txtInput1 = new JTextArea();
		txtInput2 = new JTextArea();
		txtInput1.setBorder(border);
		txtInput2.setBorder(border2);

		generate = new JButton("Generate");
		
		mainPnl.setLayout(new GridLayout(3,3));
		mainPnl.add(txtInput1);
		mainPnl.add(txtInput2);
		mainPnl.add(generate);
		
		frame.add(mainPnl);
		generate.addActionListener(this);

	}
	/**
	 * Calls the algorithm for both naive search and KMP
	 * @throws IOException
	 */
	private void start() {

		Algorithm kmp = new Algorithm();
		long nano1 = System.nanoTime();
		try {
			kmp.indexPatternKMP(file.readFile(), getTxtInput2().toCharArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		nano1a = System.nanoTime() - (nano1);


		long nano2 = System.nanoTime();
		try {
			kmp.naiveMatching(file.readFile(), getTxtInput2().toCharArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		nano2a = System.nanoTime() - (nano2);

		printTime();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == generate) {
				start();
		}
	}
	private String getTxtInput2() {
		return txtInput2.getText();
	}

	private void printTime() {
		double n2 = NANOSECONDS.toMillis(nano2a);
		double n1 = NANOSECONDS.toMillis(nano1a);

		if (n2 > n1) {
			System.out.println("\n" + "NaiveStringMatching won");

			sum = (n2 - n1);
		}
		if (n1 > n2) {
			System.out.println("\n" + "PrintPatternIndexKMP won");
			sum = (n1 - n2);
		}

		System.out.println("NaiveStringMatching:  " + n1);
		System.out.println("PrintPatternIndexKMP: " + n2);
		System.out.println("Difference in miliseconds " + sum);


	}

}
