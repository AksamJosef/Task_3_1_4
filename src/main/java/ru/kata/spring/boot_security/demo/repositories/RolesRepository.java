package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.Optional;


@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {
}
