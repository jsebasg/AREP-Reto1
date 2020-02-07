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
	
	public static ServerSocket serverSocket = null;
	public static Socket clientSocket = null;
	
	public static void main(String[] args) throws IOException {
		
		try {
			serverSocket = new ServerSocket(getPort());
		}
		catch (IOException e) {
			System.err.println("Could not listen on port: 8080.");
			System.exit(1);
		}
		
		try {
			System.out.println("Listo para recibir ...");
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String inputline, outputLine;
		
		while ((inputline = in.readLine()) != null) {
			System.out.println("Received: " + inputline);
			String[] arch = inputline.split("/");
			if(! in.ready()) {
				Archivo(arch[1]);	
			}			
		}
		
	}
	
	
	public static void Archivo(String a) throws IOException {
		String [] archivo = a.split("."); 
		String carpeta = "src/resource/";
		File arch = new File(carpeta + a);
		if (arch.exists()) {
			if (archivo[1].equals("html")) {
				FileInputStream filecall = new FileInputStream(arch);
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
				br.close();
			}
			else if (archivo[1].equals("jpg") || archivo[1].equals("png")) {
				
			}
			else {
				
			}
		}	
	}
	
	
	static int getPort() {
	      if (System.getenv("PORT") != null) {
	          return Integer.parseInt(System.getenv("PORT"));
	      }        
	         
	      return 4759; //returns default port if heroku-port isn't set(i.e. on localhost)    }
	  }
	
	
}
