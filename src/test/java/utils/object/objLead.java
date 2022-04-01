package utils.object;

import java.util.Iterator;
import java.util.Map;

import utils.data.dataJsonLead;

public class objLead {
	
	private String leadName = null;
	private String emailAddress = null;	
	private String leadForm = null;
	private String companyName = null;
	private String street = null;
	private String tags = null;	
	private String salesTeam = null;
	private Boolean createManual = false;
	
	
	
	public objLead() {
		
	}
	
	
	public objLead(Iterator interator) {
		
		
		try {
		Iterator itr1 = interator;
		while (interator.hasNext()) {

        	Iterator<Map.Entry> itr2 = ((Map) itr1.next()).entrySet().iterator();
            while (itr2.hasNext()) {
                Map.Entry pair = itr2.next();
                if(pair.getKey().equals(dataJsonLead.ADDRESS.getValue()) == false )
                {
                	
                	System.out.println(pair.getKey() + " : " + pair.getValue());
                	
                }
            }
        }
    } 
    catch (Exception e) 
    {
        e.printStackTrace();
    } 
		
	}

//	public Object getJsonValue(Iterator interator, String key)
//	{
//		Object obj = new Object();
//		
//		try {
//			Iterator itr1 = interator;
//			while (interator.hasNext()) {
//
//	        	Iterator<Map.Entry> itr2 = ((Map) itr1.next()).entrySet().iterator();
//	            while (itr2.hasNext()) {
//	                Map.Entry pair = itr2.next();
//	                if(pair.getKey().equals(dataLead.ADDRESS.getValue()) == false )
//	                {
//	                	
//	                	for (dataLead d : dataLead.values())
//	                	{
//	                		if(pair.getKey().equals(d.getValue()) == true )
//	                			obj = pair.getValue();
//	                			System.out.println(pair.getKey() + " : " + pair.getValue());
//	                			System.out.println("Gotten key: "+ d.getValue()+ " Gotten value: "+ (String)obj);
//	                			return obj;
//	                	}
//	                }
//	            }
//	        }
//	    } 
//	    catch (Exception e) 
//	    {
//	        e.printStackTrace();
//	    } 
//		
//		return null;
//	}
	
	public String getLeadName() {
		return leadName;
	}
	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}
	public void setLeadName(Iterator interator) {
		this.leadName = leadName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getLeadForm() {
		return leadForm;
	}
	public void setLeadForm(String leadForm) {
		this.leadForm = leadForm;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getSalesTeam() {
		return salesTeam;
	}
	public void setSalesTeam(String salesTeam) {
		this.salesTeam = salesTeam;
	}
	public Boolean getCreateManual() {
		return createManual;
	}
	public void setCreateManual(Boolean createManual) {
		this.createManual = createManual;
	}
}
