package utils.data;

public enum dataJsonLead {
	//1. Root list
	LEADLIST("leadsList"),	
	//2. A typical lead
	LEADTYPE("leadType"),
	LEADNAME("leadName"),		
	EMAILADDRESS("emailAddress"),
	LEADFORM("leadForm"),
	COMPANYNAME("companyName"),
	//3.Address
	ADDRESS("address"),	
	//3.1.content of Address
	COUNTRY("country"),
	STREETADDRESS("streetAddress"),	
	CITY("city"),
	STATE("state"),
	POSTALCODE("postalCode"),
	
	//Others
	CONTACTNAME("contactname"),	
	TAGS("tags"),	
	SALESTEAM("salesTeam"),
	STAGEOPP("stageOpp"),
	PRIORITY("priority"),
	TESTCASETYPE("testcaseType"),
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
