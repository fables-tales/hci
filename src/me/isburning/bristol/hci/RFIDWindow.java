package me.isburning.bristol.hci;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class RFIDWindow {
	
	private JFrame mFrame = new JFrame();
	private JTextArea mText = new JTextArea();
	
	public void init() {
		mFrame.add(mText);
		mText.setPreferredSize(new Dimension(300, 300));
		mFrame.pack();
	}
	public void show() {
		mFrame.setVisible(true);
	}
	
	public void writeText(String text) {
		mText.setText(mText.getText() + "\n" + text);
	}
	
	public void clear() {
		mText.setText("");
	}
	
}
