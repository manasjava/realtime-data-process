package com.prodhanidata.cassandra;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.prodhanidata.cassandra.entity.OrdersEntity;
import com.prodhanidata.cassandra.entity.SessionKey;
import com.prodhanidata.cassandra.entity.UserSession;
import com.prodhanidata.cassandra.repository.OrdersRepository;
import com.prodhanidata.cassandra.repository.UserSessionRepository;

//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.serializer.JsonSerializer;

//@SpringBootApplication
public class ProdhanidatacassandraApplication {

	public static void main(String[] args) {
		
		String str = "{name=}";
		
		
		
		
	//	SpringApplication.run(ProdhanidatacassandraApplication.class, args);
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(new String[] {"com.prodhanidata.cassandra","com.prodhanidata.cassandra.*"});
		UserSessionRepository repository = applicationContext.getBean(UserSessionRepository.class);
		
		
		SessionKey key = new SessionKey();
        key.setSessionId("1E760E1B64199D8903A93848B7A7BFFF");
		List<UserSession> userSessions = repository.findUserSessionBySessionKeySessionId(key.getSessionId());
		System.out.println("userSessions "+userSessions);
				
		OrdersRepository repository1 = applicationContext.getBean(OrdersRepository.class);
		List<OrdersEntity> entities= repository1.findOrdersEntityByOrderKeySessionIdAndOrderKeyOrderId("1E760E1B64199D8903A93848B7A7BFFF", "sdsad");
		System.out.println("OrdersEntity "+entities);
//		String[] beanNames = applicationContext.getBeanDefinitionNames();
//		Arrays.sort(beanNames);
//		for (String beanName : beanNames) {
//			System.out.println(beanName);
//		}
//		
//		UserSessionRepository repository = applicationContext.getBean(UserSessionRepository.class);
//		UserSession user = new UserSession();
//		SessionKey key = new SessionKey("CA1A54546EB0E5DFD04BEE18B9D2C5A9", "abci@gmail.com");
//		user.setKey(key);
//		
//		
//		UserClickURL clickURL = new UserClickURL();
//		clickURL.setUrl("https://maxae.local:9002/ae/en/");
//		List<UserClickURL> clickURLList = new  ArrayList<>();
//		clickURLList.add(clickURL);
//		user.setClickurl(clickURLList);
//		
//		
//		repository.save(user);
//		getKafkaTemplate().send("userRequest", "produced recod in topic:ssdsdf");

	}
	
	
	
	
//	private static Map<String, Object> producerConfigs() {
//		final Map<String, Object> props = new HashMap<>();
//	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//	    return props;
//	}
//
//	private static KafkaTemplate<String, String> getKafkaTemplate()
//	{
//		return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfigs()));
//	}

	
}
