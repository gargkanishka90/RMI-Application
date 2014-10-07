package assignment1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
 
   public interface InsuranceInterface extends Remote {
	   public String submitClaim(ArrayList<Patient> pt) throws RemoteException;
   }
