package org.kkpa.playgroundsb.kafka_consumer_opensearch;

import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.opensearch.action.bulk.BulkRequest;
import org.opensearch.action.bulk.BulkResponse;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.xcontent.XContentType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class WikimediaConsumer {
  protected static final String TOPIC_NAME = "wikimedia.recentchange";
  protected static final String GROUP_ID = "consumer-opensearch-group";
  private final OpenSearchService openSearchService;

  public WikimediaConsumer(OpenSearchService openSearchService) {
    this.openSearchService = openSearchService;
  }

  private static String extractId(String json) {
    // gson library to extract meta->id
    return JsonParser.parseString(json)
            .getAsJsonObject()
            .get("meta")
            .getAsJsonObject()
            .get("id")
            .getAsString();
  }

  @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_ID, batch = "true")
  public void processMessage(@Payload List<ConsumerRecord<String, String>> records) {
    log.info("Wikimedia Consumer Records {} ", records.size());
    BulkRequest bulkRequest = new BulkRequest();
    for (ConsumerRecord<String, String> record : records) {
      process(record);
      String id = extractId(record.value());
      IndexRequest idxReq = new IndexRequest(openSearchService.getIndexName());
      idxReq.source(record.value(), XContentType.JSON)
              .id(id);

      bulkRequest.add(idxReq);
    }
    sentToOpenSearch(bulkRequest);
  }

  @Async
  public void sentToOpenSearch(BulkRequest bulkRequest) {
    try {
      if (bulkRequest.numberOfActions() > 0) {
        RestHighLevelClient openSearchClient = openSearchService.createOpenSearchClient(false);
        BulkResponse bulkResponse = openSearchClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        log.info("Inserted to Open Search" + bulkResponse.getItems().length + " record(s).");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void process(ConsumerRecord<String, String> record) {
    long timestamp = record.timestamp();
    int partition = record.partition();
    long offset = record.offset();
    String consumerID = getConsumerID();


    String key = record.key();
    log.info("WikiMediaConsumer {} [Key:{}_Partition:{}_Offset:{}] - TS:{} ", consumerID, key, partition, offset, timestamp);
  }

  String getConsumerID() {
    return "1";
  }
}
