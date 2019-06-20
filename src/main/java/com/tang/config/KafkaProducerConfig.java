package com.tang.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tang on 2019/6/19.
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.consumer.servers}")
    private String serverForTest;

    @Value("${kafka.consumer.serversForDynamicData}")
    private String serverForTest2;



    public Map<String, Object> producerConfigs(String servers) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    public ProducerFactory<String, String> producerFactory(String servers) {
        return new DefaultKafkaProducerFactory<>(producerConfigs(servers));
    }

    @Bean
    public KafkaProducer<String, String> kafkaProducerForTest() {
        return new KafkaProducer<>(producerConfigs(serverForTest));
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplateForForTest() {
        return new KafkaTemplate<>(producerFactory(serverForTest2));
    }


}
