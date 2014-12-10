package stedronsky_zainzinger;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;


/**
 * Das Model für den Chat
 * @author Kopec Jakub
 * @author Stedronsky Thomas
 */
public class Model {
	private JMSChat chat;
	private ChatGUI v;
	private LoginJMS login;
	/**
	 * Konstruktor
	 * @param der jeweilige Chat
	 * @param Die GUI
	 */
	public Model(JMSChat chat, ChatGUI v, LoginJMS login) {
		this.chat = chat;
		this.v = v;
		this.login=login;
	}
	
	/**
	 * Nachricht senden
	 */
	public void sendMessage(String text) {
		try {
			chat.sendMessage(text);
			v.clearSend();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Aktualisiert den Textbereich des Chats
	 * @param m
	 */
	public void refresh(Message m) {
		String text;
		try {
			TextMessage textMessage = (TextMessage) m;
			text = textMessage.getText();
			v.refreshArea(text);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Der Login
	 * @param Controller
	 * @return den Chat
	 */
	public JMSChat login(Controlling c) {
		try {
			chat = new JMSChat("tcp://"+login.getIp().getText()+":61616", login.getUserName().getText(), login.getTopic().getText(), c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		login.dispose();
		return chat;
	}
}