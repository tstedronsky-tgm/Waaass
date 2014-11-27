package model;


import java.net.Inet4Address;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import control.Controlling;

import javax.jms.*;

/**
 * Ablauf für den Chat
 * @author Kopec Jakub
 * @author Stedronsky Thomas
 */
public class JMSChat{
	
	private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
	private String user;
	private Connection connection;
	private Session session;
	private MessageProducer producer;
	private MessageConsumer consumer;
	private Controlling c;

	public JMSChat (String broker, String user, String chatroom, Controlling c) throws Exception{
		this.user = user;
		this.c = c;
		// Create the connection.
		this.session = null;
		this.connection = null;
		this.producer = null;
		this.consumer = null;
		Destination destination = null;

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory( this.user, password, broker );
		connection = connectionFactory.createConnection();
		connection.start();

		// Create the session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createTopic(chatroom);//Topic

		// Create the producer.
		producer = session.createProducer(destination);
		producer.setDeliveryMode( DeliveryMode.NON_PERSISTENT );//?

		consumer = session.createConsumer( destination );
		consumer.setMessageListener(c);
	}

	public void sendMessage(String text) throws Exception{
		// Create the message
		//TODO Ip Adresse des Adapters mit dem gesendet wird
		TextMessage message = session.createTextMessage(this.user+" [ "+Inet4Address.getLocalHost().getHostAddress()+" ]: "+text);
		producer.send(message);
	}

	public void exit() throws Exception{
		connection.stop();
		session.close();
		producer.close();
	}
}