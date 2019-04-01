package com.prodhanidata.usertracking.topology;

import javax.annotation.Resource;

import org.apache.storm.topology.TopologyBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.prodhanidata.cassandra.repository.UserSessionRepository;
import com.prodhanidata.storm.bolt.AbstractLMGBaseRichBolt;
import com.prodhanidata.storm.bootstrap.LMGSpringContextUtil;
import com.prodhanidata.storm.spout.AbstractSpoutBuilder;
import com.prodhanidata.storm.topology.AbstractLMGTopology;

@Component
@Profile("userRequestTopology")
public class LMGUserRequestTopology extends AbstractLMGTopology {

	@Resource(name="lmgUserRequestSpoutBuilder")
	private AbstractSpoutBuilder spoutBuilder;
	
	@Resource(name="lmgRequestSinkBolt")
	AbstractLMGBaseRichBolt sinkBolt;
	
	@Resource(name="lmgCartBolt")
	AbstractLMGBaseRichBolt cartBolt;
	
	@Resource(name="lmgOrderBolt")
	AbstractLMGBaseRichBolt orderBolt;
	
	@Resource(name="lmgUserRequestBolt")
	AbstractLMGBaseRichBolt userRequestBolt;
	
	@Resource(name="userRequestTopology")
	TopologyBuilder builder;
	
	@Resource
	LMGSpringContextUtil contextUtil;
	
	@Resource
	private UserSessionRepository sessionRepository;
	
	public static final String USER_REQUEST_KAFKA_TOPIC ="userJSONRequestWeb";
	public static final String USER_REQUEST_SPOUT_ID ="user-request-spout-id";
	public static final int NUMBER_OF_THREAD_PER_BOLT =1;
	public static final int NUMBER_OF_TASK_PER_BOLT =1;
	public static final String SINK_BOLT_ID ="sink-bolt-id";
	public static final String CART_BOLT_ID ="cart-bolt-id";
	public static final String ORDER_BOLT_ID ="order-bolt-id";
	public static final String USER_REQUEST_BOLT_ID ="user-request-bolt-id";
	public static final String CART_BOLT_STREAM ="cart-stream";
	public static final String ORDER_BOLT_STREAM ="order-stream";
	public static final String USER_REQUEST_BOLT_STREAM ="user-request-stream";
	
	
	@Override
	protected TopologyBuilder buildTopologyBuilder() {
		builder.setSpout(USER_REQUEST_SPOUT_ID, spoutBuilder.buildKafkaSpout(USER_REQUEST_KAFKA_TOPIC), 1);
		builder.setBolt(SINK_BOLT_ID,sinkBolt , NUMBER_OF_THREAD_PER_BOLT).setNumTasks(NUMBER_OF_TASK_PER_BOLT).shuffleGrouping(USER_REQUEST_SPOUT_ID);
		builder.setBolt(CART_BOLT_ID,cartBolt , NUMBER_OF_THREAD_PER_BOLT).setNumTasks(NUMBER_OF_TASK_PER_BOLT).shuffleGrouping(SINK_BOLT_ID,CART_BOLT_STREAM);
//		builder.setBolt(CART_BOLT_ID,cartBolt , NUMBER_OF_THREAD_PER_BOLT).setNumTasks(NUMBER_OF_TASK_PER_BOLT).fieldsGrouping(SINK_BOLT_ID,CART_BOLT_STREAM,new Fields("typeUTL"));
		builder.setBolt(ORDER_BOLT_ID,orderBolt , NUMBER_OF_THREAD_PER_BOLT).setNumTasks(NUMBER_OF_TASK_PER_BOLT).shuffleGrouping(SINK_BOLT_ID,ORDER_BOLT_STREAM);
		builder.setBolt(USER_REQUEST_BOLT_ID,userRequestBolt , NUMBER_OF_THREAD_PER_BOLT).setNumTasks(NUMBER_OF_TASK_PER_BOLT).shuffleGrouping(SINK_BOLT_ID,USER_REQUEST_BOLT_STREAM);
		return builder;
	}

}
