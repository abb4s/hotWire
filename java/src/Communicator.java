import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Communicator {
	private static InetAddress host;
	private static Socket connection=null;//set null for validation in getter
	private static PrintWriter output=null;//set null for validation in getter
	private static int port=2015;
	public Communicator(){
		setHost();
		setConnection();
		setOutput();
	}
	public static Socket getConnection() {
		while(connection == null)
			setConnection();
		return connection;
	}
	public static PrintWriter getOutput() {
		while(output == null)
			setOutput();
		return output;
	}
	public static InetAddress getHost() {
		while(host == null)
			setHost();
		return host;
	}
	
	public static boolean setOutput() {
		try{
			PrintWriter output =new PrintWriter(getConnection().getOutputStream(),true);
			}
		catch(IOException e){
			return false;
		}
		return true;
	}
	public static boolean setHost() {
		try{
			host=InetAddress.getLocalHost();
		}catch(UnknownHostException e){
			return false;
		}
		return true;
	}
	public static boolean setConnection() {
		try{
			connection=new Socket(getHost(),port);
		}catch (IOException e){
			return false;
		}
			return true;
	}
	public static void send(String str){
		if(connection==null){
			setConnection();
			setHost();
		}
		getOutput().println(str);
	}
	public void close(){
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
			System.out.println(e2);
		}
	}
	
}
