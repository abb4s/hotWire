import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class test {

	public static void main(String[] args) throws IOException {

		Scanner answer = new Scanner(System.in);
		myCNC CNC = new myCNC();
		CNC.setStartpos(0, 0, 0);
		CNC.setFinaltpos(0,0,0);
		CNC.cutline();
//		diviceCommunicator.wire.close();
			}

}
