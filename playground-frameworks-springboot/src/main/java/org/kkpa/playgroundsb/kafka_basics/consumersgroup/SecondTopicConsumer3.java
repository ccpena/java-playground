package org.kkpa.playgroundsb.kafka_basics.consumersgroup;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecondTopicConsumer3 extends SecondTopicConsumerGroup {

  @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_ID)
  public void processMessage(ConsumerRecord<String, String> record) {
    process(record);
  }

  @Override String getConsumerID() {
    return "3";
  }
}
