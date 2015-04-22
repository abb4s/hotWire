import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Communicator {
	private  InetAddress host;
	private  Socket connection=null;//set null for validation in getter
	private  PrintWriter output=null;//set null for validation in getter
	private  int port=2015;
	public void reconnect(){
		close();
		connect();
	}
	public Communicator(){
		connect();
	}
	public void connect(){
		try{
			host=InetAddress.getLocalHost();
			try{
				connection=new Socket(host,port);
				try{
					output =new PrintWriter(getConnection().getOutputStream(),true);
					}
				catch(IOException e){
					System.out.println(e + "/n cannot set output! ");
				}
			}catch (IOException e){
				System.out.println(e + " /n can not finde server !");
			}
			
		}catch(UnknownHostException e){
			System.out.println(e + " /n can not set host !");
		}

		
	}
	public  Socket getConnection() {
		return connection;
	}
	public void send(String str){

			/*try{
				PrintWriter output =new PrintWriter(getConnection().getOutputStream(),true);
				output.println(str);
				System.out.println("sended : " + str);

			}
			catch(IOException e){
				System.out.println(e);
			}*/
		output.println(str);
		System.out.println("sended : " + str);

	}
	public void close(){
		if(connection != null)
			try
			{
				System.out.println(
				"\n* Closing connection… *");
				connection.close(); 
			}
			catch(IOException ioEx)
			{
				System.out.println(
				"Unable to disconnect!");
				System.exit(1);
			}
			catch (NullPointerException e2) {
				// TODO: handle exception
				System.out.println(e2 + " /n befor closed !");
			}
	}


	
}
