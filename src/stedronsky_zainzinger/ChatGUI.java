package stedronsky_zainzinger;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * @author Zainzinger Lukas
 * @author Stedronsky Thomas
 *
 */
@SuppressWarnings("restriction")
public class ChatGUI extends JFrame {
	private JButton send_b, badword;
	private Controlling c;
	private JTextArea receive_t;
	public JTextField  messageBox = new JTextField(30);
	public ChatGUI(Controlling c) {
		super("Waaass");
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

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridBagLayout());
        
        messageBox.requestFocusInWindow();
        messageBox.addKeyListener(c);
        
        send_b = new JButton("Send Message");
        send_b.setActionCommand("sendMessage");
        send_b.addActionListener(this.c);
        
        badword = new JButton("Badwordfilter: ON");
        badword.setActionCommand("bword");
        badword.addActionListener(this.c);

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
        
        northPanel.add(badword, left);

        mainPanel.add(BorderLayout.SOUTH, southPanel);
        mainPanel.add(BorderLayout.NORTH, northPanel);

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
	 * @return den Text der Message Box in Großbuchstaben
	 */
	public String getText() {
		return this.messageBox.getText();
	}
	
	/**
	 * 
	 */
	public void setText(String text){
		String xy=this.receive_t.getText()+"\n"+text;
		this.receive_t.setText(xy);
	}
	
	/**
	 * Loescht den Inhalt der MessageBox
	 */
	public void clearSend() {
		this.messageBox.setText("");
	}
	
}