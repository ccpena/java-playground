package org.kkpa.playgroundsb.kafka_basics.consumersgroup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecondTopicConsumer2 {

  @KafkaListener(topics = "second_topic", groupId = "secondT-consumer")
  public void processMessage(String content) {
    log.info("Consumer {} - {} ", "2", content);
  }
}
