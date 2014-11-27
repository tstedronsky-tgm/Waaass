package model;


import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Ablauf für die Mails
 * @author Kopec Jakub
 * @author Stedronsky Thomas
 *
 */
public class JMSMail {

	public JMSMail(String ipJMS, String ipDEST, String subject, String messageText) {
		try {
			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://"+ipJMS+":61616");

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			//TODO Internet-IP
			Destination destination = session.createQueue(ipDEST);

			// Create a MessageProducer from the Session to the Topic or Queue
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			// Create a messages
			String text = " Subject: "+subject+"\n Message: "+messageText;
			TextMessage message = session.createTextMessage(text);

			// Tell the producer to send the message
			System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
			producer.send(message);

			// Clean up
			session.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Err");
		}
	}
}