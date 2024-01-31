package org.kkpa.algorithms.caching;

import java.util.LinkedHashMap;

public class LeastRecentlyUsed {

  public static void main(String[] args) {
    LeastRecentlyUsed lru = new LeastRecentlyUsed();
    lru.run();
  }

  private void run() {
    LRUCache<String, String> lruCache = new LRUCache<String, String>(4);
    lruCache.put("1", "1");
    lruCache.put("2", "2");

    lruCache.get("2");
    printCache(lruCache);
    lruCache.put("3", "3");
    lruCache.put("4", "4");
    printCache(lruCache);
    lruCache.get("3");
    lruCache.get("3");
    lruCache.get("2");
    lruCache.get("2");

    printCache(lruCache);
    lruCache.put("5", "5");

    printCache(lruCache);
  }

  private void printCache(LRUCache<String, String> lruCache) {
    lruCache.forEach((key, value) -> System.out.println(key + ":" + value));
  }

  class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCache(int capacity) {
      // Hey, magical box, when you're about 75% full, start thinking about making some space
      // The ordering mode - true for access-order (least-recently-used), false for insertion-order(remove from the head. First insertion)
      super(capacity, 0.50f, false);
      this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
      return size() > capacity;
    }
  }

}
