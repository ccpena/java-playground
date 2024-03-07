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

@Component
@Slf4j
public class ProducerWithKeys {

  public static final String SECOND_TOPIC = "second_topic";

  private final KafkaTemplate<String, String> kafkaTemplate;

  public ProducerWithKeys(KafkaTemplate<String, String> kafkaTemplate) {
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
    IntStream.rangeClosed(0, 3).forEach(i -> {
      IntStream.rangeClosed(0, 3)
              .forEach(n -> {
                String key = "id_" + n;
                String value = String.format("i_%d:n_%d", i, n);
                sendMessageWithKey(key, value);
              });
    });
  }

  private void sendMessageWithKey(String key, String value) {
    ProducerRecord<String, String> recordKey = new ProducerRecord<>(SECOND_TOPIC, key, value);
    CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(recordKey);
    future.thenAccept(sendResult -> {
      RecordMetadata metadata = sendResult.getRecordMetadata();
      log.info("Message-KEY! [Key:{}-Value:{}]  Consumer[Partition:{} Offset:{} Timestamp:{}]",
              sendResult.getProducerRecord().key(),
              sendResult.getProducerRecord().value(),
              metadata.partition(), metadata.offset(), metadata.timestamp());
    }).exceptionally(throwable -> {
      log.error("Failed to Send message: {}", throwable.getMessage());
      return null;
    })
    ;
    sleep(1000);
  }
}
