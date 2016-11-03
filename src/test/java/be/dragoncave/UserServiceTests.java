package be.dragoncave;

import be.dragoncave.domain.*;
import be.dragoncave.persistance.UserRepository;
import be.dragoncave.service.TaskService;
import be.dragoncave.service.TaskServiceImpl;
import be.dragoncave.service.UserService;
import be.dragoncave.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * Created by benoit on 02/11/2016.
 */
@RunWith(SpringRunner.class)

public class UserServiceTests {

    @MockBean
    private UserRepository userRepository;

    @Test()
    @Rollback(value = true)
    public void saveUserTest() {
        User persUser = new User("xwcwx", "sdd", "dqd", "dsqd", "9899", "dfsdf", new Country("Nederland"), LocalDateTime.now());
        when(this.userRepository.findOne(1)).thenReturn(persUser);
        UserService taskService = new UserServiceImpl(userRepository);
        assertThat(taskService.user(1), is(persUser));
    }
}
