package org.kkpa.playgroundsb.kafka_consumer_opensearch;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.indices.GetIndexRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

@Service
@Slf4j
public class OpenSearchService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public OpenSearchService(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public RestHighLevelClient createOpenSearchClient(boolean useBonsai) {
    String connectionURL = "http://localhost:9200";
    String connBonsai = "https://zfh2059j3g:el8ltgwvir@kkpa-cluster-7214429236.us-east-1.bonsaisearch.net:443";
    if (useBonsai) {
      connectionURL = connBonsai;
    }


    // we build a URI from the connection string
    RestHighLevelClient restHighLevelClient;
    URI connUri = URI.create(connectionURL);
    // extract login information if it exists
    String userInfo = connUri.getUserInfo();

    if (userInfo == null) {
      // REST client without security
      restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost(connUri.getHost(), connUri.getPort(), "http")));

    } else {
      // REST client with security
      String[] auth = userInfo.split(":");

      CredentialsProvider cp = new BasicCredentialsProvider();
      cp.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(auth[0], auth[1]));

      restHighLevelClient = new RestHighLevelClient(
              RestClient.builder(new HttpHost(connUri.getHost(), connUri.getPort(), connUri.getScheme()))
                      .setHttpClientConfigCallback(
                              httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(cp)
                                      .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())));


    }

    return restHighLevelClient;
  }

  public String getIndexName() {
    return "wikimedia";
  }

  @PostConstruct
  public void init() throws IOException {
    log.info("Open Search Service Init...");
    String indexName = getIndexName();
    // Create an OpenSearch Client
    RestHighLevelClient openSearchClient = createOpenSearchClient(false);

    // we need to create the index on OpenSearch if it doesn't exist already
    try (openSearchClient) {
      boolean indexExists = openSearchClient.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);

      if (!indexExists) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        openSearchClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        log.info("The Wikimedia Index has been created!");
      } else {
        log.info("The Wikimedia Index already exits");
      }

      log.info("The Wikimedia Index has been created!");
    }

    // Create our Kafka Client

    // Main code logic

    // Close Things


  }

}
