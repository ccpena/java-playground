package algorithms.caching;

import org.junit.jupiter.api.Test;
import org.kkpa.algorithms.caching.CustomCachingStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CustomCachingStrategyTest {

  @Test
  void testCachingStrategy() {
    CustomCachingStrategy.CachingProvider<String, Integer> lfuCache =
            new CustomCachingStrategy.CachingProvider<>(3);

    // Test basic put and get operations
    lfuCache.put("A", 1);
    lfuCache.put("B", 2);
    lfuCache.put("C", 3);

    assertEquals(1, lfuCache.get("A"));
    assertEquals(2, lfuCache.get("B"));
    assertEquals(3, lfuCache.get("C"));

    // Test LFU eviction
    lfuCache.get("A");
    lfuCache.get("A");
    lfuCache.get("B");

    lfuCache.put("D", 4);

    assertNull(lfuCache.get("C")); // C should be evicted due to LFU
    
  }
}
