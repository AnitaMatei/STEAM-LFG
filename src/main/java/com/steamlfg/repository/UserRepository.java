package com.steamlfg.repository;

import com.steamlfg.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUserId(int userId);

    Optional<User> findByUsername(String userName);

    Optional<User> findByOid(String oid);
}
