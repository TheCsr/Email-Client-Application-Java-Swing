package package1;

import java.util.*;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class SRMail extends GmailClient {
	@SuppressWarnings("rawtypes")
	List messagelist = new ArrayList();

	@Override
	public void SendGmail(String from, String to, String subject, String text) {
		

        this.from=from;
        this.to=to;
        this.subject=subject;
        this.text=text;
 
        // For a Gmail account--sending mails-- host and port shold be as follows
 
        this.sendingHost="smtp.gmail.com";
        this.sendingPort=465;
 
        Properties props = new Properties();
 
        props.put("mail.smtp.host", this.sendingHost);
        props.put("mail.smtp.port", String.valueOf(this.sendingPort));
        props.put("mail.smtp.user", this.userName);
        props.put("mail.smtp.password", this.password);
 
        props.put("mail.smtp.auth", "true");
 
         Session session1 = Session.getDefaultInstance(props);
 
         Message simpleMessage = new MimeMessage(session1);
 
        //MIME stands for Multipurpose Internet Mail Extensions
 
        InternetAddress fromAddress = null;
        InternetAddress toAddress = null;
 
        try {
 
            fromAddress = new InternetAddress(this.from);
            toAddress = new InternetAddress(this.to);
 
        } catch (AddressException e) {
 
            e.printStackTrace();
 
                       JOptionPane.showMessageDialog(null, "Sending email to: " + to + " failed !!!", "Falied to Send!!!", JOptionPane.ERROR_MESSAGE);
 
        }
 
        try {
 
            simpleMessage.setFrom(fromAddress);
 
            simpleMessage.setRecipient(RecipientType.TO, toAddress);
 
                        // to add CC or BCC use
                        // simpleMessage.setRecipient(RecipientType.CC, new InternetAddress("CC_Recipient@any_mail.com"));
                        // simpleMessage.setRecipient(RecipientType.BCC, new InternetAddress("CBC_Recipient@any_mail.com"));
 
            simpleMessage.setSubject(this.subject);
 
            simpleMessage.setText(this.text);
 
        //sometimes Transport.send(simpleMessage); is used, but for gmail it's different
 
            Transport transport = session1.getTransport("smtps");
 
            transport.connect (this.sendingHost,sendingPort, this.userName, this.password);
 
            transport.sendMessage(simpleMessage, simpleMessage.getAllRecipients());
 
            transport.close();
 
            JOptionPane.showMessageDialog(null, "Mail sent successfully ...","Mail sent",JOptionPane.PLAIN_MESSAGE);
 
        } catch (MessagingException e) {
 
            e.printStackTrace();
 
                       JOptionPane.showMessageDialog(null, "Sending email to: " + to + " failed !!!", "Falied to Send!!!", JOptionPane.ERROR_MESSAGE);
 
        }
 
		
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List ReadGmail(String from ,String password) {
		this.from=from;
		this.password=password;
		
		
		 this.receivingHost="imap.gmail.com";//for imap protocol
		 
	        Properties props2=System.getProperties();
	 
	        props2.setProperty("mail.store.protocol", "imaps");
	        // I used imaps protocol here
	 
	        Session session2=Session.getDefaultInstance(props2, null);
	 
	            try {
	 
	                    Store store=session2.getStore("imaps");
	 
	                    store.connect(this.receivingHost,this.from, this.password);
	 
	                    Folder folder=store.getFolder("INBOX");//get inbox
	 
	                    folder.open(Folder.READ_ONLY);//open folder only to read
	 
	                    Message message[]=folder.getMessages();
	 
	                    for(int i=0;i<message.length;i++){
	 
	                        //print subjects of all mails in the inbox
	 
	                        System.out.println(message[i].getSubject());
	                    	messagelist.add(message[i].getSubject());
	                    	/*String element = (String) messagelist.get(i);
	                    	System.out.println(element);*/
	 
	                    }
	                    
	                  
	                    
	 
	                    //close connections
	 
	                    
	                    
	                    folder.close(true);
	               	 
	                    store.close();
	                    return messagelist;
	 
	            } catch (Exception e) {
	 
	                    System.out.println(e.toString());
	 
	            }
				return null;
			
		
		
	}

}
