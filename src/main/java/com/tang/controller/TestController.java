package com.tang.controller;

import com.tang.messgae.producer.TestKafkaProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Tang on 2019/6/20.
 */
@RestController
public class TestController {

    @Resource
    TestKafkaProducer testkafkaProducer;

    @RequestMapping("testSend")
    public Integer sendMessage(){
        testkafkaProducer.sendQueueMessageKafkaProducerForTest("producer");
        testkafkaProducer.sendQueueMessageKafkaTemplateForForTest("template");
        return 1;
    }
}
