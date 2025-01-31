package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;




public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByName(String name);

    List<Role> findAll();

}

