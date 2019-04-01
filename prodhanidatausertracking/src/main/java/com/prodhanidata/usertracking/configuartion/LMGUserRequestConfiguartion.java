package com.prodhanidata.usertracking.configuartion;

import org.apache.storm.topology.TopologyBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prodhanidata.storm.topology.LMGTopologyBuilder;

@Configuration
public class LMGUserRequestConfiguartion {
	
	@Bean(name="userRequestTopology")
	public TopologyBuilder getLMGTopologyBuilder() {
		TopologyBuilder builder = new LMGTopologyBuilder("UserRequestTopology");
		return builder;
	}

}
