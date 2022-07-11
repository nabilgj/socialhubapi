package com.revature.services;

import com.revature.exception.InvalidCredentialException;
import com.revature.exception.InvalidUserException;
import com.revature.models.User;
import com.revature.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private UserRepo ur;

    @Autowired
    public UserService(UserRepo ur){
        this.ur = ur;
    }

    // register
    public User registerNewUser(String first, String last, String username, String email, String password){
        User register = new User(first, last, username, email, password);

        return ur.save(register);

//        try {
//            return ur.save(register);
//        } catch(Exception e){
//            throw new InvalidUserException();
//        }
    }

    // login
    public User loginUser(String username, String password){
        User loggedIn = ur.findUserByUsernameAndPassword(username, password);

        if(loggedIn == null){
            throw new InvalidCredentialException();
        }

        return loggedIn;
    }
    // update

    // delete

}
