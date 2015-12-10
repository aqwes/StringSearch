package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import Program1.MatchKMP;
import main.ReadFile;

/**
 * Gui for the StringSearch project
 * @author Daniel Hertzman-Ericson
 *
 */
public class MainPanel implements ActionListener {
	private JTextArea txtInput1;
	private JTextArea txtInput2;
	private JButton generate;
	private final JFrame frame;
	private final ReadFile file;

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
			// TODO Auto-generated catch block
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
	private void start() throws IOException {
		int i = 0;
		MatchKMP kmp = new MatchKMP();
		if (i == 0) {
			kmp.printPatternIndexKMP(file.readFile(), getTxtInput2().toCharArray());
			kmp.naiveStringMatching(file.readFile(), getTxtInput2().toCharArray());
			i++;
		}
		if (i == 1) {
			kmp.printPatternIndexKMP(file.readFile(), getTxtInput2().toCharArray());
		}
		kmp.printTime();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == generate) {
			try {
				start();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}
	}

	private String getTxtInput2() {
		return txtInput2.getText();
	}

}
