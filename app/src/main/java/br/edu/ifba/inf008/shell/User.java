package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IUser;

public class User implements IUser {
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String toString(){
        return getName();
    }
}
