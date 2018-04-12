package com.kang.mvp;

import javax.inject.Inject;

public class User {
    private String name;

    @Inject
    public User() {
        this.name = "This is a Dagger2 test";
    }

    public String getName() {
        return name;
    }
}
