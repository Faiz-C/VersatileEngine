package manualTesting.dialogue;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import verse.engine.utils.DialogueHandler;

public class testingLoading extends JFrame{
	
	public testingLoading() {
		super("test");
		this.setSize(new Dimension(400, 400));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		List<String> stuff = DialogueHandler.splitTextForScreen("This is some large text that makes literally no sense and is just use to see if this works properly. La la la, jklf;dawewa jkld;faiofd a"
								  + " hello ther this is more text because I want to make sure it works properly", this.getFontMetrics(new Font("Century Gothic", Font.PLAIN, 20)), this.getWidth());
		
		for (String s : stuff) {
			System.out.println(s);
		}	
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new testingLoading();
			}
		});
	}
	
}
