package com.intransition.labs.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class LoginForm {

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String password;

    boolean rememberMe;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void remember(boolean remember) {
        rememberMe = remember;
    }

    public boolean isRemembed() {
        return rememberMe;
    }

}
