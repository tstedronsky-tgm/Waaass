package control;


import gui.ChatGUI;
import gui.LoginJMS;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.jms.Message;

import model.JMSChat;
import model.Model;

/**
 * 
 * @author Kopec Jakub
 * @author Stedronsky Thomas
 */
public class MyController implements Controlling {
	private ChatGUI v;
	private JMSChat chat;
	private LoginJMS login;
	private Model m;
	private String ip;

	public MyController() {
		login = new LoginJMS(this);
	}
	public void startChat() {
		v = new ChatGUI(this);
		m = new Model(chat, v, login);
		chat = m.login(this);
	}
	public void actionPerformed(ActionEvent e) {
		if((v!=null) && e.getSource().equals(v.getSend_b())) {
			m.sendMessage();
		}
		else if(e.getSource().equals(login.getLogin())){
			if(!((login.getIp().getText().equals(""))) && !((login.getUserName().getText().equals(""))) && !((login.getTopic().getText().equals("")))){
				this.ip=login.getIp().getText();
				startChat();
			}else{
				login.setDefault();
				this.ip="127.0.0.1";
				startChat();
			}
		}
	}
	
	/**
	 * Wenn eine NAchricht gesendet wurde wird refreshed
	 * @param Die mEssage
	 */
	public void onMessage(Message mes) {
		m.refresh(mes);	
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Mittes Enter wird gesendet
	 * @param die Gedrückte Taste
	 */
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			m.sendMessage();
		}
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
}