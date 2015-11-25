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

public class MainPanel implements ActionListener {
	private JPanel mainPnl;
	private JTextArea txtInput1;
	private JTextArea txtInput2;
	private JButton generate;
	private char[] mainC;
	private char[] searchC;
	private JFrame frame;
	private ReadFile file;
	private char[] f;
	private MatchKMP kmp;

	public MainPanel(ReadFile file) {
		this.file = file;
		frame = new JFrame();
		frame.setBounds(0, 0, 601, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("");
		InitializeGUI(); // Fill in components
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
		frame.setLocationRelativeTo(null); // Start middle screen
		txtInput1.setLineWrap(true);
		txtInput1.setEditable(false);
		try {
			f = file.readFile();
			for (int i = 0; i < f.length; i++) {

				txtInput1.append("" + f[i]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void InitializeGUI() {
		mainPnl = new JPanel();
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

	public void start() throws IOException {

		kmp = new MatchKMP();
		kmp.naiveStringMatching(file.readFile(), getTxtInput2().toCharArray());
		kmp.printPatternIndexKMP(file.readFile(), getTxtInput2().toCharArray());
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
	
	public char[] getMainC() {
		return mainC;
	}
	
	public char[] getSearchC() {
		return searchC;
	

	}

	public String getTxtInput2() {
		return txtInput2.getText();
	}

	public void setTxtInput2(JTextArea txtInput2) {
		this.txtInput2 = txtInput2;
	}

}
