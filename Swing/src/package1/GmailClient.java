package package1;

import java.util.List;

public abstract class GmailClient {
	
	protected String userName;
	protected String password;
    protected String sendingHost;
    protected int sendingPort;
    protected String from;
    protected String to;
    protected String subject;
    protected String text;
    protected String receivingHost;
//    private int receivingPort;
 
    public void setAccountDetails(String userName,String password){
 
        this.userName=userName;//sender's email can also use as User Name
        this.password=password;
 
    }

	public abstract void SendGmail(String mailFrom, String mailTo, String mailSubject, String mailText);

	@SuppressWarnings("rawtypes")
	public abstract List ReadGmail(String from , String password);
	
	
	
		
}


