package com.lyca2206.repository.abstraction;

import com.lyca2206.model.User;

public interface AuthenticationRepository {
    User signIn(User user);
    User signUp(User user);
}
