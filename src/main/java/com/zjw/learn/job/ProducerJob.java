package com.zjw.learn.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author Zhang Jinwei
 * @date 2018年04月21日
 */
@Slf4j
@Component
public class ProducerJob {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedRate = 600000)
    public void testJob() {
        for (int i = 0; i < 10; i++) {
            ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send("learn", i + "");
            listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

                @Override
                public void onFailure(Throwable throwable) {
                    log.error("发送失败{}", throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, String> stringStringSendResult) {
                    log.warn("发送成功{}", stringStringSendResult.getProducerRecord().value());
                }

            });
        }

    }
}
