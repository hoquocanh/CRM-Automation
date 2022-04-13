package pages;

public class TESTGenericClass<T, N extends Integer> {
    // An object of type T is declared
    T obj;
    N in;
    TESTGenericClass(){}
    TESTGenericClass(T obj) { this.obj = obj; } // constructor
    
    public T getObject() { return this.obj; }
    //public N getObject() { return this.in; }
    public T getObject(T text, N inte) { 
    	Integer a = inte + 10; 
    	System.out.println("Text:" + text);
    	System.out.println("Tinh: "+ a);
    	return text; }
}
  

