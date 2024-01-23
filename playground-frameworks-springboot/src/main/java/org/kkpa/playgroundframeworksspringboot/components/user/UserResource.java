package org.kkpa.playgroundframeworksspringboot.components.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {

  private final UserService userService;

  public UserResource(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/")
  public ResponseEntity<User> save() {
    User user = new User();
    user.setId(1L);
    user.setName("Dynamic");
    return ResponseEntity.of(this.userService.save(user));
  }

}
