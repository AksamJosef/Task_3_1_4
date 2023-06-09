package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.RolesRepository;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;
import ru.kata.spring.boot_security.demo.security.EntityUserDetails;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }


    @Transactional
    @Override
    public void addAsUser(User user) {
//        add ass User by default

        if (user.getRoles() == null) {
            user.addRole(rolesRepository.getById(1));
        } else {
            user.setRoles(user.getRoles());
        }
        usersRepository.save(user);
    }

    @Override
    public List<User> getUserList() {
        return usersRepository.findAll();
    }


    @Transactional
    @Override
    public void delete(Long id) {
        usersRepository.deleteById(id);
    }


    @Transactional
    @Override
    public void update(User user) {

        usersRepository.save(user);
    }


    @Override
    public User getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }


}
