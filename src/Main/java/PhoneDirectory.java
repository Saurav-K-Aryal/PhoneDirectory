package Main.java;

import java.io.*;
import java.util.*;

public class PhoneDirectory {
	/**
	 * This class has 6 member functions.
	 * 1) Constructor, which sets in the location of the file.
	 * 2) To see if the file is valid. (If the file is empty)
	 * 3) To add an Entry
	 * 4) To delete an Entry
	 * 5) To get the number from the name of the user
	 * 6) To modify the entry entered by the user
	 */
	String name,number,location,middle;
	HashMap<String,String> myHashMap = new HashMap<String,String>();
	/**
	 * @param string
	 * @prama sep
	 * PreCondition: the location of the string is passed
	 * PostCondition: the location is set.
	 * This will also pull pre-exisiting values of our file in a HashMap.
	 * This will be used when this program is closed and compiled again.
	 */
	public PhoneDirectory(String file_path, String delim){
		location = file_path;
		/**
		 * there are some compiler issues with using | for delimiter
		 * temp="\\" is a simple workaround
		 */
		String temp ="\\";
		temp = temp.concat(delim);
		middle = delim;
		File fileName = new File(location);
		String line=null;
		try{
			FileReader fileReader = new FileReader(fileName);			
			BufferedReader bufferedReader = new BufferedReader(fileReader);	
			while((line=bufferedReader.readLine())!=null){
				String[] value = line.split(temp);
				name=value[0];
				number=value[1];
				myHashMap.put(name,number);
			}
			bufferedReader.close();
		}
		
		catch(FileNotFoundException ex){
			System.out.println("Unable to open file");
		}
		catch(IOException ex){
			System.out.println("File not opened.");
		}
	}
	/**
	 * Checks if the file and hashmap are empty.
	 * If empty, fiel is invalid.
	 * @return 
	 * Pre-Condition: The file exists.
	 * Post Condition: Will return false if the file or the hashmap is empty.
	 */
	public boolean isValid(){
		File inFile = new File(location);
		boolean validity = true;
		try{
			FileReader reader = new FileReader(inFile);
			BufferedReader bufferedReader = new BufferedReader(reader);
			int length = bufferedReader.read();
			if(length==-1 && myHashMap.isEmpty()){
				System.out.println("ERROR. There is nothing to read");
				validity = false;
			}		
			bufferedReader.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("Unable to open file");
		}
		catch(IOException ex){
			System.out.println("File not opened.");
		}
		return validity;
	}
	/**
	 * @param name
	 * @param number
	 * Pre-Condition: The arguments passed are intended to be added.
	 * Post Condition: If entry is unique, add to file and hashmap.
	 */
	public void addEntry(String name,String number){
		String num = myHashMap.get(name);
		if(num != null){			
				System.out.println("Name is already in directory. ");
		}
		else{
			myHashMap.put(name, number);
			System.out.println(name + " and " + number + " added. ");
		}
	}
	/**
	 * @param name
	 * Pre-Condition: The name is passed as an arguement.
	 * Post Condition: The name of the user is deleted from the Directory, if present. 
	 */
	public void DeleteEntry(String name){
		if(myHashMap.get(name)!=null){
			myHashMap.remove(name);
			System.out.println(name + " deleted. ");
		}
		else{
			System.out.println("Error! The name is not in directory");
		}
		
	}
	/**
	 * @param name
	 * @return
	 * Pre Condition: The name to search is passed an argument.
	 * Post Condition: If name is present, return user. Else, return NUll.
	 */
	public String getNumber(String name){
		String foundNumber = "";
		foundNumber = myHashMap.get(name);
		if(foundNumber == null){
			System.out.println("ERROR!! The name is not in directory");
		}
		return foundNumber;
	}
	
	/**
	 * @param name
	 * @param number
	 * Pre-condition: The name and number as passed as an argument.
	 * Post condition: if passed name exists, the number is updated.
	 */
	public void changeEntry(String name,String number){
		String isValid=myHashMap.get(name);
		if(isValid==null){
			System.out.println("ERROR! The name not found ");
		}
		else{
			myHashMap.put(name, number);
			System.out.println(name + " is modified. ");
		}
	}
	/**
	 * This will write to the file.
	 */
	public void write(){
		File fileName= new File(location);
		Iterator iter = myHashMap.entrySet().iterator();
		try{
			FileWriter fileWriter  = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			while (iter.hasNext()) {
				HashMap.Entry pair = (HashMap.Entry)iter.next();
				
				bufferedWriter.write(pair.getKey()+middle+pair.getValue());
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("Unable to open file");
		}
		catch(IOException ex){
			System.out.println("File not opened.");
		}
	}
}