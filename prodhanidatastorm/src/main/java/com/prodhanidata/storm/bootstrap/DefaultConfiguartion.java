package com.prodhanidata.storm.bootstrap;

import org.apache.storm.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@Profile("default")
@PropertySource(value = { "classpath:default_configs.properties" })
public class DefaultConfiguartion {

	@Autowired
	private Environment environment;
	
	@Bean
	public Config getStromConfig() {
		Config config = new Config();
		config.put("storm.zookeeper.hosts",environment.getProperty("kafka.zookeeper"));
		return config;
	}
	
}
