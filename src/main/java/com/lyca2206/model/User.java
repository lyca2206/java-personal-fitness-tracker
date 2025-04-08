package com.lyca2206.model;

import java.util.regex.Pattern;

public class User {
    private final String email;
    private String password;
    private final Role role;
    private final String firstName;
    private final String lastName;

    public User(String email, String password) {
        validateEmail(email);

        this.email = email;
        setPassword(password);
        this.role = null;
        this.firstName = null;
        this.lastName = null;
    }

    public User(String email, String password, Role role, String firstName, String lastName) {
        validateEmail(email);
        validateRole(role);
        validateName(firstName);
        validateName(lastName);

        this.email = email;
        setPassword(password);
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, Role role, String firstName, String lastName) {
        validateEmail(email);
        validateRole(role);
        validateName(firstName);
        validateName(lastName);

        this.email = email;
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
            throw new IllegalArgumentException("The given email is invalid: it requires an @ and a domain (.com, .org, etc.)");
        }
    }

    public void setPassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    private void validatePassword(String password) {
        if (password == null) {
            return;
        }

        Pattern regexPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

        boolean match = regexPattern
                .matcher(password)
                .matches();

        if (!match) {
            throw new IllegalArgumentException("The given password is invalid: it must contain 8 characters, 1 digit, 1 uppercase letter and 1 lowercase letter");
        }
    }

    private void validateRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("The given role must be a non null value");
        }
    }

    private void validateName(String name) {
        if (name == null || name.length() < 3) {
            throw new IllegalArgumentException("Any of the names should be at least 3 characters long, and it has to be a non null value");
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

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}