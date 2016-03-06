package resrc.java;

import java.io.*;
import java.util.*;

public class PhoneDirectory {
	/* Class to hold the 4 functions needed to manage 
	 * Data is stored as keys and values in a properties file.
	 */
	
	/* Pre-Condition: Name and number is provided.
	 * Post Condition: Passed Argument are Added to PhoneDirectory's file system.
	 */
	public void addEntry(String name,String number){
		Properties property = new Properties();
		try{
			OutputStream outputStream = new FileOutputStream("src/main/resources/phone.properties",true);
			property.setProperty(name,number);
			property.store(outputStream,null);
			outputStream.close();
		}
		catch(IOException ex){
			System.out.println("Unable to open file : "+ex);
		}		
	}
	
	/*
	 * Pre-Condition: The name is passed.
	 * Post Condition: if user is present, return user. else, return NULL.
	 */
	public String getNumber(String name){
		String number="";
		Properties property = new Properties();
		try{
			InputStream inputStream  = new FileInputStream("src/main/resources/phone.properties");
			property.load(inputStream);
			number = property.getProperty(name);
			inputStream.close();			
		}
		
		catch(Exception e){
			System.out.println("File not found");
		}
		return number;	
	}
	
	/*
	 *Pre-Condition: The name is provided.
	 *Post Condition: If name of the user is deleted if it exits. 
	 */
	public void deleteEntry(String name){
		Properties property = new Properties();
		try{
			InputStream inputStream = new FileInputStream("src/main/resources/phone.properties");  
			property.load(inputStream);
			String check = property.getProperty(name);
			if(check==null){
				System.out.println("ERROR! The name not found");
				return;
			}
			property.remove(name);
			inputStream.close();
			OutputStream outputStream = new FileOutputStream("src/main/resources/phone.properties"); 
			property.store(outputStream , null);
			outputStream.close();
			System.out.println(name + "is deleted!");
		}
		catch(FileNotFoundException ex){
			System.out.println("Unable to oepn file");
		}
		catch(IOException ex){
			System.out.println("File not opened.");
		}		
	}
	
	/*
	 * Pre condition: The name and number is provided.
	 * Post condition: If present, records are updated.
	 */
	public void updateEntry(String name,String number){
		Properties property = new Properties();
		try{		
			InputStream inputStream = new FileInputStream("src/main/resources/phone.properties");
			property.load(inputStream);
			String check = property.getProperty(name);
			if(check==null){
				System.out.println("ERROR! The name not found");
				return;
			}
			property.replace(name, number);
			inputStream.close();
			OutputStream outputStream = new FileOutputStream("src/main/resources/phone.properties"); 
			property.store(outputStream, null);
			outputStream.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("Unable to oepn file");
		}
		catch(IOException ex){
			System.out.println("File not opened.");
		}
	}
}