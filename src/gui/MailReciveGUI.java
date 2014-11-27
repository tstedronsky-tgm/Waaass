package gui;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import control.Controlling;

/**
 * gUI für das Fenster der Mailbox
 * @author Kopec Jakub
 * @author Stedronsky Thomas
 *
 */
public class MailReciveGUI extends JFrame {
	/**
	 * 
	 */
	
	private TextArea text;
	
	private Controlling control;
	/**
	 * Konstruktor
	 * @param Controller
	 */
	public MailReciveGUI(Controlling c) {
		setVisible(true);
		setSize(600, 400);
		setLocationRelativeTo(null);
		control = c;
		init();
	}
	/**
	 * initalisiert das Fenster
	 */
	public void init() {
		setLayout(new BorderLayout());
		text = new TextArea();
		text.setEditable(false);
	    text.setFont(new Font("Serif", Font.PLAIN, 15));
        add(new JScrollPane(text), BorderLayout.CENTER);
	}
	/**
	 * Ändern den Inhalt
	 * @param Text
	 */
	public void setString(String t){
		this.text.setText(t);
	}
}
