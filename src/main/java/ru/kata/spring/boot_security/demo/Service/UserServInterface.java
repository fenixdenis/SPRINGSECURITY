package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserServInterface {


    List<User> findAll();

    public void save(User user);

    public User getUser(int id);

    public void deleteById(int id);

}
