package pages;
import pages.TESTGenericClass;
public class TEST2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// instance of Integer type
		
        // instance of String type
        TESTGenericClass<String, Integer> sObj
            = new TESTGenericClass<String, Integer>("GeeksForGeeks");
        String text = sObj.getObject("aaa", 1);
        
        TESTGenericClass<Boolean, Integer> bObj
        = new TESTGenericClass<Boolean, Integer>(true);
    Boolean a = bObj.getObject(true, 1);
        System.out.println(a);
	}

}
