package com.prodhanidata.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InvalidProtocolBufferException
    {
    	UserSessionProtos.UserSession.SessionKey sessionKey = UserSessionProtos.UserSession.SessionKey.newBuilder()
    																								.setSessionId("FTGS65323GS23ff")
    																								.setUserId("prodhani.java@gmail.com")
    																								.build();
    	 UserSessionProtos.UserSession session = UserSessionProtos.UserSession.newBuilder()
    			 								 .setSessionKey(sessionKey)
								    			 .setMobileNumber("9535385330")
								 				 .build();
 		System.out.println("UserSession "+session.toByteArray());
 		
 		session =	UserSessionProtos.UserSession.parseFrom(session.toByteArray());
 		System.out.println(" UserSessionProtos.UserSession "+session.toString());
    }
}
