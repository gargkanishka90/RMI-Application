package assignment1;


@SuppressWarnings("serial")
public class Patient implements java.io.Serializable{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String docs_name;
	
	public Patient(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public int getID(){return this.id;}
	public String getName(){return this.name;}
	public String getDocName(){return this.docs_name;}
	
	public void setID(int id){this.id = id;}
	public void setName(String name){this.name = name;}
	public void setDocsName(String name){this.docs_name = name;}
	
	@Override
	public String toString(){
		return "Name: "+ this.name + " ID: " + this.id + " Doctor's name: "+ this.docs_name;
	}
}

