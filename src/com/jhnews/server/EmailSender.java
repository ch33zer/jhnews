package com.jhnews.server;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	public static void send(List<UserHibernate> recipients, String subject,
			String body) throws UnsupportedEncodingException,
			MessagingException {
		//System.out.println("Started email send");
		final String username = "jhnewsmailserver@gmail.com";
		final String password = "groupgreat";
		final String host = "smtp.gmail.com";
		final int port = 465;//465

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.password", password);
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		//System.out.println("Session created");
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username,
				"JHNews Admin"));
		InternetAddress address;
		for (UserHibernate user : recipients) {
			try {
				address = new InternetAddress(user.getEmail(), true);
				address.setPersonal(user.getFirstName() + " "
						+ user.getLastName());
				msg.addRecipient(Message.RecipientType.BCC, address);
				System.out.println("Parse succeeded for " + address.getAddress());
			} catch (AddressException e) {
				System.out.println("Address parse failed for " + e.getMessage() + ", " + e.getCause());
			}
		}
		msg.setSubject(subject);
		msg.setText(body);
		System.out.println("Created message");
		//TODO
	    /*Transport transport = session.getTransport("smtps");
	    transport.connect(host, port, username, password);
	    transport.sendMessage(msg, msg.getAllRecipients());
	    transport.close();*/
		//System.out.println("Sent message");

	}
}
