package assignment1;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;   
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
 
public class HospitalServer implements HospitalInterface{
	 ArrayList<Patient> globalList;  
	
	// This is to hold the name of all the service hospitals.
	   Map<Integer,String> hospitalList = new HashMap<>();
	   
	// This is to hold the name of all doctors in each hospital.
	   Map<Integer,ArrayList<String>> doctorList = new HashMap<>();
	   
	   public HospitalServer() {}
	   
	   // Initializes the list of hospital
	   public void addHospitals(){
		   hospitalList.put(1,"Rush Hospital");
		   hospitalList.put(2, "Noble Hospital");
		   hospitalList.put(3, "Family Medical Center");
		   hospitalList.put(4, "UIC Hospital");
	   }
	   
	   // To get the list of all the hospitals in the system.
	   public Map<Integer,String> getListOfHospitals(){
		   return hospitalList;
	   }
	   
	   // Initializes the list of Doctors for each hospital
	   public void addDoctors(){
		   doctorList.put(1,new ArrayList<String>(Arrays.asList("Dr. Kumar","Dr. Anderson","Dr. Harold")));
		   doctorList.put(2,new ArrayList<String>(Arrays.asList("Dr. Smith","Dr. Ericsson","Dr. Yu")));
		   doctorList.put(3,new ArrayList<String>(Arrays.asList("Dr. Bell","Dr. Turan","Dr. Cheek")));
		   doctorList.put(4,new ArrayList<String>(Arrays.asList("Dr. Fyfe","Dr. Westland","Dr. California")));
	   }
	   
	// To get the list of all the doctors on the basis of hospital selected by the Patient.
		public ArrayList<String> getListOfDoctors(int value) throws RemoteException {
			return doctorList.get(value);
		}
	   
	   public static void main (String[] argv) {
		   
		   try {
			   // Create a hospital object and export it on RMI Registry.
			   HospitalServer hos = new HospitalServer();	
			   hos.addHospitals();
			   hos.addDoctors();
			   HospitalInterface stub = (HospitalInterface) UnicastRemoteObject.exportObject(hos, 0);
			   Registry registry = LocateRegistry.getRegistry();
	           registry.bind("hospital", stub); // Make the hospital object available to clients.
	           System.out.println("Hospital Server Ready!");
			   }
		   	catch (Exception e) {
				   System.out.println("Hospital Server failed: " + e);
				}
		   }

	// This method takes and integer as an input from patient and shows the list of doctor in a hospital.
	   
	// This method is to book an appointment with the selected doctor for Patients.
	@Override
	public String bookaDoctor(ArrayList<Patient> pList) throws RemoteException {
		try {
			globalList = pList;
			Registry registry = LocateRegistry.getRegistry(null);
			DoctorInterface s = (DoctorInterface)registry.lookup("doctor");
			s.sendDetails(pList); // Send the patient details to the doctor.
			System.out.println("Details Sent to Doctor for "+ "[ "+ pList.get(0).toString()+ " ]");
			System.out.println("Details Sent to Doctor for "+ "[ "+ pList.get(1).toString()+ " ]");
			System.out.println("Details Sent to Doctor for "+ "[ "+ pList.get(2).toString()+ " ]");
			return "hello, patients";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// This method is useful for Insurance Server to look-up for Patient's details on Hospital Server. 
	//Basically to show circularity. Insurance Server calls to Hospital server.
	@Override
	public String lookUp(int findID) throws RemoteException {
		
		for(Patient o: globalList)
		{
			if(o.getID()== findID)
				return o.getName();
		}
		return findID + " Not found in the Hospital Database";
	}

	
}