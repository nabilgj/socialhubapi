package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {


}
