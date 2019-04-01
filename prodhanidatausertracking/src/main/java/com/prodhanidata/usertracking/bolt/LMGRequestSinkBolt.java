package com.prodhanidata.usertracking.bolt;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.google.protobuf.InvalidProtocolBufferException;
import com.prodhanidata.protobuf.UserSessionProtos;
import com.prodhanidata.usertracking.topology.LMGUserRequestTopology;

@Component("lmgRequestSinkBolt")
@Profile("userRequestTopology")
@DependsOn({ "userRequestTopology" })
public class LMGRequestSinkBolt extends AbstractLMGUserTrackingBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String STREAM_TYPE = "userRequestStream";

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;		
	}

	@Override
	public void execute(Tuple tuple) {

		byte[] bytesArray = tuple.getBinaryByField("bytes");
		System.out.println(" UserSessionProtos.UserSession  bytesArray " + bytesArray);
		try {
			UserSessionProtos.UserRequest sessionProto = UserSessionProtos.UserRequest.parseFrom(bytesArray);
			System.out.println(" UserSessionProtos.UserSession " + sessionProto.toString());
			
			if (isOrderRequest(sessionProto)) {
				collector.emit(LMGUserRequestTopology.ORDER_BOLT_STREAM,
						new Values(LMGUserRequestTopology.ORDER_BOLT_STREAM, sessionProto));
			}if (isCartRequest(sessionProto)) {
				collector.emit(LMGUserRequestTopology.CART_BOLT_STREAM,
						new Values(LMGUserRequestTopology.CART_BOLT_STREAM, sessionProto));
			} else {
				collector.emit(LMGUserRequestTopology.USER_REQUEST_BOLT_STREAM,
						new Values(LMGUserRequestTopology.USER_REQUEST_BOLT_STREAM, sessionProto));
			}
			collector.ack(tuple);

		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void cleanup() {
		System.out.println("LMGRequestSinkBolt test  cleanup");
	}

	@Override
	public void declareOutputFields(org.apache.storm.topology.OutputFieldsDeclarer declarer) {
		System.out.println("LMGRequestSinkBolt test declareOutputFields");
		declarer.declareStream(LMGUserRequestTopology.CART_BOLT_STREAM, new Fields(STREAM_TYPE, STREAM_CONTENT));
		declarer.declareStream(LMGUserRequestTopology.ORDER_BOLT_STREAM, new Fields(STREAM_TYPE, STREAM_CONTENT));
		declarer.declareStream(LMGUserRequestTopology.USER_REQUEST_BOLT_STREAM,new Fields(STREAM_TYPE, STREAM_CONTENT));
	}
}
