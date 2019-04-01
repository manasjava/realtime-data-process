package com.prodhanidata.storm.bootstrap;

import org.apache.storm.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@Profile("live")
@PropertySource(value = { "classpath:storm_configs.properties" })
public class CustomeConfiguartion {
	
	@Autowired
	private Environment environment;
	
//	@Bean(name="PropertiesFactoryBean")
//	public PropertiesFactoryBean getPropertiesFactoryBean() {
//	
//		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//		ClassPathResource[] resources = new ClassPathResource[ ]{ new ClassPathResource( "application.properties" ) };
//		propertiesFactoryBean.setLocations(resources);
//		return propertiesFactoryBean;
//	}
	
	@Bean
	public Config getStromConfig() {
		Config config = new Config();
		config.put("storm.zookeeper.hosts",environment.getProperty("kafka.zookeeper"));
		return config;
	}
	

}