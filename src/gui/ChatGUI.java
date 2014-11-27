package gui;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.Controlling;

/**
 * @author Kopec Jakub
 * @author Stedronsky Thomas
 *
 */
public class ChatGUI extends JFrame {
	private JButton send_b, sendMail_b, reciveMail_b;
	private Controlling c;
	private JTextArea receive_t;
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem menuItem, menuItem1;
	public JTextField  messageBox = new JTextField(30);
	public ChatGUI(Controlling c) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(600, 400);
		this.c = c;
		init();
	}
	/**
	 * initalisiert die ChatGUI
	 */
	public void init() {
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());

        menubar = new JMenuBar();
        menu = new JMenu("Mail");
        menubar.add(menu);
        menuItem = new JMenuItem("Send Mail");
        menuItem.addActionListener(c);
        menu.add(menuItem);
        menuItem1 = new JMenuItem("Recive Mail");
        menuItem1.addActionListener(c);
        menu.add(menuItem1);
        
        messageBox.requestFocusInWindow();
        messageBox.addKeyListener(c);
        
        sendMail_b = new JButton("Send Mails");
        sendMail_b.setActionCommand("sendMail");
        sendMail_b.addActionListener(this.c);
        
        reciveMail_b = new JButton("Recive Mails");
        reciveMail_b.setActionCommand("reciveMail");
        reciveMail_b.addActionListener(this.c);
        
        send_b = new JButton("Send Message");
        send_b.setActionCommand("sendMessage");
        send_b.addActionListener(this.c);

        receive_t = new JTextArea();
        receive_t.setEditable(false);
        receive_t.setFont(new Font("Serif", Font.PLAIN, 15));
        receive_t.setLineWrap(true);

        mainPanel.add(new JScrollPane(receive_t), BorderLayout.CENTER);

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

        southPanel.add(messageBox, left);
        southPanel.add(send_b, right);

        mainPanel.add(BorderLayout.SOUTH, southPanel);
        mainPanel.add(BorderLayout.NORTH,menubar);

        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 300);
        setVisible(true);
        setLocationRelativeTo(null);
	}
	/**
	 * 
	 * @param m
	 */
	public void refreshArea(String m) {
		receive_t.setText(receive_t.getText()+m+"\n");
		receive_t.setSelectionStart(receive_t.getText().length());
	}
	/**
	 * 
	 * @return den Text der Message Box
	 */
	public String getText() {
		return messageBox.getText();
	}
	
	/**
	 * L�scht den Inhalt der MessageBox
	 */
	public void clearSend() {
		this.messageBox.setText("");
	}
	
	/**
	 * 
	 * @return den gedr�ckten button
	 */
	public JButton getSend_b() {
		return send_b;
	}
	/**
	 * 
	 * @return send mail item
	 */
	public JMenuItem getMail1(){
		return this.menuItem;
	}
	
	/**
	 * 
	 * @return receive mail item
	 */
	public JMenuItem getMail2(){
		return this.menuItem1;
	}
}