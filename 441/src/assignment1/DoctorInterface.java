package assignment1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
 
   public interface DoctorInterface extends Remote {
	   public String sendDetails(ArrayList<Patient> dList) throws RemoteException;
   }

