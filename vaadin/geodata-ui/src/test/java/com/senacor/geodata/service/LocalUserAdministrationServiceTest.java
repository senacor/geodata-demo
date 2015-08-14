package com.senacor.geodata.service;

import com.senacor.geodata.TestApplicationContext;
import com.senacor.geodata.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author dschmitz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplicationContext.class)
@Transactional
public class LocalUserAdministrationServiceTest {

    @Autowired
    private LocalUserAdministrationService userAdministrationService;

    @Autowired
    private EntityManager em;

    @Test
    public void a_created_user_gets_an_id_set() {
        User user = userAdministrationService.createNewUser("foo@bar.de");

        assertThat(user.getEmail()).isEqualTo("foo@bar.de");
        assertThat(user.getId()).isNotEmpty();
    }

    @Test(expected = PersistenceException.class)
    public void creating_an_user_with_an_existing_email_fails() {
        userAdministrationService.createNewUser("foo@bar.de");
        userAdministrationService.createNewUser("foo@bar.de");
        em.flush(); // force db sync
    }

    @Test
    public void find_all_returns_all_users() {
        List<String> emails = new LinkedList<>(Arrays.asList("foo@bar.de", "faaoo@araa.de", "ff@aqq.de"));
        emails.stream().forEach(userAdministrationService::createNewUser);

        List<User> allUsers = userAdministrationService.findAllUsers();

        allUsers.stream().map(User::getEmail).forEach(emails::remove);

        assertTrue("Cannot find all emails in result Leftover: " + emails, emails.isEmpty());
    }
}