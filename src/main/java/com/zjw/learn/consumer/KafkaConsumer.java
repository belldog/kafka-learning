package com.zjw.learn.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Zhang Jinwei
 * @date 2018年04月21日
 */
@Slf4j
@Component
public class KafkaConsumer {
    /**
     * 消费消息
     * @param record
     */
    @KafkaListener(topics = "learn",group = "default-group")
    public void handleTrade(ConsumerRecord<String,String> record){
        log.info("topic={},key={},value={}",record.topic(),record.key(),record.value());
        log.info("消息处理成功");
    }
}
