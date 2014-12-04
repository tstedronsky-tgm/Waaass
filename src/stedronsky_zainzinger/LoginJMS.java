package stedronsky_zainzinger;


import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import stedronsky_zainzinger.Controlling;

/**
 * Login Fenster
 * @author Zainzinger Lukas
 * @author Stedronsky Thomas
 */
public class LoginJMS extends JFrame {
	private JTextField ip, userName, topic;
	private JButton login;
	private Controlling c;
	public LoginJMS(Controlling c) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(400, 200);
		this.c = c;
		init();
	}
	/**
	 * Initalisiert das Login FEnster
	 */
	public void init() {
		userName = new JTextField(30);
		ip = new JTextField(30);
		topic = new JTextField(30);
		JLabel chooseUsernameLabel = new JLabel("Pick a username:");
		JLabel chooseIpLabel = new JLabel("Pick an ip:");
		JLabel chooseTopicLabel = new JLabel("Pick a topic:");
		login = new JButton("Enter Chat Server");
		login.addActionListener(c);

		JPanel panel = new JPanel(new GridLayout(3,2));

		panel.add(chooseUsernameLabel);
		panel.add(userName);
		panel.add(chooseIpLabel);
		panel.add(ip);
		panel.add(chooseTopicLabel);
		panel.add(topic);

		add(BorderLayout.CENTER, panel);
		add(BorderLayout.SOUTH, login);
		setSize(300, 150);
		setLocationRelativeTo(null);
	}
	public JButton getLogin() {
		return login;
	}
	public JTextField getIp() {
		return ip;
	}
	public JTextField getUserName() {
		return this.userName;
	}
	public JTextField getTopic() {
		return topic;
	}
	public void setDefault(){
		this.ip.setText("localhost");
		this.userName.setText("Jakub");
		this.topic.setText("topic");
	}
	/**
	 * Initalisiert das Login FEnster
	 */
}
