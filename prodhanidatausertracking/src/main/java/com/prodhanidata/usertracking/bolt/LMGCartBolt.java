package com.prodhanidata.usertracking.bolt;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.datastax.driver.core.utils.UUIDs;
import com.google.protobuf.InvalidProtocolBufferException;
import com.prodhanidata.cassandra.entity.CartEntity;
import com.prodhanidata.cassandra.entity.CartKey;
import com.prodhanidata.cassandra.entity.UserClickURL;
import com.prodhanidata.cassandra.repository.CartsRepository;
import com.prodhanidata.protobuf.UserSessionProtos;

@Component("lmgCartBolt")
@Profile("userRequestTopology")
@DependsOn({ "userRequestTopology" })
public class LMGCartBolt extends AbstractLMGUserTrackingBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CartsRepository cartRepository;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		
		System.out.println("LMGCartBolt prepare Start");
		
		this.collector = collector;
		InitializeApplicationContext();
		cartRepository = applicationContext.getBean(CartsRepository.class);
		
		System.out.println("LMGCartBolt prepare End");
	}

	@Override
	public void execute(Tuple tuple) {
		
		System.out.println("LMGCartBolt cassandra save start ");

		UserSessionProtos.UserRequest userRequest = (UserSessionProtos.UserRequest) tuple.getValueByField("content");

		System.out.println("LMGCartBolt UserSessionProtos.UserRequest  " + userRequest);

		try {
			saveProtobufMessage(userRequest);
			System.out.println(" Insertion for protobuf message done ");
			collector.ack(tuple);

		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub

	}
	
	private void saveProtobufMessage(UserSessionProtos.UserRequest userRequest) throws InvalidProtocolBufferException {

		CartEntity cartEntity = null;
		Date now = Calendar.getInstance().getTime();

		List<CartEntity> cartsEntityList = cartRepository.findCartEntityByCartKeySessionIdAndCartKeyCartId(userRequest.getSessionId(), userRequest.getCartId());
		
		List<UserClickURL> clickURLs = getUserClickURLList(userRequest.getClickurlList(), now);
		if (!CollectionUtils.isEmpty(cartsEntityList)) {
			cartEntity = cartsEntityList.get(0);
			cartEntity.getClickurl().addAll(clickURLs);
			cartEntity.setLastModified(now);
		} else {
			cartEntity = new CartEntity();

			CartKey orderKey = new CartKey();
			orderKey.setId(UUIDs.timeBased());
			orderKey.setSessionId(userRequest.getSessionId());
			orderKey.setCartId(userRequest.getCartId());
			cartEntity.setCartKey(orderKey);
			
			cartEntity.setClickurl(clickURLs);
			cartEntity.setLastModified(now);
			cartEntity.setCreatedOn(now);
		}
		if (userRequest.hasName()) {
			cartEntity.setName(userRequest.getName());
		}
		if (userRequest.hasMobileNumber()) {
			cartEntity.setMobileNumber(userRequest.getMobileNumber());
		}
		if (userRequest.hasUiExperienceLabel()) {
			cartEntity.setUiExperienceLabel(userRequest.getUiExperienceLabel());
		}
		cartRepository.save(cartEntity);

	}

}
