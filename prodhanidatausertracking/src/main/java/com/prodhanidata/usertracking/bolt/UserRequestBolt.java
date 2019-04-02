package com.prodhanidata.usertracking.bolt;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.tuple.Tuple;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.datastax.driver.core.utils.UUIDs;
import com.google.protobuf.InvalidProtocolBufferException;
import com.prodhanidata.cassandra.entity.SessionKey;
import com.prodhanidata.cassandra.entity.UserClickURL;
import com.prodhanidata.cassandra.entity.UserSession;
import com.prodhanidata.cassandra.repository.UserSessionRepository;
import com.prodhanidata.protobuf.UserSessionProtos;

@Component("userRequestBolt")
@Profile("userRequestTopology")
@DependsOn({ "userRequestTopologyBuilder" })
public class UserRequestBolt extends AbstractUserTrackingBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String STREAM_TYPE ="userRequestStream";
  
	protected UserSessionRepository sessionRepository;
	
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

		System.out.println("UserRequestBolt prepare Start");

		this.collector = collector;
		InitializeApplicationContext();
		sessionRepository = applicationContext.getBean(UserSessionRepository.class);

		System.out.println("UserRequestBolt prepare End");

	}

	@Override
	public void execute(Tuple tuple) {

		System.out.println("UserRequestBolt cassandra save start ");
		
		UserSessionProtos.UserRequest userRequest = (UserSessionProtos.UserRequest) tuple.getValueByField("content");

		System.out.println("UserRequestBolt UserSessionProtos.UserSession  " + userRequest);

		try {
			saveProtobufMessage(userRequest);
			System.out.println(" Insertion for protobuf message done ");
			collector.ack(tuple);
			
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void saveProtobufMessage(UserSessionProtos.UserRequest userRequest) throws InvalidProtocolBufferException {

		UserSession sessionEntity = null;
		Date now = Calendar.getInstance().getTime();

		List<UserSession> userSessionList = sessionRepository
				.findUserSessionBySessionKeySessionId(userRequest.getSessionId());
		
		List<UserClickURL> clickURLs = getUserClickURLList(userRequest.getClickurlList(), now);
		if (!CollectionUtils.isEmpty(userSessionList)) {
			sessionEntity = userSessionList.get(0);
			sessionEntity.getClickurl().addAll(clickURLs);
			sessionEntity.setLastModified(now);
		} else {
			sessionEntity = new UserSession();

			SessionKey sessionKey = new SessionKey();
			sessionKey.setId(UUIDs.timeBased());
			sessionKey.setSessionId(userRequest.getSessionId());
			sessionKey.setUserId(userRequest.getUserId());
			sessionEntity.setSessionKey(sessionKey);

			sessionEntity.setClickurl(clickURLs);
			sessionEntity.setLastModified(now);
			sessionEntity.setCreatedOn(now);
		}
		if (userRequest.hasName()) {
			sessionEntity.setName(userRequest.getName());
		}
		if (userRequest.hasMobileNumber()) {
			sessionEntity.setMobileNumber(userRequest.getMobileNumber());
		}
		if (userRequest.hasUiExperienceLabel()) {
			sessionEntity.setUiExperienceLabel(userRequest.getUiExperienceLabel());
		}
		sessionRepository.save(sessionEntity);

	}

	@Override
	public void cleanup() {
		System.out.println("UserRequestBolt test  cleanup");
	}

	@Override
	public void declareOutputFields(org.apache.storm.topology.OutputFieldsDeclarer declarer) {
		System.out.println("UserRequestBolt test declareOutputFields");
	}
}
