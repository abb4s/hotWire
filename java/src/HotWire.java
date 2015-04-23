import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JFrame;


public class HotWire {
	Device device;
	Scanner n;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HotWire program=new HotWire();
	}
	public HotWire(){
		device=new Device();
		n=new Scanner(System.in);
		/*System.out.println("you can try some thing like this : asdfafh@600asdfh@603sfvr@50asdfvl@34 \n enter :");
		Control con=new Control();
		con.explodeMainPath(n.nextLine());
		for (String  a : con.path) {
			System.out.println(a);
		}*/
		/*device.resetWire();
		while(true){
			System.out.println("enter :");
			n.nextLine();
			test_device();
		}*/
		
                
/*                Shape sh=new Shape();
                sh.getFromFile("t.obj");
                View b = new View(sh.getPoints());
                b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                b.setVisible(true);
                b.setSize(400,400);*/
		device.test();
                
			
	}
	public void control(){
	
	}
	public void test_device(){
		
		//device.command("h@0.5");
	}
}
