import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kkpa.MainPGCore;

public class MainCoreTest {

  @Test
  public void setup(){
    Assertions.assertEquals("Hello", MainPGCore.returnHello());
  }
}
