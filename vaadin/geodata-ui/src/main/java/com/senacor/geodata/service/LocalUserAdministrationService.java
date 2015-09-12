package com.senacor.geodata.service;

import com.senacor.geodata.model.User;
import com.senacor.geodata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

/**
 * @author dschmitz
 */
@Service
public class LocalUserAdministrationService implements UserAdministrationService {
    private UserRepository userRepository;

    @Autowired
    public LocalUserAdministrationService(@Nonnull UserRepository userAdministrationRepository) {
        this.userRepository = userAdministrationRepository;
    }

    @Override
    public User createNewUser(@Nonnull String email) {
        return this.userRepository.save(new User(UUID.randomUUID().toString(), email));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
//        LinkedList<User> users = new LinkedList<>();

//        userRepository.findAll().forEach(users::add);

//        return users;
    }
}
