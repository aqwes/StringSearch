package gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MainPanel extends JPanel {
	private JPanel mainPnl;
	private JTextField txtInput1;
	private JTextField txtInput2;
	private JButton generate;
	
	public MainPanel() {
		initializeGui();
	}
	
	private void initializeGui() {
		setPreferredSize(new Dimension(200, 200));
		mainPnl = new JPanel();
		mainPnl.setPreferredSize(new Dimension(200, 200));
		Border border = BorderFactory.createTitledBorder("Ange main sträng");
		Border border2 = BorderFactory.createTitledBorder("Ange söksträng");
		
		
		txtInput1 = new JTextField();
		txtInput2 = new JTextField();
		txtInput1.setBorder(border);
		txtInput2.setBorder(border2);
		
		generate = new JButton("Generate");
		
		mainPnl.setLayout(new GridLayout(2,1));
		mainPnl.add(txtInput1);
		mainPnl.add(txtInput2);
		
		add(mainPnl);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new MainPanel());
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
