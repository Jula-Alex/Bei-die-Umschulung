package simpleServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

	public static void main(String[] args) {
		
		Socket socket = null; 
		InputStreamReader inputStreamReader = null; 
		OutputStreamWriter outputStreamWriter = null; 
		BufferedReader bufferedReader = null; 
		BufferedWriter bufferedWriter = null; 
		
		try {
			socket = new Socket("localhost", 1234);
			
			inputStreamReader = new InputStreamReader(socket.getInputStream());
			outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
		
			bufferedReader = new BufferedReader(inputStreamReader);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			
			Scanner scanner = new Scanner(System.in);
			
			while(true) {
				String msgToSend = scanner.nextLine();
				
				bufferedWriter.write(msgToSend);
				bufferedWriter.newLine();
				bufferedWriter.flush();
				
				System.out.println("SERVER: " + bufferedReader.readLine()); 
				
				if(msgToSend.equalsIgnoreCase("bye"))
					break;
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null) 
					socket.close();
				if(inputStreamReader != null)
					inputStreamReader.close();
				if(outputStreamWriter != null) 
					outputStreamWriter.close();
				if(bufferedReader != null) 
					bufferedReader.close();
				if(bufferedWriter != null) 
					bufferedWriter.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}
