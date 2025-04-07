package com.lyca2206.repository.abstraction;

import com.lyca2206.model.User;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthenticationRepository {
    User signIn(User user) throws AccountNotFoundException, FailedLoginException, NoSuchAlgorithmException, InvalidKeySpecException;
    void signUp(User user) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
