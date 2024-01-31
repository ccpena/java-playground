package org.kkpa.algorithms.strategy;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinLoadBalancer {
  private List<String> servers;
  private int currentIndex;

  public RoundRobinLoadBalancer(List<String> servers) {
    this.servers = new ArrayList<>(servers);
    this.currentIndex = 0;
  }

  public static void main(String[] args) {
    // Example usage
    List<String> serverList = List.of("Server1", "Server2", "Server3");
    RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer(serverList);

    // Simulate 10 requests
    for (int i = 0; i < 10; i++) {
      String nextServer = loadBalancer.getNextServer();
      System.out.println("Request " + (i + 1) + ": Directed to " + nextServer);
    }
  }

  public String getNextServer() {
    if (servers.isEmpty()) {
      throw new IllegalStateException("No servers available");
    }

    String nextServer = servers.get(currentIndex);
    currentIndex = (currentIndex + 1) % servers.size(); // Circular increment

    return nextServer;
  }
}

