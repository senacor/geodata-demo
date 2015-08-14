package com.senacor.geodata.service;

import com.senacor.geodata.model.User;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author dschmitz
 */
public interface UserAdministrationService {
    User createNewUser(@Nonnull String email);
    List<User> findAllUsers();
}
