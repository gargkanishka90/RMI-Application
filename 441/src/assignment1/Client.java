package assignment1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


public class Client {
	static ArrayList<Patient> list = new ArrayList<Patient>();
	public static void main(String[] args) {
		try {  		    
  		    // In order to connect to the hospital server, client uses 
			//the default port number in order to bind to hospital object.
  		    Registry registry = LocateRegistry.getRegistry(null);
			HospitalInterface s = (HospitalInterface)registry.lookup("hospital");
			// Displays the list of hospitals and select the hospital.
			int hospChoice = showHospitals(s.getListOfHospitals());
			ArrayList<String> docs = s.getListOfDoctors(hospChoice);
			
			// Create some patient objects to be sent to hospital server.
			Patient c1 = new Patient(11,"Kanishka");
			list.add(c1);
			// Select the doctor for this Patient.
			selectDoctors(docs,c1);
			// Create a second patient and refer it to the first patient.
			Patient c2 = c1;
			list.add(c2);
			
			System.out.println("Wait for doctor's appointment confirmation. Your details are saved.");
			// Create a third patient object
			Patient c3 = new Patient(21,"John");
			list.add(c3);
			
			hospChoice = showHospitals(s.getListOfHospitals());
			docs = s.getListOfDoctors(hospChoice);
			selectDoctors(docs,c3);
			
			System.out.println("Wait for doctor's appointment confirmation. Your details are saved.");
			
			// Call a remote method - bookaDoctor on hospital object.
			String res1 = s.bookaDoctor(list);
			System.out.println();
			System.out.println("REFERENTIAL INTEGRITY ON THE LOCAL MACHINE WHERE THE OBJECTS ARE CREATED!");
			System.out.println("Patient object 1 created on Client Machine "+ "[ "+ list.get(0).toString() + " ]");
			System.out.println("Patient object 2 created on Client Machine " + "[ "+ list.get(1).toString() + " ]");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Patient object 3 created on Client Machine "+ "[ "+ list.get(2).toString() + " ]");
			System.out.println(res1);
			
			}catch (Exception e) {
				System.out.println("Client exception: " + e);
				}
		}

	@SuppressWarnings("resource")
	private static void selectDoctors(ArrayList<String> docs,Patient p1) {
        Scanner input = new Scanner(System.in);
			System.out.println("-----LIST OF DOCTORS-----");
		for(String s:docs)
			System.out.println(s);
		
		System.out.println("Select a doctor. Enter the name of the doctor.");
		String inp = input.nextLine();
		
		p1.setDocsName(inp);
		
		return;
	}

	@SuppressWarnings("resource")
	private static int showHospitals(Map<Integer, String> listOfHospitals) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter 1 to see the list of hospitals.");
		if(input.nextLine()!=null)
		{
		System.out.println("-----LIST OF HOSPITALS-----");
		}
		for(Integer k: listOfHospitals.keySet())
			System.out.println(k + "-" + listOfHospitals.get(k));
		System.out.println("Select one hospital. Enter the valid number.");
		int inp = Integer.parseInt(input.next());
		
		if(inp < 1 || inp > 4){
			System.out.println("Not a valid number");
			System.out.println("Please run the application again!!");
			return 0;
		}
		return inp;	
	}
}

