package com.lyca2206.repository.implementation;

import com.lyca2206.model.User;
import com.lyca2206.repository.abstraction.AuthenticationRepository;
import com.lyca2206.utilities.hash.generator.HashGenerator;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class InMemoryAuthentication implements AuthenticationRepository {
    private final Map<String, User> users;

    public InMemoryAuthentication(Map<String, User> users) {
        this.users = users;
    }

    @Override
    public User signIn(User user) {
        return null;
    }

    @Override
    public void signUp(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = HashGenerator.getSalt();
        byte[] hash = HashGenerator.hashPassword(user.getPassword().toCharArray(), salt);
        user.setPassword(salt, hash);
        users.put(user.getEmail(), user);
    }
}
