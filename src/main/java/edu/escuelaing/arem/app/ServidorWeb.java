package edu.escuelaing.arem.app;

import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * @author jsebasg
 */

public class ServidorWeb {
	public static void main(String[] args) throws IOException {
		
		while (true) {
			ServerSocket serverSocket = null;
			try {
				serverSocket = new ServerSocket(getPort());
			} catch (IOException e) {
				System.err.println("Could not listen on port: 8080.");
				System.exit(1);
			}
			Socket clientSocket = null;
			try {
				System.out.println("Listo para recibir ...");
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.exit(1);
			}

			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine, outputLine;
			String[] arch = null;
			String[] elem = null;

			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains("GET")) {
					arch = inputLine.split("/");
					elem = arch[1].split(" ");
					String[] archivo = elem[0].split("\\.");
					if(archivo.length == 1 ){
						file("index.html", clientSocket.getOutputStream(), out);
					}
					else {
						if (archivo[1].equals("html") || archivo[1].equals("js") || archivo[1].equals("jpg") || archivo[1].equals("png")) {
							file(elem[0], clientSocket.getOutputStream(), out);
						}
					}
				}
				if (!in.ready()) {
					break;
				}
			}
			out.close();
			in.close();
			clientSocket.close();
			serverSocket.close();
		}
	}
	public static void file(String a, OutputStream clientOutput, PrintWriter out) throws IOException {
		String[] archivo = a.split("\\.");
		String carpeta = "/src/resource/";
		String outputLine = "";
		File arch = new File(System.getProperty("user.dir") + carpeta + a);
		if (arch.exists()) {
			if (archivo[1].equals("html")) {
				String lineas;
				FileReader f = new FileReader(System.getProperty("user.dir") + carpeta + a);
				BufferedReader bf = new BufferedReader(f);
				while ((lineas = bf.readLine()) != null) {
					System.out.println(lineas);
					outputLine += lineas;
				}
				bf.close();
				out.write(("HTTP/1.1 404 Not Found \r\n" + "Content-Type: text/html; charset=\"utf-8\" \r\n" + "\r\n"
						+ outputLine));
			}if (archivo[1].equals("jpg") || archivo[1].equals("png")) {

				BufferedImage imagen = ImageIO.read(arch);

				ByteArrayOutputStream bytes = new ByteArrayOutputStream();

				DataOutputStream w = new DataOutputStream(clientOutput);

				ImageIO.write(imagen, archivo[1], bytes);

				w.writeBytes("HTTP/1.1 404 Not Found \r\n");
				w.writeBytes("Content-Type: image/" + archivo[1] + "; charset=\"utf-8\" \r\n");
				w.writeBytes("\r\n");
				w.write(bytes.toByteArray());
			}if (archivo[1].equals("js")){

				String lineas;
				FileReader f = new FileReader(System.getProperty("user.dir") + carpeta + a);
				BufferedReader bf = new BufferedReader(f);
				while ((lineas = bf.readLine()) != null) {
					outputLine += lineas;
				}

				bf.close();

				out.write(("HTTP/1.1 404 Not Found \r\n" + "Content-Type: text/html; charset=\"utf-8\" \r\n" + "\r\n"
						+ outputLine));
			}
		}
	}

	static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}

		return 8080; // returns default port if heroku-port isn't set(i.e. on localhost) }
	}

}
