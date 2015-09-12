package com.senacor.geodata.repository;

import com.senacor.geodata.TestApplicationContext;
import com.senacor.geodata.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertNotSame;

/**
 * @author dschmitz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplicationContext.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test(expected = DataIntegrityViolationException.class)
    public void emails_should_be_unique() {
        userRepository.save(new User("abc", "foo@bar.de"));
        userRepository.save(new User("adbc", "foo@bar.de"));
    }

    @Test
    public void a_user_can_be_stored_in_the_repository() {
        User user = new User("abc", "foo@bar.de");
        User savedInstance = userRepository.save(user);

        assertNotSame(user, savedInstance);
    }

    @Test
    public void a_user_that_is_stored_can_be_read() {
        User user = new User("abc", "foo@bar.de");
        user = userRepository.save(user);

        User foundUser = userRepository.findOne("abc");

        assertThat(foundUser.getId()).isEqualTo(user.getId());
        assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void a_user_can_be_updated() {
        User user = new User("abc", "foo@bar.de");
        user = userRepository.save(user);

        user.setEmail("newemail@foo.com");

        userRepository.save(user);

        User foundUser = userRepository.findOne("abc");
        assertThat(foundUser.getEmail()).isEqualTo("newemail@foo.com");
    }

    @Test
    public void find_all_returns_all_users() {
        userRepository.save(new User("abc", "foo@bar.de"));
        userRepository.save(new User("abc", "foo@qux.de"));
        userRepository.save(new User("abc", "foo@baz.de"));

        List<User> users = userRepository.findAll();

        assertThat(users.size()).isGreaterThanOrEqualTo(3);
    }
}