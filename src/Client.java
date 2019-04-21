
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Properties;
import java.util.function.Supplier;

import javax.mail.SendFailedException;
import javax.mail.Session;



 public class Client{
	 String messageFile = "file.txt";
	 
	  String from = "yevgeniia2015@gmail.com";

	  String to = "lady.grebenuck2012@yandex.ru";
	  
	  String mailHost = "smtp.gmail.com";
	  
	 

 	private Socket socket;
 	private int serverPort;
 	private String serverIpAddress;
        BufferedReader in;
        PrintWriter out;
        String inData;
        String outData;
       

       
	public Client(){
		
	
        	clientStart();
 	}

	private void clientStart(){
		//properties

 		System.out.println("Server IP:"+serverIpAddress);
		System.out.println("Server port:"+serverPort); 

 		try{
 			socket = new Socket(serverIpAddress,serverPort);
 			System.out.println("Connected...\n");
 			
 			
 			clientService();
		}
 		catch(Exception e){
 			System.out.println("Error"+e);
 		}
 		finally{
 			try{
 				socket.close();
 			}
 			catch(Exception e){
 				System.out.println("Error"+e);
 			}
 		}
 	}
    private void send(BufferedReader in, PrintWriter out, String string) throws IOException {
    
    	
try {
	out.write(string + "\n");
	out.flush(); //ensures that all [ending data is sent out to the target
	
	string = in.readLine();
	
	
	System.out.println(from+ string);
	System.out.println(to + string);
	
} catch (Exception e) {
	e.printStackTrace();
}
    	
	
}
 	public void clientService(){
            try{
            	
            	
            	Socket socket = new Socket(serverIpAddress,serverPort);
                in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
                 
                
                //System.out.println(in.readLine()+"\n");
               
                String userInput;
                while ((userInput = in.readLine()) != null) {
                    out.println(userInput);
                    System.out.println("echo: " + in.readLine());
                }
             

                 

		in.close();
		out.close();

            }catch(Exception e){
		System.out.println("Error"+e);
            }

 	}
 	public void getProperties() {
 		
 		Properties properties = new Properties();
 		InputStream inputStream=null;
 		try {
 			properties.put("mail.smtp.auth", "true");
 			properties.put("mail.smtp.starttls.enable", "true");
 			properties.put("mail.smtp.host", "smtp.gmail.com");
 	 		properties.put("mail.smtp.port", "587");
 	 		properties.put("mail.smtp.user", "yevgeniia2015@gmail.com");
 	 		properties.put("mail.smtp.password", "yourPassword");
 	 		properties.put("mail.smtp.domain", "domain");
 	 		
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
            //check if the input stream in empty
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
 		
 		
 		
 	}

        public static void main(String[] args) {
        
           new Client();
     }
 }