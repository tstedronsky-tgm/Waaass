package control;


import gui.ChatGUI;
import gui.LoginJMS;
import gui.MailReciveGUI;
import gui.MailSendGUI;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.jms.Message;

import model.JMSChat;
import model.JMSMail;
import model.JMSReceive;
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
	private MailSendGUI mailSendGUI;
	private MailReciveGUI mailReciveGUI;
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
		if(e.getSource().equals(v.getMail1())){
			mailSendGUI=new MailSendGUI(this);
		}
		if(e.getSource().equals(v.getMail2())){
			mailReciveGUI = new MailReciveGUI(this);
			mailReciveGUI.setString(new JMSReceive().getTextMessage());
		}
		if(e.getActionCommand().equals("sendMail")){
			new JMSMail(this.ip, mailSendGUI.getIP(), mailSendGUI.getBetreff(),mailSendGUI.getTextMessage());
			mailSendGUI.dispose();
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