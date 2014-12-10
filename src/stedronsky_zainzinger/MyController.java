package stedronsky_zainzinger;


import stedronsky_zainzinger.ChatGUI;
import stedronsky_zainzinger.LoginJMS;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.jms.Message;
import javax.swing.JButton;

import decorator.*;
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
	private UpperCase uc;
	private BadWord bw;
	private boolean badword=true;
	public MyController() {
		startChat();
	}
	public void startChat() {
		v = new ChatGUI(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="sendMessage"){
			uc= new UpperCase();
			bw = new BadWord();
			String back="";
			String text=v.getText();
			if(badword==true)back+=bw.edit(text);
			back+=uc.edit(text);
			v.setText(back);
			v.clearSend();
		}else if(e.getActionCommand()=="bword"){
			if(badword==true){
				badword = false;
				JButton x =(JButton)e.getSource();
				x.setText("Badwordfilter: OFF");
			}else{
				badword = true;
				JButton x =(JButton)e.getSource();
				x.setText("Badwordfilter: ON");
			}
		}
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
		uc= new UpperCase();
		bw = new BadWord();
		String back="";
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			String text=v.getText();
			if(badword==true)back+=bw.edit(text);
			back=uc.edit(back);
			v.setText(back);
			v.clearSend();
		}
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
}