package streams_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kkpa.pgutil.builders.AddressBuilder;
import org.kkpa.pgutil.builders.UserBuilder;
import org.kkpa.pgutil.models.user.User;
import org.kkpa.streams_api.ReducerOperator;

import java.util.List;

public class ReducerOperatorTest {
  private User userOne;
  private List<User> allUsers;

  private UserBuilder userBuilder = new UserBuilder(new AddressBuilder());

  private ReducerOperator reducerOperator = new ReducerOperator();

  @BeforeEach
  public void setup() {
    userOne = userBuilder.singleModel(1L);
    allUsers = userBuilder.randomData(5);
  }

  @Test
  public void findOldestUser() {
    System.out.println(reducerOperator.findOldestUser(allUsers));
  }
}
