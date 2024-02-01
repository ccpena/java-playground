package org.kkpa.algorithms.caching;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CustomCachingStrategy {

  public static void main(String[] args) {
    CachingProvider cachingProvider = new CachingProvider(2);

    cachingProvider.put(1, 1);
    cachingProvider.put(3, 3);
    cachingProvider.put(2, 2);

    cachingProvider.get(1);
    cachingProvider.get(1);
    cachingProvider.get(2);
    cachingProvider.get(3);
    cachingProvider.get(2);
    cachingProvider.get(1);
    cachingProvider.get(1);

    cachingProvider.put(4, 4);
    cachingProvider.get(3);

    cachingProvider.print();
  }

  private static class CacheEntry<K, V> {
    private int frequency;
    private V value;
    private K key;

    private Instant lastAccess = Instant.now();

    public CacheEntry(K key, V value) {
      this.key = key;
      this.value = value;
      this.frequency = 1;
    }

    @Override
    public String toString() {
      return "{" +
              "value=" + key +
              ", frequency=" + frequency +
              "}";
    }
  }

  private static class CachingComparator<K, V> implements Comparator<CacheEntry<K, V>> {
    @Override
    public int compare(CacheEntry<K, V> o1, CacheEntry<K, V> o2) {
      System.out.println("Comparing...");
      if (o1.frequency > o2.frequency) {
        return 1;
      } else if (o1.frequency < o2.frequency) {
        return -1;
      } else {
        return o1.lastAccess.compareTo(o2.lastAccess);
      }
    }
  }

  public static class CachingProvider<K, V> {
    private Map<K, CacheEntry<K, V>> cache = new HashMap<>();
    private int capacity;
    private PriorityQueue<CacheEntry<K, V>> heap = new PriorityQueue<>(new CachingComparator<K, V>());

    public CachingProvider(int capacity) {
      this.capacity = capacity;
    }

    public V get(K key) {
      CacheEntry<K, V> cacheEntry = cache.get(key);
      if (cacheEntry == null) {
        return null;
      }
      incrementAccess(cacheEntry);
      return cacheEntry.value;
    }

    private void incrementAccess(CacheEntry<K, V> cacheEntry) {
      cache.merge(cacheEntry.key, cacheEntry, (oldValue, newValue) -> {
        oldValue.frequency++;
        oldValue.lastAccess = Instant.now();
        return oldValue;
      });
      updateHeap(cacheEntry);
    }

    public void put(K key, V value) {
      CacheEntry<K, V> valueEntry = cache.containsKey(key) ? cache.get(key) : new CacheEntry<>(key, value);
      if (cache.size() >= capacity) {
        if (valueEntry.frequency == 1) {
          purgeHeap();
        }
      }
      cache.put(key, valueEntry);
      updateHeap(valueEntry);
    }

    private void updateHeap(final CacheEntry cacheEntry) {
      if (heap.contains(cacheEntry)) {
        heap.remove(cacheEntry);
      }
      heap.add(cacheEntry);
    }

    private void purgeHeap() {
      int heapSize = heap.size();

      while (heapSize >= capacity) {
        CacheEntry<K, V> key = heap.remove();
        cache.remove(key.key);
        heapSize--;
      }

    }

    public void print() {
      this.heap.forEach(heapEntry -> System.out.println(heapEntry));
    }
  }


}
