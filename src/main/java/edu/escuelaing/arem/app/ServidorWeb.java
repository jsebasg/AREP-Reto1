package edu.escuelaing.arem.app;

import java.io.BufferedReader;
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
			serverSocket = new ServerSocket(8080);
		}
		catch (IOException e) {
			System.err.println("Could not listen on port: 8080.");
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
		String inputline, outputLine;
		
		while ((inputline = in.readLine()) != null) {
			System.out.println("Received: " + inputline);
			String[] arch = inputline.split("/");
			Archivo(arch[1]);
			
			/*if(! in.ready()) {
				
			}*/			
		}
		
	}
	
	
	public static void Archivo(String a) {
		String[] extencion = a.split(".");
		
	}
	
	
}
