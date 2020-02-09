package edu.escuelaing.arem.app;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorWeb {
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8000);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
			System.exit(1);
		}
		Socket clientSocket = null;
		try {
			System.out.println("Listo para recibir ...");
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String inputLine, outputLine;
		System.out.println(clientSocket.getInputStream());
		System.out.println(in);
		System.out.println(in.readLine());
		String[] arch = null; 
		
		while ((inputLine = in.readLine()) != null) {
			System.out.println("Received: " + inputLine);
			arch = inputLine.split("/");
			if(! in.ready()) {
				break;	
			}
		}
		out.println(Archivo(arch[1]));
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
		
	}
	
	
	public static String Archivo(String a) throws IOException {
		String [] archivo = a.split("."); 
		String carpeta = "/src/resource/";
		String outputLine = null;
		File arch = new File(System.getProperty("user.dir")+carpeta + a);
		if (arch.exists()) {
			if (archivo[1].equals("html")) {
				String lineas;
				System.out.println("entra esta vuelta");
				FileReader f = new FileReader(System.getProperty("user.dir")+carpeta + a);
				BufferedReader bf = new BufferedReader(f);
				while ((lineas = bf.readLine()) != null) {
					System.out.println(lineas);
					outputLine += lineas; 
				}
				
				bf.close();
				return outputLine;
				/*FileInputStream filecall = new FileInputStream(arch);
				byte[] datos = new byte [(int) arch.length()];
				filecall.read(datos);
				filecall.close();
				DataOutputStream binout = new DataOutputStream(clientSocket.getOutputStream());
				binout.writeBytes("HTTP/1.0 200 OK\r\n");
				binout.writeBytes("Content-Type: text/html\r\n");
				BufferedReader br = new BufferedReader(new FileReader(arch));
				StringBuilder strB = new StringBuilder();
				String inputLine = null;
				while ((inputLine = br.readLine()) != null) {
					strB.append(inputLine);
				}
				System.out.println(strB.toString());
				br.close();*/
			}
			else if (archivo[1].equals("jpg") || archivo[1].equals("png")) {
				
			}
			else {
				
			}
		}
		return outputLine;
	}
	
	
	static int getPort() {
	      if (System.getenv("PORT") != null) {
	          return Integer.parseInt(System.getenv("PORT"));
	      }        
	         
	      return 4759; //returns default port if heroku-port isn't set(i.e. on localhost)    }
	  }
	
	
}
