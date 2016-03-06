package Main.java;

import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		/* name is for the name entered the user.
		 * number is for the number to be entered by user
		 */
		String name,number;
		System.out.println("Welcome to Your Phone Directory");
		System.out.println("Press 1 to Add entry.\tPress 2 to Delete entry.\tPress 3 to Get number. \tPress 4 to Change contact");
		int option;
		PhoneDirectory object = new PhoneDirectory();

		System.out.println("Enter your option : ");
		Scanner in_stream = new Scanner(System.in);
		option=in_stream.nextInt();
		if(option ==1){
			Scanner name_object = new Scanner(System.in);
			Scanner number_object= new Scanner(System.in);
			System.out.println("Enter the name : ");
			name=name_object.nextLine();
			System.out.println("Enter the number : ");
			number=number_object.nextLine();
			object.addEntry(name, number);
		}
		else if(option==2){
						
					Scanner name_object= new Scanner(System.in);
					System.out.println("Enter the name to be deleted : ");
					name=name_object.nextLine();
					object.deleteEntry(name);
			}
		else if(option==3){
				Scanner name_object= new Scanner(System.in);
				System.out.println("Enter the name to search : ");
				name = name_object.nextLine();
				number=object.getNumber(name);
				if(number.equals(null)){
					System.out.println("ERROR! The name entered is not found");
				}
				else{
					System.out.println("The number is : " + number);
				}
			}
		else if(option==4){
				Scanner name_object= new Scanner(System.in);
				Scanner number_object= new Scanner(System.in);
				System.out.println("Enter the name update : ");
				name=name_object.nextLine();
				System.out.println("Enter the number to be updated : ");
				number=number_object.nextLine();
				object.updateEntry(name, number);						
			}
		else{
			System.out.println("Wrong option entered");
		}
	}
}