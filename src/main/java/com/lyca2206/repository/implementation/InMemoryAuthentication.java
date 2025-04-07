package com.lyca2206.repository.implementation;

import com.lyca2206.model.User;
import com.lyca2206.repository.abstraction.AuthenticationRepository;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
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
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(user.getPassword().toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();

        user.setPassword(salt, hash);
        users.put(user.getEmail(), user);
    }
}
