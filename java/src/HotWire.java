import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class HotWire {
	Scanner n;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HotWire program=new HotWire();
	}
	public HotWire(){
		System.out.println("you can try some thing like this : asdfafh@600asdfh@603sfvr@50asdfvl@34 \n enter :");
		n=new Scanner(System.in);
		Control con=new Control();
		con.explodeMainPath(n.nextLine());
		for (String  a : con.path) {
			System.out.println(a);
		}
	}
	public void control(){
	
	}
}
