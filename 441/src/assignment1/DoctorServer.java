package assignment1;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;   
import java.util.ArrayList;
 
public class DoctorServer implements DoctorInterface{
	   public static void main (String[] argv) {
		   try {
			   //System.setSecurityManager(new RMISecurityManager());
			   DoctorInterface doc1 = new DoctorServer();	
			   DoctorInterface stub = (DoctorInterface) UnicastRemoteObject.exportObject(doc1, 0);
			   Registry registry = LocateRegistry.getRegistry();
	           registry.bind("doctor", stub);// Make the doctor object available to other clients.
	           System.out.println("Doctor Server Ready!");
			   }
		   	catch (Exception e) {
				   System.out.println("Doctor Server failed: " + e);
				}
		   }

	// This is a remote method exposed by the Doctor Server, which receives the Patient objects from Hospitals
	@Override
	public String sendDetails(ArrayList<Patient> dList) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(null);
			InsuranceInterface cl = (InsuranceInterface)registry.lookup("insurance");
			// Submit a claim to a remote insurance object on Insurance Server.
			cl.submitClaim(dList);
			for(Patient p: dList)
				System.out.println("Claim submitted to insurance company for : "+ "[ " + p.toString() + " ]");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
