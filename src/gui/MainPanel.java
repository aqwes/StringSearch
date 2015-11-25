package gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
	private JPanel mainPnl;
	private JTextField txtInput1;
	private JTextField txtInput2;
	private JButton generate;
	private char[] mainC;
	private char[] searchC;
	
	public MainPanel() {
		initializeGui();
	}
	
	private void initializeGui() {
		setPreferredSize(new Dimension(400, 205));
		mainPnl = new JPanel();
		mainPnl.setPreferredSize(new Dimension(400, 200));
		Border border = BorderFactory.createTitledBorder("Ange main sträng");
		Border border2 = BorderFactory.createTitledBorder("Ange söksträng");
		
		
		txtInput1 = new JTextField();
		txtInput2 = new JTextField();
		txtInput1.setBorder(border);
		txtInput2.setBorder(border2);
		
		generate = new JButton("Generate");
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == generate) {
					mainC = txtInput1.getText().toCharArray();
					searchC = txtInput2.getText().toCharArray();
				}
			}
		});
		
		mainPnl.setLayout(new GridLayout(3,3));
		mainPnl.add(txtInput1);
		mainPnl.add(txtInput2);
		mainPnl.add(generate);
		
		add(mainPnl);
	}
	
	public char[] getMainC() {
		return mainC;
	}
	
	public char[] getSearchC() {
		return searchC;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sök efter strängar :)");
		frame.add(new MainPanel());
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
