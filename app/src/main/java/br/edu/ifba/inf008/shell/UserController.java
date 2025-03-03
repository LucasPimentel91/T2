package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.*;
import java.util.*;

public class UserController implements IUserController {
    

    public boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    public boolean isValidEmail(String email) {
        return email != null && !email.isEmpty();
    }

    public boolean isValidPassword(String password) {
        return password != null && !password.isEmpty();
    }

    public boolean requestCreateUser(String name, String email, String password) {
        if (isValidName(name) && isValidEmail(email) && isValidPassword(password)) {
            return true;
        }
        return false;
    }

    public IUser createUser(String name, String email, String password) {
        IUser user = new User(name, email, password);
        return user;
    }

    public boolean thisUserExists(IIOController ioController, IUser user) {
        return ioController.getListUser().contains(user);
    }

    public ArrayList<IBook> getListBooks(IUser user){
        return user.myListBooks();
    }

    public IUser findUserByEmail(String email) {
        var ioController = Core.getInstance().getIOController();
        
        for (IUser user : ioController.getListUser()) {
            if (email != null && email.equals(user.getEmail())) {
                return user;
            }
        }
        
        return null; 
    }
}
