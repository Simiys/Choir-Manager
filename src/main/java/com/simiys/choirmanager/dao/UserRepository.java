package com.simiys.choirmanager.dao;

import com.simiys.choirmanager.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
        Optional<User> getByUsername (String username);
}
