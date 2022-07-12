package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.User;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    // start our basic crud operations
    // create is handle by jpa repository - dont wory
    // Read all, and findById are handled by JpaRepository
    // Update is also handled by JpaRepository
    // delete is also handled by JpaRepository

    public User findUserByUsername(String username);

    public User findUserByUsernameAndPassword(String username, String password);


}
