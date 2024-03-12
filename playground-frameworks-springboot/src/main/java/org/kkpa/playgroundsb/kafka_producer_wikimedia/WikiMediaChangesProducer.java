package org.kkpa.playgroundsb.kafka_producer_wikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.MessageEvent;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class WikiMediaChangesProducer {
  public static final String WIKIMEDIA_TOPIC = "wikimedia.recentchange";
  private final EventSource eventSource;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public WikiMediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
    String wikimediaURL = "https://stream.wikimedia.org/v2/stream/recentchange";
    EventHandler eventHandler = new WikimediaEventHandler();
    eventSource = new EventSource.Builder(eventHandler, URI.create(wikimediaURL))
            .build();
  }

  private static void sleep(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private Map<String, Object> producerConfigs(String bootstrapServers,
                                              Class<StringSerializer> keySerializer,
                                              Class<StringSerializer> valueSerializer) {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
    props.put(ProducerConfig.LINGER_MS_CONFIG, 50); // Set linger time to 50 milliseconds
    return props;
  }

  @PostConstruct
  public void init() {
    eventSource.start();
    log.info("Wikimedia Changes Producer");
    closeEventSource();
  }

  @Async
  public void closeEventSource() {
    sleep(5000);
    eventSource.close();
  }

  private class WikimediaEventHandler implements EventHandler {
    @Override
    public void onOpen() {
      // Handle the open event if needed
    }

    @Override public void onClosed() {
      kafkaTemplate.destroy();
    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) {
      log.info("Sending Wikimedia LastEventId: {} ", messageEvent.getLastEventId());
      ProducerRecord<String, String> newProducerRecords = new ProducerRecord<>(WIKIMEDIA_TOPIC, messageEvent.getData());
      kafkaTemplate.send(newProducerRecords);
    }

    @Override
    public void onComment(String comment) {
      // Handle comments if needed
    }

    @Override public void onError(Throwable throwable) {
      log.error("Error in Stream Reading {}", throwable.getMessage());
    }


  }
}
