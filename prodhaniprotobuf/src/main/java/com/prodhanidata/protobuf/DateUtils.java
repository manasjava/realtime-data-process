package com.prodhanidata.protobuf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DateUtils {

	private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	public static String getStringFromDate(Date date) {
		return formatter.format(date);
	}
	public static Date getDateFromString(String source) {
		
		try {
			return formatter.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(getRandom("13,14"));
	}
	
	public static String getRandom(String value)
	{
		
		final List<String> originUrlList = Arrays.asList(value.trim().split(","));
		return originUrlList.get(random.nextInt(originUrlList.size()));
	}
	private static final Random random = new Random();

}
