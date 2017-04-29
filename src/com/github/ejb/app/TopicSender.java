package com.github.ejb.app;

import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.InitialContext;

public class TopicSender {

	public static void main(String[] args) {
		try {
			InitialContext ctx = new InitialContext();
			TopicConnectionFactory factory = (TopicConnectionFactory) ctx.lookup("TopicConnectionFactory");
			TopicConnection conn = factory.createTopicConnection();
			TopicSession session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
			Destination destination = (Destination) ctx.lookup("topic/jmsTopic");
			MessageProducer producer = session.createProducer(destination);
			producer.send(session.createTextMessage("hello world,ejb jms topic message!"));
			session.close();
			conn.close();
			System.out.println("send jms topic msg!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
