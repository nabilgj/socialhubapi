package com.revature.services;


import com.revature.models.User;
import com.revature.repo.PostRepo;
import com.revature.models.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostService {

    private PostRepo pr;
    private UserService us;

    @Autowired
    public PostService(PostRepo pr, UserService us){
        this.pr = pr;
        this.us = us;
    }

    public Post createNewPost(int userId, String content, Date postDate){
        User postUser = us.getCurrentUserById(userId);

        Post p = new Post(0, content, postDate, postUser);

        return pr.save(p);
    }

    public List<Post> getAllBySpecifiedUser(int id){
        User u = us.getCurrentUserById(id);
        return u.getPosts();
    }

    public List<Post> getFollowersPosts(int id){
        User u = us.getCurrentUserById(id);

        List<Post> allPosts = new ArrayList<>();

        for(User follow: u.getFollowing()){
            allPosts.addAll(follow.getPosts());
        }

//        allPosts.addAll(u.getPosts());

        return allPosts;
    }
}
