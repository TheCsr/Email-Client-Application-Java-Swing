package package1;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class First extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6672594319721820776L;
	TitledBorder brd = new TitledBorder("Inbox");
	TitledBorder brd1 = new TitledBorder("Compose");
	GmailClient newGmailClient;
	protected String emailfrm;
	protected String emailto;
	protected String pass;
	protected String subj;
	protected String message;
	JFrame jfrm;
	JPanel lp;
	JPanel rp;
	JPanel irp;
	JPanel Inboxirp;
	
	JButton btnCmp;
	JButton btnSend;
	JButton btnInbox;
	JMenuBar mnbr;
	
	JLabel displaymsg;
	JLabel frm;
	JLabel to;
	JLabel pw;
	JLabel sub;
	JLabel msg;
	JTextArea afrm;
	JTextArea ato;
	JPasswordField apw;
	JTextArea asub;
	JTextArea amsg;
	
	public First()
	{
		jfrm = new JFrame("Email-Xilla");
		
		
		mnbr = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu help = new JMenu("Help");
		mnbr.add(file);
		mnbr.add(edit);
		mnbr.add(help);
		
		
		
		JSplitPane sp = new JSplitPane();
		
		lp = new JPanel(null);
		rp = new JPanel(null);
		
		
		sp.setLeftComponent(lp);
		sp.setRightComponent(rp);
		sp.setDividerLocation(200);
		
		
		btnCmp = new JButton("Compose");
		btnCmp.addActionListener(this);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(this);
		
		btnInbox = new JButton("Inbox");
		btnInbox.addActionListener(this);

		lp.add(btnCmp);
		lp.add(btnSend);
		lp.add(btnInbox);
		btnCmp.setBounds(10,10,100,30);
		btnSend.setBounds(10,60,100,30);
		btnInbox.setBounds(10,120,100,30);
		
		
	
		
		
		
		
		
		jfrm.add(sp);
		jfrm.setJMenuBar(mnbr);
		jfrm.setSize(1920,1080);
		jfrm.setVisible(true);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		
	}
	public void RightPanel()
	{
		
		irp = new JPanel(null);
		rp.add(irp,BorderLayout.CENTER);
		brd1.setTitleJustification(TitledBorder.CENTER);
	    brd1.setTitlePosition(TitledBorder.TOP);
	    irp.setBorder(brd1);
		//irp.setBackground(Color.RED);
		irp.setBounds(10,10,800,700);
		irp.setVisible(false);
		
		frm = new JLabel("From:");
		frm.setBounds(15,15,100,50);
		to = new JLabel("To:");
		to.setBounds(15,75,100,50);
		pw = new JLabel("Password");
		pw.setBounds(15,135,100,50);
		sub = new JLabel("Subject");
		sub.setBounds(15,195,100,50);
		msg = new JLabel("Message");
		msg.setBounds(15,255,100,50);
		
		afrm = new JTextArea();
		afrm.setBounds(150,15,200,25);
		ato =new JTextArea();
		ato.setBounds(150,75,200,25);
		apw= new JPasswordField();
		apw.setBounds(150,135,200,25);
		asub = new JTextArea();
		asub.setBounds(150,195,200,25);
		amsg = new JTextArea();
		amsg.setBounds(150,255,500,200);
		
		irp.add(frm);
		irp.add(to);
		irp.add(pw);
		irp.add(sub);
		irp.add(msg);
		irp.add(afrm);
		irp.add(ato);
		irp.add(apw);
		irp.add(asub);
		irp.add(amsg);
		
		brd.setTitleJustification(TitledBorder.CENTER);
	    brd.setTitlePosition(TitledBorder.TOP);
		Inboxirp = new JPanel();
		Inboxirp.setBorder(brd);
		rp.add(Inboxirp,BorderLayout.EAST);
		//Inboxirp.setBackground(Color.RED);
		Inboxirp.setBounds(850,10,850,700);
		Inboxirp.setVisible(false);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnCmp)
		{
			irp.setVisible(true);
			
		}
		if(e.getSource()==btnSend)
		{
			try {
			newGmailClient = new SRMail();
			emailfrm = afrm.getText();
			emailto = ato.getText();
			pass = new String(apw.getPassword());
			subj = asub.getText();
			message = amsg.getText();
			newGmailClient.setAccountDetails(emailfrm,pass);
			newGmailClient.SendGmail(emailfrm,emailto,subj,message );
			}
			catch(Exception er)
			{
				 er.printStackTrace();
				 
                 JOptionPane.showMessageDialog(null, "Sending email to: " + to + " failed !!!", "Falied to Send!!!", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		if(e.getSource()==btnInbox)
		{
			displaymsg= new JLabel();
			Inboxirp.setVisible(true);
			Inboxirp.setLayout(new GridLayout(20,1));
			newGmailClient = new SRMail();
			emailfrm = afrm.getText();
			pass = new String(apw.getPassword());
			@SuppressWarnings("rawtypes")
			List messagedis= newGmailClient.ReadGmail(emailfrm,pass);
			/*displaymsg= new JLabel();
			String element = (String) messagedis.get(0);
			displaymsg.setText(element);
			Inboxirp.add(displaymsg);*/
			
			displaymsg= new JLabel();
			String element1 = (String) messagedis.get(1);
			displaymsg.setText(element1);
			Inboxirp.add(displaymsg);
			
			displaymsg= new JLabel();
			String element3 = (String) messagedis.get(2);
			displaymsg.setText(element3);
			Inboxirp.add(displaymsg);
			
			displaymsg= new JLabel();
			String element4 = (String) messagedis.get(3);
			displaymsg.setText(element4);
			Inboxirp.add(displaymsg);
			
			displaymsg= new JLabel();
			String element5 = (String) messagedis.get(4);
			displaymsg.setText(element5);
			Inboxirp.add(displaymsg);
			
			displaymsg= new JLabel();
			String element6 = (String) messagedis.get(5);
			displaymsg.setText(element6);
			Inboxirp.add(displaymsg);
			
			displaymsg= new JLabel();
			String element7 = (String) messagedis.get(6);
			displaymsg.setText(element7);
			Inboxirp.add(displaymsg);
			
			displaymsg= new JLabel();
			String element8 = (String) messagedis.get(7);
			displaymsg.setText(element8);
			Inboxirp.add(displaymsg);
			
			displaymsg= new JLabel();
			String element9 = (String) messagedis.get(8);
			displaymsg.setText(element9);
			Inboxirp.add(displaymsg);
			
			displaymsg= new JLabel();
			String element10 = (String) messagedis.get(9);
			displaymsg.setText(element10);
			Inboxirp.add(displaymsg);
			
			displaymsg= new JLabel();
			String element11 = (String) messagedis.get(10);
			displaymsg.setText(element11);
			Inboxirp.add(displaymsg);
					
		}
			
		
	}

}
