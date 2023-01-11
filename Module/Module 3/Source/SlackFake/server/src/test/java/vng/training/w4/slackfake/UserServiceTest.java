package vng.training.w4.slackfake;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vng.training.w4.slackfake.model.User;
import vng.training.w4.slackfake.service.user.UserService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = SlackFakeApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

/*    @Autowired
    private UserService userService;*/

    @Test
    public void testRegister() {
/*        userService.register("admin", "admin113", "Admin Ne");
        User user = userService.getUserByUsername("admin");

        assertNotNull(user);*/
    }

}
