package com.prodhanidata.usertracking.configuartion;

import org.apache.storm.topology.TopologyBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.prodhanidata.protobuf.App;
import com.prodhanidata.storm.topology.PDSTopologyBuilder;

@Configuration
@ComponentScan(basePackageClasses={App.class})
public class UserRequestConfiguartion {
	
	@Bean(name="userRequestTopologyBuilder")
	public TopologyBuilder getTopologyBuilder() {
		TopologyBuilder builder = new PDSTopologyBuilder("UserRequestTopology");
		return builder;
	}

}
