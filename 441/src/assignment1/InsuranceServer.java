package assignment1;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;   
import java.util.ArrayList;
 
public class InsuranceServer implements InsuranceInterface{
	   ArrayList<Integer> idList;
	   public static void main (String[] argv) {
		   try {
			   //System.setSecurityManager(new RMISecurityManager());
			   InsuranceInterface ad = new InsuranceServer();	
			   InsuranceInterface stub = (InsuranceInterface) UnicastRemoteObject.exportObject(ad, 0);
			   LocateRegistry.createRegistry(1099); // This server creates the registry on default port and other server connects after it.
			   Registry registry = LocateRegistry.getRegistry();
	           registry.bind("insurance", stub);// Make the insurance object available to clients.
	           System.out.println("Insurance Server Ready!");
			   }
		   	catch (Exception e) {
				   System.out.println("Insurance Server failed: " + e);
				}
		   }

	// This is a method exposed by Insurance Server, for submitting the claims on Doctor's Server.
	@Override
	public String submitClaim(ArrayList<Patient> pts) throws RemoteException {
		
		for(Patient p: pts)
		{
		System.out.println("[" + p.toString()+ " ]" + " has submitted a claim!");
		Registry registry = LocateRegistry.getRegistry(null);
		try {
			HospitalInterface s = (HospitalInterface)registry.lookup("hospital");
			String y = s.lookUp(p.getID());
			System.out.println("FOR SHOWING CIRCULARITY - " + " Name obtained from Hospital Server: " + "[ "+ y +" ]");
			System.out.println();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return null;
		}
		
		System.out.println();
		System.out.println("REFERENTIAL INTEGRITY ON THE REMOTE SERVER!");
		System.out.println("Object1: "+ "[ " + pts.get(0).toString() + " ]");
		System.out.println("Object2: "+  "[ " + pts.get(1).toString() + " ]");
		
		return "Claim Submitted";
	}
}