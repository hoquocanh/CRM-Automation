package utils.data;

public enum dataJsonLead {
	//Root list
	LEADLIST("leadsList"),	
	//A typical lead
	LEADTYPE("leadType"),
	LEADNAME("leadName"),		
	EMAILADDRESS("emailAddress"),
	LEADFORM("leadForm"),
	COMPANYNAME("companyName"),
	//Address
	ADDRESS("address"),
	STREETADDRESS("streetAddress"),
	DISTRICT("district"),
	CITY("city"),
	STATE("state"),
	POSTALCODE("postalCode"),
	
	//Others
	CONTACTNAME("contactname"),	
	TAGS("tags"),	
	SALESTEAM("salesTeam"),
	CREATEMANUAL("createManual");
	
		
	private String value;
	
	dataJsonLead(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
