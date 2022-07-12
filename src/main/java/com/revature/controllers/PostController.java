package com.revature.controllers;

import com.revature.models.Post;
import com.revature.models.User;
import com.revature.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PostController {

    private PostService ps;

    @Autowired
    public PostController(PostService ps){
        this.ps = ps;
    }

    @PostMapping("/post")
    public Post handleCreatePost(@RequestParam(name="userId")int userid, @RequestBody Post body){
        System.out.println(body);
        return ps.createNewPost(userid, body.getContent(), body.getPostedDate());
    }

    @GetMapping("/post/user/{id}")
    public List<Post> handleGetAllPostFromUser(@PathVariable("id")int id){
        return ps.getAllBySpecifiedUser(id);
    }

    @GetMapping("/post/following/{id}")
    public List<Post> handleGetAllPostsFromFollowingList(@PathVariable("id")int id){
        return ps.getFollowersPosts(id);
    }
}
