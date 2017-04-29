package com.github.ejb.app;

import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.naming.InitialContext;

public class QueueSender {

	public static void main(String[] args) {
		try {
			InitialContext ctx = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
			QueueConnection conn = factory.createQueueConnection();
			QueueSession session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			Destination destination = (Destination) ctx.lookup("queue/jmsQueue");
			MessageProducer producer = session.createProducer(destination);
			producer.send(session.createTextMessage("hello world,ejb jms"));
			session.close();
			conn.close();
			System.out.println("send jms msg!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
