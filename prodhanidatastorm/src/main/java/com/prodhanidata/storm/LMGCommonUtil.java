package com.prodhanidata.storm;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LMGCommonUtil {
	
	
	public static String getStringFromJSONObject(Object value) {
		if(value instanceof String) {
			return (String) value;
		}else {
			return "";
		}
	}
	public static Integer getIntegerFromJSONObject(Object value) {
		if(value instanceof Integer) {
			return (Integer) value;
		}else {
			return -1;
		}
	}

	public static String getCQLComplienceValue(Object value) {
		return getCQLComplienceValueWithNoComma(value)+",";
	}
	
	public static String getCQLComplienceValueWithNoComma(Object value) {
		if(value==null) {
			return "'"+""+"'";
		}else if(value instanceof String){
			return "'"+(String)value+"'";
		}else if(value instanceof Integer){
			return value.toString();
		}else {
			return "'"+value.toString()+"'";
		}
	}
	public static int getCQLComplienceIntegerValueWithNoComma(Object value) {
		
		if(value==null) {
			return -1;
		}else if(value instanceof String){
			return Integer.valueOf(value.toString());
		}else if(value instanceof Integer){
			return (Integer)value;
		}else {
			return -1;
		}
	}
	public static String getCQLComplienceJSON(Object json) {
		if(json==null) {
			return "{}"+",";
		}else if(json instanceof String){
			return ((String)json).replace('\"', '\'')+",";
		}else {
			return json.toString().replace('\"', '\'')+",";
		}
	}
	
	public static void main(String[] args) throws ParseException {
//		String name = "{\"naem\":\"rezaul\"}";
//		System.out.println(name);
//		System.out.println(getCQLComplienceJSON(name));
//		
//		
//		
		String userRequest = "{\"name\":\"rezaul\",\"add\":{\"vill\":\"kuk\" ,\"loc\":{\"str\":\"st1\"}     }}";
		
		JSONParser parser = new JSONParser();
		JSONObject userRequestJSON = (JSONObject) parser.parse(userRequest);
		
		System.out.println("user request  userRequestJSON "+userRequest);
		
		System.out.println("name "+ userRequestJSON.get("name"));
		
		System.out.println("name  "+ getCQLComplienceValue(userRequestJSON.get("name")));
		
		System.out.println("add  "+ getCQLComplienceJSON(userRequestJSON.get("add")));
		System.out.println("vill  "+ getCQLComplienceValue(((JSONObject)userRequestJSON.get("add")).get("vill")));
		System.out.println("loc  "+ getCQLComplienceValue(((JSONObject)userRequestJSON.get("add")).get("loc")));
		
		
		
	}
}
