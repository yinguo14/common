package com.military.cloud.common.rocketmq.config;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author guo.yin
 * @date 2024/03/13 16:49
 **/
@Component
public class RocketMqProducer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private void sedMessage(String topic, String message) {
        rocketMQTemplate.convertAndSend(topic, message);
    }
}
