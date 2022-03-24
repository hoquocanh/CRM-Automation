package utils.data;

public enum dataGenralHomePage {

	
	CRM("CRM");
	
		
	private String value;
	
	dataGenralHomePage(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
