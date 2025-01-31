package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    public User findById(int id);

    List<User> findAll();

    public User save(User user);

    public void deleteById(int id);
}

