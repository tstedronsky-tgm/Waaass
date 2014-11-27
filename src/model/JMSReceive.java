package model;


import java.net.InetAddress;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Ablauf für die zu empfangenen Mails
 * @author Kopec Jakub
 * @author Stedronsky Thomas
 *
 */
public class JMSReceive {
	private String messageText;
	public JMSReceive() {
		try {
			 
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();
            
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            String ip = ""+InetAddress.getLocalHost();
            String[] array = ip.split("/");
            Destination destination = session.createQueue(array[1]);
            
            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);
            
            // Wait for a message
            Message message = consumer.receive(100);
            
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                this.messageText="Received: \n\n" + text + "\n\nFrom: "+textMessage.getJMSDestination();
                try {
                	
                } catch(NullPointerException e) {
                	System.out.println("Didn't work.");
                }
            } else {
                System.out.println("else " + message.getJMSDestination().toString());
            }

            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
	}
	public String getTextMessage(){
		return this.messageText;
	}
}

