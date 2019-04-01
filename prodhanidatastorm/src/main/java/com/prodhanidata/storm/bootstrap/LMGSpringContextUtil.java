package com.prodhanidata.storm.bootstrap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class LMGSpringContextUtil implements ApplicationContextAware{

	private  ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		
		applicationContext = appContext;
	}
	
	public  ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
