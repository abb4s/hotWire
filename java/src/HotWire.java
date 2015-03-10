import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class HotWire {
	private static InetAddress host;
	public static Socket connection=null;
	private static int port=2015;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			host=InetAddress.getLocalHost();
		}catch(UnknownHostException e){
			System.out.println(e);
		}
		HotWire program=new HotWire();
	}
	public HotWire(){
		try{
			connection=new Socket(host,port);
			Scanner n=new Scanner(System.in);
			
			while(true){
				send();
				System.out.println("enter :");
				n.nextLine();
			}
			
		}catch (IOException e){
			System.out.print(e);
		}
		finally
		{
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
	public void send(){
		
		try{
//			Scanner in = new Scanner(connection.getInputStream());
			PrintWriter output =new PrintWriter(connection.getOutputStream(),true);
			output.println("200vl");
			/*String text;
			if(!(text=in.next()).isEmpty())
				System.out.println("server :" + in.nextLine());
			*/
			}
		catch(IOException e){
			System.out.print(e);
		}

	}
	public void control(){
		String command=new String();
		Scanner in =new Scanner(System.in);
		
		command=in.nextLine();
		if(command.matches("^/d h$") || command.matches("^/d vl$") || command.matches("^/d vr$")){
			
		}
		try{
			PrintWriter output =new PrintWriter(connection.getOutputStream(),true);
			}
		catch(IOException e){
			
		}
		
		
	}


}
