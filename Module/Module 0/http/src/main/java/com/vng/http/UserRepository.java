package com.vng.http;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public List<User> findByUserEmail(String email);
}
