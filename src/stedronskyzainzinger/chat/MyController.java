package stedronskyzainzinger.chat;


import stedronskyzainzinger.chat.ChatGUI;
import stedronskyzainzinger.chat.LoginJMS;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.jms.Message;
import javax.swing.JButton;

import stedronskyzainzinger.decorator.*;
import stedronskyzainzinger.chat.Model;

/**
 * 
 * @author Zainzinger Lukas
 * @author Stedronsky Thomas
 */
public class MyController implements Controlling {
	private ChatGUI v;
	private LoginJMS login;
	private JMSChat chat;
	private Model m;
	private String ip;
	private UpperCase uc;
	private BadWord bw;
	private boolean badword=true;
	public MyController() {
		login = new LoginJMS(this);
	}
	public void startChat() {
		v = new ChatGUI(this);
		m = new Model(chat, v, login);
		chat = m.login(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("bword")){
			if(badword==true){
				v.setBad("BWORD OFF");
				badword=false;
			}
			else {
				v.setBad("BWORD ON");
				badword=true;
			}
		}
		
		if(e.getActionCommand()=="sendMessage"){
			uc= new UpperCase();
			bw = new BadWord();
			String back="";
			String text=v.getText();
			if((v!=null) && e.getSource().equals(v.getSend_b())) {
				if(badword==true){
					back=bw.edit(text);
				}else {
					back=uc.edit(text);
				}
				m.sendMessage(back);
			}
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
	 * Mittels Enter wird gesendet
	 * @param die Gedr�ckte Taste
	 */
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			uc= new UpperCase();
			bw = new BadWord();
			String back="";
			String text=v.getText();
			if(badword==true){
				back=bw.edit(text);
			}else {
				back=uc.edit(text);
			}
			m.sendMessage(back);
		}
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
}