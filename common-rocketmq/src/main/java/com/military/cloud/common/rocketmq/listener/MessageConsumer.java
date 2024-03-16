package com.military.cloud.common.rocketmq.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author guo.yin
 * @date 2024/03/14 10:00
 **/
@Service
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
public class MessageConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {

    }
}
