package com.tang.messgae.customer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by Tang on 2019/6/19.
 */
@Service
public class KafkaCustomer {

    @Value("${kafka.topic.dynamic.data.queue}")
    private String topic;

//    //消费kafka集群1
//    @KafkaListener(topics = "test",containerFactory = "kafkaListenerContainerFactory")
//    public void onRecive2(String content){
//        System.out.println(content);
//    }

    //消费kafka集群2
    @KafkaListener(topics = "iprd-quque-customer-datasync",containerFactory = "kafkaListenerContainerFactoryForDynamicData")
    public void onRecive(String content){
        System.out.println(content);
    }
}
