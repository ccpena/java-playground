package org.kkpa.playgroundsb.kafka_basics.producers;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Component
public class ProducerNoKeysStickyP {

  public static final String SECOND_TOPIC = "second_topic";
  private final KafkaTemplate<String, String> kafkaTemplate;

  public ProducerNoKeysStickyP(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  private static void sleep(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @PostConstruct
  public void init() {
    sendMessageWithNO_Key(-1, SECOND_TOPIC);
    sleep(10000);
    produceWithStickyPartition();
  }

  /**
   * If you sent messages quickly, the producer is smart enough to be now batching
   * these messages into one batch to just make it more efficient. Messages
   * will be sent to the same partition "sticky"
   */
  private void produceWithStickyPartition() {
    String topic = "second_topic";
    IntStream.rangeClosed(0, 2)
            .forEach(n -> {
              sendMessageWithNO_Key(n, SECOND_TOPIC);
            });
  }

  /**
   * partitioner.class = null. Default partitioner [UniformPartition] without KEY.
   *
   * @param n
   * @param topic
   */
  private void sendMessageWithNO_Key(int n, String topic) {
    ProducerRecord<String, String> recordNoKey = new ProducerRecord<>(topic, "Sticky Value:" + n);
    CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(recordNoKey);
    future.thenAccept(sendResult -> {
      RecordMetadata metadata = sendResult.getRecordMetadata();
      log.info("Message sent! Partition:{}  Offset:{} Timestamp:{}", metadata.partition(), metadata.offset(), metadata.timestamp());
    }).exceptionally(throwable -> {
      log.error("Failed to Send message: {}", throwable.getMessage());
      return null;
    })
    ;
    sleep(1000);
  }
}
