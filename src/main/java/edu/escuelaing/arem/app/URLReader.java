package edu.escuelaing.arem.app;


import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class URLReader {
	static ArrayList <String> lines = new ArrayList<>(); 
	public static void main(String[] args) throws Exception {
		URL google = new URL("http://www.google.com");
			
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(google.openStream()))) {
				String inputLine = null;
				while ((inputLine = reader.readLine()) != null) {
					lines.add(inputLine);
				System.out.println(inputLine);
			}
		}
		catch (IOException x) {
				System.err.println(x);
		}
		
	}

	public static void crearArchivo(ArrayList<String> arreglo) {
		
		try {
			FileWriter write = new FileWriter("C:\\Users\\jimmy.chirivi\\Downloads\\resultado.html");
			BufferedWriter bf = new BufferedWriter(write);
			bf.write();
			bf.close();
			
		} catch (IOException e) {				
			e.printStackTrace();
		}
		
	}
	
	

}
