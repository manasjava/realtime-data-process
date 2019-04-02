package com.prodhanidata.usertracking.bolt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.storm.task.OutputCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.CollectionUtils;

import com.prodhanidata.cassandra.entity.UserClickURL;
import com.prodhanidata.protobuf.DateUtils;
import com.prodhanidata.protobuf.UserSessionProtos;
import com.prodhanidata.storm.bolt.AbstractPDSBaseRichBolt;

public abstract class  AbstractUserTrackingBolt extends AbstractPDSBaseRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected OutputCollector collector;
	
	protected static ApplicationContext applicationContext = null;
	
	protected void InitializeApplicationContext() {
		if(applicationContext == null) {
			 applicationContext = new AnnotationConfigApplicationContext(
						new String[] { "com.prodhanidata.cassandra", "com.prodhanidata.cassandra.entity",
								"com.prodhanidata.cassandra.repository" });
		}
	}
	
	protected boolean isOrderRequest(UserSessionProtos.UserRequest sessionProto ) {
		boolean isOrder = sessionProto.getClickurlList().stream()
			    .anyMatch(e -> e.getUrl().contains("/orders/") || 
			    			   e.getUrl().contains("/order/")||
			    			   e.getUrl().contains("/checkout/")||
			    			   e.getUrl().contains("/apple-pay/")||
			    			   e.getUrl().contains("/quickCheckout/"));
		return isOrder;
	}
	protected boolean isCartRequest(UserSessionProtos.UserRequest sessionProto ) {
		boolean isOrder = sessionProto.getClickurlList().stream()
			    .anyMatch(e -> e.getUrl().contains("/carts/") || 
			    			   e.getUrl().contains("/cart/"));
		return isOrder;
	}
	
//	protected void saveJSONMessage(byte[] bytesArray) {
//
//		String userRequest = new String(bytesArray, StandardCharsets.UTF_8);
//
//		System.out.println("JSON string  " + userRequest);
//
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			UserSession sessionEntity = mapper.readValue(userRequest, UserSession.class);
//
//			sessionEntity.getSessionKey().setId(UUIDs.timeBased());
//
//			Date now = Calendar.getInstance().getTime();
//			sessionEntity.setCreatedOn(now);
//			sessionEntity.setLastModified(now);
//
//			sessionEntity.getClickurl().get(0).setCreatedOn(now);
//			sessionEntity.getClickurl().get(0).setLastmodifed(now);
//
//			List<UserSession> userSessionList = sessionRepository
//					.findUserSessionBySessionKeySessionId(sessionEntity.getSessionKey().getSessionId());
//			if (!CollectionUtils.isEmpty(userSessionList)) {
//				UserSession userSession = userSessionList.get(0);
//				sessionEntity.getSessionKey().setId(userSession.getSessionKey().getId());
//				sessionEntity.getClickurl().addAll(userSession.getClickurl());
//			}
//			sessionRepository.save(sessionEntity);
//
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}

//	protected void saveProtobufMessage(byte[] bytesArray) throws InvalidProtocolBufferException {
//		UserSessionProtos.UserRequest sessionProto = UserSessionProtos.UserRequest.parseFrom(bytesArray);
//		saveProtobufMessage(sessionProto);
//
//	}
	
	protected List<UserClickURL> getUserClickURLList(
			java.util.List<com.prodhanidata.protobuf.UserSessionProtos.UserClickURL> userClickUrl, Date now) {
		List<UserClickURL> clickURLList = new ArrayList<UserClickURL>();
		for (UserSessionProtos.UserClickURL clickURL : userClickUrl) {
			UserClickURL userClickURL = new UserClickURL();
			userClickURL.setUrl(clickURL.getUrl());
			userClickURL.setRequestTime(DateUtils.getDateFromString(clickURL.getRequestTime()));
			userClickURL.setResponseTime(DateUtils.getDateFromString(clickURL.getResponseTime()));
			userClickURL.setRequestAction(clickURL.getRequestAction());
			userClickURL.setResponseStatus(clickURL.getResponseStatus());
			userClickURL.setRequestMethodType(clickURL.getRequestMethodType());
			if (!CollectionUtils.isEmpty(clickURL.getUserClickURLParamList())) {
				userClickURL.setUserClickURLParam(clickURL.getUserClickURLParamList().stream()
						.collect(Collectors.toMap(com.prodhanidata.protobuf.UserSessionProtos.MapFieldEntry::getKey,
								com.prodhanidata.protobuf.UserSessionProtos.MapFieldEntry::getValue)));
			}
			userClickURL.setCreatedOn(now);
			userClickURL.setLastmodifed(now);
			clickURLList.add(userClickURL);
		}
		return clickURLList;
	}
	
	protected void saveUserSesion() {
//		try {
//
//			JSONObject userRequestJSON = null;
//			try {
//				JSONParser parser = new JSONParser();
//				userRequestJSON = (JSONObject) parser.parse(userRequest);
//			} catch (Exception e) {
//				System.out.println("Exception in fetching value by field bytes");
//				e.printStackTrace();
//			}
//
//			if (userRequestJSON != null) {
//				JSONObject  sessionKey = (JSONObject) userRequestJSON.get("sessionKey");
//				
//				JSONArray clickurlJSONArray = (JSONArray) userRequestJSON.get("clickurl");
//				JSONObject urlInfoJSON = (JSONObject) clickurlJSONArray.get(0);
////				UserSession user = new UserSession();
////				SessionKey key = new SessionKey(UUIDs.timeBased(),CommonUtil.getStringFromJSONObject(userRequestJSON.get("sessionId")), 
////						CommonUtil.getStringFromJSONObject(userRequestJSON.get("userId")));
////				user.setSessionKey(key);
////				user.setName(CommonUtil.getStringFromJSONObject(userRequestJSON.get("userName")));
////				user.setMobileNumber(CommonUtil.getStringFromJSONObject(userRequestJSON.get("mobileNumber")));
////				user.setUiExperienceLabel(CommonUtil.getStringFromJSONObject(userRequestJSON.get("uiLabel")));
////				user.setCreatedOn(Calendar.getInstance().getTime());
////				user.setLastModified(Calendar.getInstance().getTime());
////				
////				
////				UserClickURL clickURL = new UserClickURL();
////				clickURL.setUrl(CommonUtil.getStringFromJSONObject(userRequestJSON.get("url")));
////				clickURL.setResponseStatus(CommonUtil.getIntegerFromJSONObject(userRequestJSON.get("urlStatus")));
////				
////				List<UserClickURL> clickURLList = new ArrayList<>();
////				clickURLList.add(clickURL);
////				user.setClickurl(clickURLList);
////				sessionRepository.save(user);
//
//				String userSession = "INSERT INTO usertracking.sessions (id,sessionId,userId,mobile,name,uiExperienceLabel,clickurl,createdOn,lastModified)"
//						+
//
//						"VALUES (" +
//
//						UUIDs.timeBased() + "," +
//
//						CommonUtil.getCQLComplienceValue(sessionKey.get("sessionId")) +
//
//						CommonUtil.getCQLComplienceValue(sessionKey.get("userId")) +
//
//						CommonUtil.getCQLComplienceValue(userRequestJSON.get("mobileNumber")) +
//
//						CommonUtil.getCQLComplienceValue(userRequestJSON.get("userName")) +
//
//						CommonUtil.getCQLComplienceValue(userRequestJSON.get("uiLabel")) +
//
//						"[{ " + "url :" + CommonUtil.getCQLComplienceValue(urlInfoJSON.get("url"))
//						+ "responseStatus : "
//						+ CommonUtil.getCQLComplienceIntegerValueWithNoComma(urlInfoJSON.get("responseStatus")) + ","
//						+ "requestTime : " + CommonUtil.getCQLComplienceValue(urlInfoJSON.get("requestTime"))
//						+ "responseTime : " + CommonUtil.getCQLComplienceValue(urlInfoJSON.get("responseTime"))
//						+ "requestMethodType : " + CommonUtil.getCQLComplienceValue(urlInfoJSON.get("requestMethodType"))
//						+ "requestAction : " + CommonUtil.getCQLComplienceValue(urlInfoJSON.get("requestAction"))
//						+ "UserClickURLParam : " + CommonUtil.getCQLComplienceJSON(urlInfoJSON.get("userClickURLParam"))
//						+ "createdOn : toTimeStamp(now())," + "lastmodifed : toTimeStamp(now())" + "}]," +
//
//						"toTimeStamp(now())," +
//
//						"toTimeStamp(now())" +
//
//						")";
//
//				System.out.println("Constructed querey " + userSession);
//				System.out.println("Constructed querey End");
//
//				CassandraConnector cassandraConnector = null;
//				try {
//					cassandraConnector = new CassandraConnector();
//					cassandraConnector.connect("127.0.0.1", 9042);
//					Session session = cassandraConnector.getSession();
//					session.execute(userSession);
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					if (cassandraConnector != null && cassandraConnector.getSession() != null) {
//						cassandraConnector.close();
//					}
//				}
//			}
//
//			System.out.println("UserRequestBolt cassandra save end");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
