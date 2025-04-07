package com.lyca2206.repository.abstraction;

import com.lyca2206.model.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthenticationRepository {
    User signIn(User user);
    void signUp(User user) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
