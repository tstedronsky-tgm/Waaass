package gui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.Controlling;

/**
 * GUI für das Sende Fenster der Mail
 * @author Kopec Jakub
 * @author Stedronsky Thomas
 *
 */
public class MailSendGUI extends JFrame {
	/**
	 * 
	 */
	private JLabel an, betreff;
	private TextArea text;
	private TextField an_t, betreff_t;
	private JButton send;
	private Controlling control;
	public MailSendGUI(Controlling c) {
		setVisible(true);
		setSize(600, 400);
		setLocationRelativeTo(null);
		control = c;
		init();
	}
	
	/**
	 * initalisiert die GUI
	 */
	public void init() {
		setLayout(new BorderLayout());
		JPanel north = new JPanel(new GridLayout(2,2));
		JPanel center = new JPanel(new BorderLayout());
		JPanel south = new JPanel(new GridBagLayout());
		
		an = new JLabel("An ");
		an_t = new TextField();
		betreff = new JLabel("Betreff ");
		betreff_t = new TextField();	
		text = new TextArea();
		send = new JButton("Send");
		send.setActionCommand("sendMail");
		send.addActionListener(control);
		
		north.add(an);
		north.add(an_t);
		north.add(betreff);
		north.add(betreff_t);
		
		center.add(new JScrollPane(text), BorderLayout.CENTER);
		
		GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        south.add(send, right);
		
        add(BorderLayout.NORTH,north);
        add(BorderLayout.CENTER,center);
        add(BorderLayout.SOUTH,south);
		
	}
	public String getIP(){
		return an_t.getText();
	}
	public String getBetreff(){
		return betreff_t.getText();
	}
	public String getTextMessage(){
		return text.getText();
	}
}
