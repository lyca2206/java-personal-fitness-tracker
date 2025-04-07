package com.lyca2206.repository.implementation;

import com.lyca2206.model.User;
import com.lyca2206.repository.abstraction.AuthenticationRepository;
import com.lyca2206.utilities.hash.generator.HashGenerator;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class InMemoryAuthentication implements AuthenticationRepository {
    private final Map<String, User> users;

    public InMemoryAuthentication(Map<String, User> users) {
        this.users = users;
    }

    @Override
    public User signIn(User user) throws AccountNotFoundException, FailedLoginException {
        User storedUser = users.get(user.getEmail());

        if (storedUser == null) {
            throw new AccountNotFoundException("The specified user couldn't be found");
        }

        if (!storedUser.getPassword().equals(user.getPassword())) {
            throw new FailedLoginException("The given credentials don't match");
        }

        return storedUser;
    }

    @Override
    public void signUp(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = HashGenerator.getSalt();
        byte[] hash = HashGenerator.hashPassword(user.getPassword().toCharArray(), salt);
        users.put(user.getEmail(), user);
    }
}
