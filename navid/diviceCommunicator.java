import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;


public class diviceCommunicator {
	
	static Socket wire;
	static OutputStream sender;
	static OutputStream Data;
	
	
	static void sendDAtaM(String b,double a){
		try {
			wire=new Socket(Inet4Address.getLocalHost(), 2015);
			sender=wire.getOutputStream();
			sender.write((b+a).getBytes());
			System.out.println((b+a));
//			Thread.sleep(50);
			sender.close();
			wire.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
	static void sendDAtaS(double a){
		try {
			wire=new Socket(Inet4Address.getLocalHost(), 2015);
			sender=wire.getOutputStream();
			sender.write(("h@"+a).getBytes());
			System.out.println(("h@"+a));
			Thread.sleep(500);
			sender.close();
			wire.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
