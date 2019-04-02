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
import com.prodhanidata.cassandra.entity.OrderKey;
import com.prodhanidata.cassandra.entity.OrdersEntity;
import com.prodhanidata.cassandra.entity.UserClickURL;
import com.prodhanidata.cassandra.repository.OrdersRepository;
import com.prodhanidata.protobuf.UserSessionProtos;;

@Component("userOrderBolt")
@Profile("userRequestTopology")
@DependsOn({ "userRequestTopologyBuilder" })
public class UserOrderBolt extends AbstractUserTrackingBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	OrdersRepository orderRepository;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		
		System.out.println("OrderBolt prepare Start");
		
		this.collector = collector;
		InitializeApplicationContext();
		orderRepository = applicationContext.getBean(OrdersRepository.class);
		
		System.out.println("OrderBolt prepare End");
	}

	@Override
	public void execute(Tuple tuple) {
		
		System.out.println("OrderBolt cassandra save start ");

		UserSessionProtos.UserRequest userRequest = (UserSessionProtos.UserRequest) tuple.getValueByField("content");

		System.out.println("OrderBolt UserSessionProtos.UserSession  " + userRequest);

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

		OrdersEntity ordersEntity = null;
		Date now = Calendar.getInstance().getTime();

		List<OrdersEntity> ordersEntityList = orderRepository.findOrdersEntityByOrderKeySessionIdAndOrderKeyOrderId(userRequest.getSessionId(), userRequest.getOrderId());
		
		List<UserClickURL> clickURLs = getUserClickURLList(userRequest.getClickurlList(), now);
		if (!CollectionUtils.isEmpty(ordersEntityList)) {
			ordersEntity = ordersEntityList.get(0);
			ordersEntity.getClickurl().addAll(clickURLs);
			ordersEntity.setLastModified(now);
		} else {
			ordersEntity = new OrdersEntity();

			OrderKey orderKey = new OrderKey();
			orderKey.setId(UUIDs.timeBased());
			orderKey.setSessionId(userRequest.getSessionId());
			orderKey.setOrderId(userRequest.getOrderId());
			ordersEntity.setOrderKey(orderKey);
			
			ordersEntity.setClickurl(clickURLs);
			ordersEntity.setLastModified(now);
			ordersEntity.setCreatedOn(now);
		}
		if (userRequest.hasName()) {
			ordersEntity.setName(userRequest.getName());
		}
		if (userRequest.hasMobileNumber()) {
			ordersEntity.setMobileNumber(userRequest.getMobileNumber());
		}
		if (userRequest.hasUiExperienceLabel()) {
			ordersEntity.setUiExperienceLabel(userRequest.getUiExperienceLabel());
		}
		orderRepository.save(ordersEntity);

	}

}
