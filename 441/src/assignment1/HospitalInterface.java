package assignment1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;
 
   public interface HospitalInterface extends Remote {
	   public String bookaDoctor(ArrayList<Patient> pList) throws RemoteException;
	   public String lookUp(int findID) throws RemoteException;
	   public Map<Integer,String> getListOfHospitals() throws RemoteException;
	   public ArrayList<String> getListOfDoctors(int value) throws RemoteException;
   }

