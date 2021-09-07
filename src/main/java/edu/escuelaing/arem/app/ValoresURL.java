package edu.escuelaing.arem.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ValoresURL {
	
	static URL link;
	
	public static void main(String[] args) {
		
		try {
			link = new URL("http://ldbn.escuelaing.edu.co:80/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Protocol: " + getProtocol() );
		System.out.println("Authority: " + getAuthority());
		System.out.println("Protocol: " + getHost());
		System.out.println("Port: " + getPort());
		System.out.println("Path: " + getPath());
		System.out.println("Query: " + getQuery());
		System.out.println("File: " + getFile());
		System.out.println("Ref: " + getRef());
	}

	
	public static String getProtocol() {
		return link.getProtocol();
	}
	
	public static String getAuthority() {
		return link.getAuthority();		
	}
	
	public static String getHost() {
		return link.getHost();
	}
	
	public static int getPort() {
		return link.getPort();
	}
	
	public static String getPath() {
		return link.getPath();
	}
	
	public static String getQuery() {
		return link.getQuery();
	}
	
	public static String getFile() {
		return link.getFile();
	}
	
	public static String getRef() {
		return link.getRef();
	}
	
	

}
