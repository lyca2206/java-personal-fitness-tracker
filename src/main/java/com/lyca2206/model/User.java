package com.lyca2206.model;

import java.util.regex.Pattern;

public class User {
    private final String email;
    private final String password;
    private final Role role;
    private final String firstName;
    private final String lastName;

    public User(String email, String password, Role role, String firstName, String lastName) {
        validateEmail(email);
        validatePassword(password);
        validateRole(role);
        validateName(firstName);

        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private void validateEmail(String email) {
        Pattern regexPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

        boolean match = regexPattern
                .matcher(email)
                .matches();

        if (!match) {
            throw new IllegalArgumentException("The given email is invalid");
        }
    }

    private void validatePassword(String password) {
        Pattern regexPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

        boolean match = regexPattern
                .matcher(password)
                .matches();

        if (!match) {
            throw new IllegalArgumentException("The given password is invalid: it should contain 8 characters, 1 digit, 1 uppercase and 1 lowercase letter");
        }
    }

    private void validateRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("The given role shouldn't be null");
        }
    }

    private void validateName(String name) {
        if (name == null || name.length() < 3) {
            throw new IllegalArgumentException("Any of the names shouldn't be empty and they have to be at least 3 letters long");
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
