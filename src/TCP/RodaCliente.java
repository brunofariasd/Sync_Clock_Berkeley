package TCP;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RodaCliente {
	
	Scanner sc = new Scanner(System.in);
	 
	ClienteRunnable c;
	public RodaCliente() throws UnknownHostException, IOException {
		Thread escutarServidor =new Thread(new Runnable() {
			@Override
			public void run() {
				Socket socket;
				try {
					socket = new Socket("127.0.0.1", 12345);
					c = new ClienteRunnable(socket);
					Thread t = new Thread(c);
					t.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		escutarServidor.start();
		
		System.out.print("Digite a hora inicial do Cliente no formato HH:MM:SS: ");
		String time1 = sc.nextLine();
		c.scheduleClient = time1;
	}
	
	public static String [] msgSeparada(String msg) {
		
		String [] arrayString = msg.split(";");
		return arrayString;
	}
	
	public static void main(String args[]) throws UnknownHostException, IOException {
		new RodaCliente(); 
	}
		
}