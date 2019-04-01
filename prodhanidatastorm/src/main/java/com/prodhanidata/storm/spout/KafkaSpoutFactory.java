package com.prodhanidata.storm.spout;

public class KafkaSpoutFactory {
	
	
//	private final String server;
//
//
//	private final String groupId;
//
//	private final String keyDeserializer;
//
//	private final String valueDeserializer;
//	
//	private final boolean enableAutoCommit;
//	
//	public KafkaSpoutFactory(String server, String groupId,
//			String keyDeserializer, String valueDeserializer,
//			boolean enableAutoCommit) {
//		this.server = server;
//		this.groupId = groupId;
//		this.keyDeserializer = keyDeserializer;
//		this.valueDeserializer = valueDeserializer;
//		this.enableAutoCommit = enableAutoCommit;
//	}
//
//	public KafkaSpout<String, String> create(String topic, Class klass) {
//		// KafkaSpout API seems to be undergoing quite a few changes.
//		// Hopefully we can take advantage of the improvements in future versions soon!
//		KafkaSpoutConfig<String, String> kafkaConfig = builder(getBootstrapServers(), topic, klass).build();
//		return new KafkaSpout<String, String>(kafkaConfig);
//	}
//
//	public String getBootstrapServers() {
//		return this.server;
//	}
//
//	public KafkaSpoutConfig.Builder<String, String> builder(String bootstrapServers, String topic, Class klass) {
//		Map<String, Object> props = new HashMap<>();
//		if (bootstrapServers == null || bootstrapServers.isEmpty()) {
//			throw new IllegalArgumentException("bootstrap servers cannot be null");
//		}
//		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//		props.put(KafkaSpoutConfig.Consumer.GROUP_ID, groupId);
//		props.put(KafkaSpoutConfig.Consumer.KEY_DESERIALIZER,
//				keyDeserializer);
//		props.put(KafkaSpoutConfig.Consumer.VALUE_DESERIALIZER,
//				valueDeserializer);
//		props.put(KafkaSpoutConfig.Consumer.ENABLE_AUTO_COMMIT, String.valueOf(enableAutoCommit));
//
//		KafkaSpoutStreams streams = new KafkaSpoutStreamsNamedTopics.Builder(
//				new KafkaSpoutStream(getFields(klass), topic)).build();
//
//		KafkaSpoutTuplesBuilder<String, String> tuplesBuilder = new KafkaSpoutTuplesBuilderNamedTopics.Builder<String, String>(
//				new TuplesBuilder(topic, klass)).build();
//
//		return new KafkaSpoutConfig.Builder<>(props, streams, tuplesBuilder);
//	}
//	
//	
//	private Fields getFields(Class klass) {
//		if (klass.isAnnotationPresent(OutputFields.class)) {
//			OutputFields outputFields = (OutputFields) klass.getAnnotation(OutputFields.class);
//			return new Fields(outputFields.value());
//		} else {
//			// TODO improve exception type and message
//			throw new RuntimeException("can't get output fields for class " + klass);
//		}
//	}
//
//	private static class TuplesBuilder extends KafkaSpoutTupleBuilder<String, String> {
//
//		private final Class klass;
//		private final ObjectMapper mapper;
//
//		public TuplesBuilder(String topic, Class klass) {
//			super(topic);
//			this.klass = klass;
//		    this.mapper = new ObjectMapper();
//		}
//
//		@Override
//		public List<Object> buildTuple(ConsumerRecord<String, String> consumerRecord) {	
//			// TODO - how to handle errors gracefully here?
//			try {
//				tupleProducer tupleProducer = (TupleProducer)mapper.readValue(consumerRecord.value(), klass);
//				return tupleProducer.toTuple();
//			} catch (JsonParseException e) {
//				// bad JSON - need to handle this!
//				logger.error("can't build tuple for consumer record " + consumerRecord, e);
//				return new Values("not well-formed JSON!");
//			} catch (UnrecognizedPropertyException e) {
//				// bad JSON - need to handle this!
//				logger.error("can't build tuple for consumer record " + consumerRecord, e);
//				return new Values("unexpected fields in JSON!");
//			} catch (JsonMappingException e) {
//				// bad JSON - need to handle this!
//				logger.error("can't build tuple for consumer record " + consumerRecord, e);
//				return new Values("unexpected fields in JSON!");
//			}  catch (IOException e) {
//				logger.error("can't build tuple for consumer record " + consumerRecord, e);
//				return null;
//			}
//		}
//
//	}

}
