package org.kkpa.playgroundsb.kafka_basics.consumersgroup;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public abstract class SecondTopicConsumerGroup {

  protected static final String TOPIC_NAME = "second_topic";
  protected static final String GROUP_ID = "groupST";

  abstract String getConsumerID();

  public void process(ConsumerRecord<String, String> record) {
    String content = record.value();
    int partition = record.partition();
    long offset = record.offset();
    String consumerID = getConsumerID();

    String key = record.key();
    log.info("Consumer {} [Key:{}_Partition:{}_Offset:{}] - Value:{} ", consumerID, key, partition, offset, content);
  }


}
