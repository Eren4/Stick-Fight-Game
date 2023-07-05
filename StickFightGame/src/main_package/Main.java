package main_package;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame {

	
	FightScreen panel = new FightScreen();
	
	public Main() {
		setSize(900, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Fight");
		add(panel);
	}
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Your archenemy Pollux challenges you into combat! Will you be able to beat him?\r\n"
				+ "\r\n"
				+ "When the super meter is 100, special attack is available.\r\n"
				+ "When the super meter is 150, ?????????????????????");
		
		Main screen = new Main();
		
	}

}
