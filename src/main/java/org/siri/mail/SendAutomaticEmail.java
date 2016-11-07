package org.siri.mail;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine; 
  
public class SendAutomaticEmail extends TimerTask{  
	
	public static void main(String[] args) {  
	 
	
  //Address[] to = new Address[] {};
 String to="neshanthkalaichelvan@gmail.com ";//Receiver email id 
  
  //Get the session object  
  Properties props = new Properties();  
  props.put("mail.smtp.host", "smtp.gmail.com");  
  props.put("mail.smtp.socketFactory.port", "465");  
  props.put("mail.smtp.socketFactory.class",  
            "javax.net.ssl.SSLSocketFactory");  
  props.put("mail.smtp.auth", "true");  
  props.put("mail.smtp.port", "465");  
   
  Session session = Session.getDefaultInstance(props,  
   new javax.mail.Authenticator() {  
   protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication("neshanthkk@gmail.com","Boxerdog3#");//sendermailid,sender password
   }  
  });  
   
  //compose message  
  try {  
   MimeMessage message = new MimeMessage(session);  
   message.setFrom(new InternetAddress("neshanthkk@gmail.com"));//sender mail id
   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
   message.setSubject("Auto generated  Email");  
   
   BodyPart body = new MimeBodyPart();
   
   body.setText("Testing");
   /*VelocityEngine ve= new VelocityEngine();
   ve.init();

   Template t= ve.getTemplate("mail-body-template.vm");
   VelocityContext context = new VelocityContext();
   context.put("sender","siriinfosolutions");
   StringWriter out = new StringWriter();
   t.merge( context, out );
   body.setContent(out.toString(),"text/html");*/
   
   Multipart multipart = new MimeMultipart();
   multipart.addBodyPart(body);
   body=new MimeBodyPart();
   String file = "C:/Users/Neshanth/workspace/MailWebApp/src/main/resources/images/sirilogo.png";// give image url to be embedded
   DataSource source = new FileDataSource(file);
   body.setDataHandler(new DataHandler(source));
   body.setFileName(file);
   multipart.addBodyPart(body);
   message.setContent(multipart);
   
   //send message  
   Transport.send(message);  
  
   System.out.println("message sent successfully");  
   
  } catch (Exception e) {throw new RuntimeException(e);}  
   
 } 
  
	public static void send(){
		 Timer t = new Timer();
		 Calendar date = Calendar.getInstance();
		    date.set(
		      Calendar.DAY_OF_WEEK,
		      Calendar.SUNDAY
		    );
		    date.set(Calendar.HOUR, 0);
		    date.set(Calendar.MINUTE, 0);
		    date.set(Calendar.SECOND, 0);
		    date.set(Calendar.MILLISECOND, 0);
		    t.schedule(
		      new SendAutomaticEmail(),
		      date.getTime(),
		      1000 * 60 * 60 * 24 * 7
		    );
		  }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Generating report");
	}
	 }

