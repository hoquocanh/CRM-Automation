package utils.common;

import java.util.Date;

public class RuntimeData {
	public static ThreadLocal<String> TIME_PERIOD = new ThreadLocal<>();
	public static ThreadLocal<Date> CUSTOM_START_DATE = new ThreadLocal<>();
	public static ThreadLocal<Date> CUSTOM_END_DATE = new ThreadLocal<>();
}
