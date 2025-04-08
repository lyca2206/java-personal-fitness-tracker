package com.lyca2206.libraries.session;

public class Session {
    private Object principal;
    private static Session instance;

    public Object getPrincipal() {
        return principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }

        return instance;
    }
}