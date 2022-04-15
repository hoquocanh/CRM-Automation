package pages;
import org.openqa.selenium.By;

import pages.TESTGenericClass;
public class TEST2 {
	public static By dynamicControl (By element, String replaceString, String replaceBy)
	{
		By dynamicElement=null;
		
		String x = element.toString().replaceAll("By.xpath: ", "");
        String y = x.replaceAll(replaceString, replaceBy);
		
        dynamicElement= By.xpath(y);
        
		return dynamicElement;
		
	}
	public static void main(String[] args) {
		By lnk_dynamic_source_lead = By.xpath("//tr[contains(@class,'o_data_row')]/td[contains(text(),'REPLACE#1')]");
		// TODO Auto-generated method stub
		// instance of Integer type
		
        // instance of String type
//        TESTGenericClass<String, Integer> sObj
//            = new TESTGenericClass<String, Integer>("GeeksForGeeks");
//        String text = sObj.getObject("aaa", 1);
//        
//        TESTGenericClass<Boolean, Integer> bObj
//        = new TESTGenericClass<Boolean, Integer>(true);
//    Boolean a = bObj.getObject(true, 1);
        
		By replace_lnk_dynamic_source_lead = dynamicControl(lnk_dynamic_source_lead,"REPLACE#1","soure lead");
        
        System.out.println(replace_lnk_dynamic_source_lead);
	}

}
