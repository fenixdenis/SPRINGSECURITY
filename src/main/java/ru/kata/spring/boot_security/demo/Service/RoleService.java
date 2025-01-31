package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRoles();

    public Role findByName(String name);
}
