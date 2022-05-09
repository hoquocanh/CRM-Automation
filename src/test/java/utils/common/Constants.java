package utils.common;

import utils.helper.PropertiesHelper;

public class Constants {

	public static final String  BROWSER_SETTING_FILE = "src/test/resources/browsers.setting.properties";
	public static final String  DATA_SOURCE = "src/test/resources/";
	
	//Gateway API
	public static final String  URL_HTTP = PropertiesHelper.getPropValue("profile.url_http");
	public static final String  URL_HTTPS = PropertiesHelper.getPropValue("profile.url_https");
	public static final String  APIVER = PropertiesHelper.getPropValue("profile.apiver");
	
	//-------------------RESTFull API-------------------------------------//
	//Common elements
	public static final String  URL_HTTP_CONFIG_A = PropertiesHelper.getPropValue("profile.url_http_configuration_A");
	public static final String  URL_HTTP_CONFIG_B = PropertiesHelper.getPropValue("profile.url_http_configuration_B");
	public static final String  URL_HTTP_CONFIG_C = PropertiesHelper.getPropValue("profile.url_http_configuration_C");
	public static final String  RACKID_CONFIG_A = PropertiesHelper.getPropValue("profile.rackid_configuration_A");
	public static final String  RACKID_CONFIG_B = PropertiesHelper.getPropValue("profile.rackid_configuration_B");
	public static final String  RACKID_CONFIG_C = PropertiesHelper.getPropValue("profile.rackid_configuration_C");
	public static final String  AUTHENTICATION_USERNAME = PropertiesHelper.getPropValue("profile.authentication_username");
	public static final String  AUTHENTICATION_PASSWORD = PropertiesHelper.getPropValue("profile.authentication_password");
	
	//Rack Information API
	
	public static final String  APIVER_ALL_RACKS = PropertiesHelper.getPropValue("profile.apiver_all_racks");
	public static final String  APIVER_INCORRECT_RACKS = PropertiesHelper.getPropValue("profile.apiver_incorrect_racks");
	public static final String  ROOT_OF_RACK_LIST = "rackList.";
	
	//Controller Information API
	
	public static final String  APIVER_ALL_CONTROLLERS = PropertiesHelper.getPropValue("profile.apiver_all_controllers");
	public static final String  ROOT_OF_CONTROLLER_LIST = "controllerList.";
	
	//Panel Information API
	
	public static final String  APIVER_ALL_PANELS = PropertiesHelper.getPropValue("profile.apiver_all_panels");
	public static final String  APIVER_PANELS_PARAMETER_RACKID = PropertiesHelper.getPropValue("profile.apiver_panels_parameter_rackid");
	public static final String  ROOT_OF_PANEL_LIST = "panelList.";
	
	public static final String	SAMPLE_COLOR	= "rgba(233, 33, 39, 1)";
	public static final String	SAMPLE_CALLID	= "39969";

	public static final int		SAMPLE_AGENTID				= 856;
	public static final String	REGEX_CALLER_FORMAT			= "[1-9][0-9][0-9][0-9][0-9]";
	public static final String	REGEX_TIME_FORMAT			= "(0[0-9]|1[0-2]):(0[1-9]|[1-5][0-9])\\s[A|P]M";
	public static final String	REGEX_DURATION_FORMAT		= "0[1-9]|[1-5][0-9]m\\s[0-5][0-9]s";
	public static final String	REGEX_URL_FORMAT			= "url[(\"]*[/a-z-]*.svg[\");]*";
	public static final int		EXPECTED_NUMBER_AGENT		= 3;

	// Range random value
	public static final float	MIN_HEIGHT	= 1.45f;
	public static final float	MAX_HEIGHT	= 1.9f;
	public static final int		MIN_WEIGHT	= 40;
	public static final int		MAX_WEIGHT	= 90;
	public static final int		MIN_OLD		= 18;
	public static final int		MAX_OLD		= 70;

	// Timeout variables
	public static final int	PRECONDITION_TIME		= 360;
	public static final int	PRECONDITION_INTERVAL	= 5;
	public static final int	LONG_TIME				= 180;
	public static final int	SHORT_TIME				= 60;
	public static final int	LOADING_TIME			= 30;
	public static final int	WAITING_TIME			= 3;
	public static final int	POSSIBLE_TIME			= 2;
	public static final int	SLEEP_TIME				= 3;

	// Create Issues variables
	public static final String PROJECT_NAME = "EHDShelf";

	// Date format
	public static final String	DATE_FORMAT_FOR_API			= "yyyy-MM-dd";
	public static final String	DATE_FORMAT_FOR_CUSTOMER	= "dd/MM/yyyy";

	// Search filters
	public static final int	CALL_LENGTH_HOUR_FROM	= 0;
	public static final int	CALL_LENGTH_MIN_FROM	= 1;
	public static final int	CALL_LENGTH_HOUR_TO		= 0;
	public static final int	CALL_LENGTH_MIN_TO		= 10;
	public static final int	CALL_LENGTH_MIN_TO_ALL	= 60;
	
	// Wait
	public static final int	WAIT_PAGE_LOAD	= 10000;
	public static final int	WAIT_ELEMENT_DISPLAY	= 500;
	
	
	//CRM
	public static final String UAT_LINK = PropertiesHelper.getPropValue("profile.uat_link");
	public static final String UAT_USERNAME = PropertiesHelper.getPropValue("profile.uat_username");
	public static final String UAT_PASSWORD = PropertiesHelper.getPropValue("profile.uat_password");	
	public static final String TEST_DOMAIN_EMAIL = "@test.com";
	public static final String GMAIL_DOMAIN_EMAIL = "@gmail.com";
	public static final String COMPANY_DOMAIN_EMAIL = "@company.com";
	public static final String TARGET_LEAD = "target lead";
	public static final String SOURCE_LEAD = "source lead";
}
