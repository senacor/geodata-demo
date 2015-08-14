package com.senacor.geodata.repository;

import com.senacor.geodata.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for handling users and associated data.
 *
 * @author dschmitz
 */
public interface UserRepository extends CrudRepository<User, String> {
    List<User> findAll();
}
