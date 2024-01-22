package org.kkpa.pgutil.builders;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public interface UniversalModelBuilder<T> {

  default String generateRandomText(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException("Length must be greater than 0");
    }

    // Define the characters you want to include in the random text
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    int charactersLength = characters.length();
    StringBuilder generatedText = new StringBuilder();
    AtomicInteger randomIdx = new AtomicInteger(-1);
    Random random = new Random();
    IntStream.rangeClosed(0, length - 1)
            .parallel()
            .forEach((idx) -> {
              randomIdx.set(random.nextInt(charactersLength));
              generatedText.append(characters.charAt(randomIdx.get()));
            });
    return generatedText.toString();
  }

  List<T> randomData(int n);

  T singleModel(long id);
}
