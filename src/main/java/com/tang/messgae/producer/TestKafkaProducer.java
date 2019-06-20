package com.tang.messgae.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Tang on 2019/6/19.
 */
@Service
public class TestKafkaProducer {

    final private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${kafka.topic.dynamic.data.queue}")
    private String topic;


    @Resource
    private Producer<String, String> kafkaProducerForTest;


    @Resource
    private KafkaTemplate<String, String> kafkaTemplateForForTest;

    public void sendQueueMessageKafkaProducerForTest(String message) {

        try {
            long beginTime = System.currentTimeMillis();
            logger.info("sendMessage begin:{}", message);
            kafkaProducerForTest.send(new ProducerRecord<>(topic, message), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e != null) {
                        logger.error("sendMessage error:", e);
                    } else {
                        //记录Partition和offset
                        //Partition中的每条Message由offset来表示它在这个partition中的偏移量，唯一确定了partition中的一条Message
                        logger.info("record partition:" + recordMetadata.partition() + ",offset:" + recordMetadata.offset());
                    }
                }
            });
            logger.info("sendMessage time:{}", System.currentTimeMillis() - beginTime);
        } catch (Exception e) {
            logger.error("sendMessage error:{}", e);
        }
    }

    public void sendQueueMessageKafkaTemplateForForTest(String message) {

        try {
            long beginTime = System.currentTimeMillis();
            logger.info("sendMessage begin:{}", message);
            kafkaTemplateForForTest.send(topic, message);
            logger.info("sendMessage time:{}", System.currentTimeMillis() - beginTime);
        } catch (Exception e) {
            logger.error("sendMessage error:{}", e);
        }
    }





}
