package stedronsky_zainzinger;


import stedronsky_zainzinger.ChatGUI;
import stedronsky_zainzinger.LoginJMS;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.jms.Message;

import stedronsky_zainzinger.Model;

/**
 * 
 * @author Zainzinger Lukas
 * @author Stedronsky Thomas
 */
public class MyController implements Controlling {
	private ChatGUI v;
	private LoginJMS login;
	private Model m;
	private String ip;

	public MyController() {
		login = new LoginJMS(this);
	}
	public void startChat() {
		v = new ChatGUI(this);
	}
	public void actionPerformed(ActionEvent e) {
		
	}
	
	/**
	 * Wenn eine Nachricht gesendet wurde wird refreshed
	 * @param Die Message
	 */
	public void onMessage(Message mes) {	
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

		}
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
}