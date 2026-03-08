package service;

import domain.Users;
import io.quarkus.security.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;


@ApplicationScoped
public class UserService {

    @Transactional
    public Users createUser(Users users) {

        Users.persist(users);
        return users;
    }

    public List<Users> getUsers() {
        return Users.listAll();
    }

    public Users getUserById(UUID id) {
        return Users.findById(id);
    }
}
