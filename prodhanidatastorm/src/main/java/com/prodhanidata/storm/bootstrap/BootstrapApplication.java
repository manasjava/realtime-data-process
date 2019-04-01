package com.prodhanidata.storm.bootstrap;

import java.util.Arrays;

import org.apache.storm.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.prodhanidata.storm.topology.TopologySubmitter;


/**
 * this bootstrap class start the spring boot and also load and submit the
 * topology to remote/local storm.
 * 
 * @author rezaul.prodhani
 *
 */
public class BootstrapApplication {

	/**
	 * Application context
	 */
	private static ApplicationContext applicationContext;

	/**
	 * this is starting of loading Spring Boot.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("########### Spring Annotation loading Start Here #############");

		loadBootstrap();

		System.out.println("########### Spring Annotation loading End Here #############");
		
		TopologySubmitter topoSubmitter = applicationContext.getBean(TopologySubmitter.class);
		topoSubmitter.loadTopology();
	}

	/**
	 * listing the beans loaded by spring boot.
	 * 
	 * @param ctx
	 * @return
	 */
	public static void displayBeanName(ApplicationContext ctx) {
		System.out.println("Let's inspect the beans provided by Spring Annotation loading:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}

	public static void loadBootstrap() {
		applicationContext = new AnnotationConfigApplicationContext(new String[] {"com.prodhanidata.*"});
		displayBeanName(applicationContext);
		Config config = applicationContext.getBean(Config.class);
		System.out.println("storm.zookeeper.hosts "+config.get("storm.zookeeper.hosts"));
		
	}
	public static void localSubmit() {

		System.out.println("########### Spring Annotation loading Start Here #############");

		loadBootstrap();

		System.out.println("########### Spring Annotation loading End Here #############");
		
		TopologySubmitter topoSubmitter = applicationContext.getBean(TopologySubmitter.class);
		topoSubmitter.loadTopology();
	}
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
