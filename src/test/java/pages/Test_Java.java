package pages;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.common.Constants;

public class Test_Java {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		
		Timestamp timestamp = new Timestamp(date.getTime());
		
		//Change the domain of Company to be as "@individual_2022_05_05T10_46_37.com
		String refineDomain = (String) sdf.format(timestamp);	
		System.out.println("Current date: "+ refineDomain);
	}

}
