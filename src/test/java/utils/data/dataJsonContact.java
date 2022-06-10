package utils.data;

public enum dataJsonContact {
	//1. Root list
	CONTACTLIST("contactList"),	
	//2. A typical lead
	CONTACTTYPE("contactType"),
	CONTACTNAME("contactName"),			
	EMAILADDRESS("emailAddress"),
	//3.Address
	ADDRESS("address"),	
		//3.1.content of Address
		COUNTRY("country"),
		STREETADDRESS("streetAddress"),	
		CITY("city"),
		STATE("state"),
		POSTALCODE("postalCode"),
	//4.contactChild
	CONTACTCHILD("contactChild"),	
		//3.1.content of contactChild
		CHILDNO("childNo"),
		CHILDCONTACTNAME("childContactName"),
		CHILDEMAILADDRESS("childEmailAddress"),
	//Others	
	TAGS("tags"),	
	SALESTEAM("salesTeam"),
	PARTNERLEVEL("partnerLevel"),
	ACTIVATIONDATE("activationDate");
	
		
	private String value;
	
	dataJsonContact(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
