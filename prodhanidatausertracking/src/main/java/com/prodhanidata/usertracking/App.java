package com.prodhanidata.usertracking;

import com.google.protobuf.InvalidProtocolBufferException;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//    	System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "local,default,userRequestTopology");
//    	//BootstrapApplication.localSubmit();
//    	
//    	byte[] byteArray = { 80, 65, 78, 75, 65, 74 };
//
//    	String str = new String(byteArray, StandardCharsets.UTF_8);
//    	System.out.println(str);
//    	
//    	String name = "{'naem':'rezaul','add':{'vill':'kuk'}}";
//    	System.out.println("name byte "+name.getBytes());
//    	
//    	 str = new String(name.getBytes(), StandardCharsets.UTF_8);
//
//    	System.out.println("name by string object  "+str);
//    	
//    	
//    	String jsonString = "{\"sessionId\":\"19A633C652BE0D0ABB7621D02926095D\",\"uiLabel\":\"sdsad\"}";
//    	
//    	
//    	JSONParser parser = new JSONParser();
//    	JSONObject json;
//		try {
//			json = (JSONObject) parser.parse(jsonString);
//			
//			String query = ""+getQueryComplienceValue(json.get("sessionId"))+","+getQueryComplienceValue(json.get("uiLabel"));
//			
//			System.out.println("query"+query);
//			
//			
//			
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	

    	
    	
    	try {
			testProtobuf();
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	

    }
    
//    private static String getQueryComplienceValue(Object value) {
//		if(value==null) {
//			return "";
//		}else if(value instanceof String){
//			return "'"+(String)value+"'";
//		}else {
//			return value.toString();
//		}
//	}
    
    private static void testProtobuf() throws InvalidProtocolBufferException {
    	
    	com.prodhanidata.protobuf.UserSessionProtos.UserSession session = com.prodhanidata.protobuf.UserSessionProtos.UserSession.newBuilder().setMobileNumber("9535385330").build();
		System.out.println("UserSession "+session.toByteArray());
		
		session =	com.prodhanidata.protobuf.UserSessionProtos.UserSession.parseFrom(session.toByteArray());
		System.out.println(" UserSessionProtos.UserSession "+session.toString());
    }
}
