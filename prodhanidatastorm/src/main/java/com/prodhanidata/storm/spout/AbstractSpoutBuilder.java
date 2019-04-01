package com.prodhanidata.storm.spout;

import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.spout.RawScheme;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import kafka.api.OffsetRequest;

public abstract class AbstractSpoutBuilder {
	
	public static final String KAFKA_ZOOKEEPER  = "kafka.zookeeper";
	public static final String KAFKA_ZKROOT = "kafka.zkRoot";
	public static final String KAFKA_CONSUMERGROUP = "kafka.consumer.group";
	
	@Autowired
	private Environment environment;
	
	public KafkaSpout buildKafkaSpout(String kafkaTopic) { 
		System.out.println("SpoutBuilder buildKafkaSpout");
		BrokerHosts hosts = new ZkHosts(environment.getProperty(KAFKA_ZOOKEEPER));
		String zkRoot = environment.getProperty(KAFKA_ZKROOT);
		String groupId = environment.getProperty(KAFKA_CONSUMERGROUP);
		SpoutConfig spoutConfig = new SpoutConfig(hosts, kafkaTopic, zkRoot, groupId);
		spoutConfig.startOffsetTime = OffsetRequest.LatestTime();
		spoutConfig.scheme = new SchemeAsMultiScheme(new RawScheme());
		KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
		return kafkaSpout;
	}
	
	


}
