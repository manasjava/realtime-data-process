package com.prodhanidata.usertracking.topology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.storm.Config;

import com.prodhanidata.storm.bootstrap.BootstrapApplication;
import com.prodhanidata.storm.topology.AbstractLMGTopology;
import com.prodhanidata.storm.topology.RemoteTopologySubmitter;
import com.prodhanidata.storm.topology.TopologySubmitter;

public class TopologyLoader {

	public static void main(String[] args) {
		
		
//		Properties configs = new Properties();
//		try {
//			configs.load(TopologySubmitter.class.getClassLoader().getResourceAsStream("default_configs.properties"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		Config conf = new Config();
//		conf.put("storm.zookeeper.hosts",configs.getProperty("kafka.zookeeper"));
//		//Defines how many worker processes have to be created for the topology in the cluster.
//		conf.setNumWorkers(1);
//		
//		TopologySubmitter submitter = new RemoteTopologySubmitter(conf);
//		
//		AbstractLMGTopology topology = new LMGUserRequestTopology();
//		List<AbstractLMGTopology> topoList = new ArrayList<>();
//		topoList.add(topology);
//		
//		submitter.setTopologyList(topoList);
//		submitter.loadTopology();
		
		
//		BootstrapApplication.loadBootstrap();
//		// creating UUID      
//	      UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");     
//
//	      // checking the value of random UUID
//	      System.out.println("Random UUID value: "+uid.randomUUID()); 
//	      
//	       uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"); 
//	       System.out.println("Random UUID value: "+uid.randomUUID()); 
		
		
	}
}
