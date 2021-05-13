package TCP;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.Encript;
import controllers.SchedulesController;

public class Servidor implements Runnable{
	
	public Socket client;
	public static String currentSchedule;
	public static ArrayList<Socket> clientsConecteds = new ArrayList<Socket>();
	public static ArrayList<String> schedules = new ArrayList<String>();
		
	public Servidor(Socket cliente) throws IOException{
		this.client = cliente;	
		Servidor.clientsConecteds.add(cliente);
	}
	
	public void run(){
		try {
			
			Scanner s = null;
			s = new Scanner(this.client.getInputStream());
			String rcv;
			
			while(s.hasNextLine()){
				rcv = s.nextLine();
				System.out.println("Texto encriptado"+ rcv);
				rcv = Encript.decriptarCifraCesar(3, rcv);
				System.out.println("Texto decriptado"+ rcv);
				
				
				schedules.add(rcv);
				System.out.println("...");
				if (schedules.size() == clientsConecteds.size()) {
					
					schedules.add(currentSchedule);
					SchedulesController.writeSchedulesArc(schedules);
					
					String actualSchedule = calculateAverageOfTime();
					enviarMensagemBroadcast("post;"+actualSchedule);
				    
					SchedulesController.writeScheduleArc(actualSchedule);
					
					schedules.clear();
				}
			}
			//Closed scanner e socket
			s.close();
			this.client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String calculateAverageOfTime() {
	    long seconds = 0;
	    for (String timestr : schedules) {
	        String[] hhmmss = timestr.split(":");
	        seconds += Integer.valueOf(hhmmss[0]) * 60 * 60;
	        seconds += Integer.valueOf(hhmmss[1]) * 60;
	        seconds += Integer.valueOf(hhmmss[2]);
	    }
	    seconds /= schedules.size();
	    long hh = seconds / 60 / 60;
	    long mm = (seconds / 60) % 60;
	    long ss = seconds % 60;
	    
		return String.format("%02d:%02d:%02d", hh,mm,ss);
	}
 
	 public void enviarMensagem(String mensagem) throws IOException{
			PrintStream saida = new PrintStream(client.getOutputStream());
			saida.println(Encript.encriptarCifraCesar(3,mensagem));
	 }
 
	 public static void enviarMensagemBroadcast(String mensagem) throws IOException {
		for (int counter = 0; counter < clientsConecteds.size(); counter++) {
			try{
				PrintStream saida = new PrintStream(clientsConecteds.get(counter).getOutputStream());
				saida.println(Encript.encriptarCifraCesar(3,mensagem));
			} catch(Exception ex){
			    /*client.remove*/
				clientsConecteds.remove(counter);
				System.out.println(ex.getMessage());
			}
		}
	 }
 
	public static String [] msgSeparada(String msg) {
		
		String [] arrayString = msg.split(";");
		return arrayString;
	}
 
}