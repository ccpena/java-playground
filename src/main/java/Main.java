import java.util.Map;

public class Main {
  public static void main(String[] args) {
    final Map<String, String> mapa = Map.of();

    System.out.println(mapa.getOrDefault(null, "HOLA"));
  }
}
