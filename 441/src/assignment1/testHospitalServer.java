package assignment1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

public class testHospitalServer {

	@Test
	public void testAddHospitals() {
		HospitalServer testObj = new HospitalServer();
		assertEquals("Before Adding Hospitals",0,testObj.hospitalList.size());
		testObj.addHospitals();
		assertEquals("After Adding Hospitals",4,testObj.hospitalList.size());
	}
	
	@Test
	public void testGetListOfHospitals(){
		HospitalServer testObj = new HospitalServer();
		assertEquals("Before Adding Hospitals",0,testObj.hospitalList.size());
		testObj.addHospitals();
		Map<Integer,String> tempHosp = testObj.getListOfHospitals();
		String value = tempHosp.get(5);
		assertNull(value);
	}

	@Test
	public void testAddDoctors() {
		HospitalServer testObj = new HospitalServer();
		assertEquals("Before Adding Doctors",0,testObj.doctorList.size());
		testObj.addDoctors();
		assertEquals("After Adding Doctors",4,testObj.doctorList.size());
	}
	
	@Test
	public void testGetListOfDoctors() throws Exception {
		HospitalServer testObj = new HospitalServer();
		assertEquals("Before Adding Doctors",0,testObj.doctorList.size());
		testObj.addDoctors();
		ArrayList<String> tempDocList = testObj.getListOfDoctors(5);
		assertNull(tempDocList);
	}
}
