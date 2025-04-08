package com.lyca2206.repository.implementation;

import com.lyca2206.model.Role;
import com.lyca2206.model.User;
import com.lyca2206.repository.abstraction.AuthenticationRepository;
import com.lyca2206.utilities.hash.generator.HashGenerator;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Map;

public class InMemoryAuthentication implements AuthenticationRepository {
    private final Map<String, StoredUser> storedUsers;

    public InMemoryAuthentication(Map<String, StoredUser> storedUsers) {
        this.storedUsers = storedUsers;
    }

    @Override
    public User signIn(User user) throws AccountNotFoundException, FailedLoginException, NoSuchAlgorithmException, InvalidKeySpecException {
        StoredUser storedUser = storedUsers.get(user.getEmail());

        if (storedUser == null) {
            throw new AccountNotFoundException("The given user does not exist");
        }

        byte[] hash = HashGenerator.hashPassword(
                user.getPassword().toCharArray(),
                storedUser.salt
        );

        if (!Arrays.equals(hash, storedUser.hash)) {
            throw new FailedLoginException("The given credentials do not match with any existing user");
        }

        return new User(
                storedUser.email,
                Role.valueOf(storedUser.role.name()),
                storedUser.firstName,
                storedUser.lastName
        );
    }

    @Override
    public void signUp(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        storedUsers.put(
                user.getEmail(),
                new StoredUser(user)
        );
    }

    public static class StoredUser {
        private final String email;
        private final byte[] salt;
        private final byte[] hash;
        private final Role role;
        private final String firstName;
        private final String lastName;

        public StoredUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
            email = user.getEmail();
            salt = HashGenerator.getSalt();

            hash = HashGenerator.hashPassword(
                    user.getPassword().toCharArray(),
                    salt
            );

            role = Role.valueOf(user.getRole().name());
            firstName = user.getFirstName();
            lastName = user.getLastName();
        }
    }
}