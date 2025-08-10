package com.telusko.joblisting.controller;

import com.telusko.joblisting.repository.PostRepository;
import com.telusko.joblisting.model.Post;
import com.telusko.joblisting.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController
{

    @Autowired
    PostRepository repo;

    @Autowired
    SearchRepository srepo;

    //For Swagger alone
    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/allPosts")
    @CrossOrigin
    public List<Post> getAllPosts()
    {
        System.out.println("repo.findAll"+ repo.findAll());
        return repo.findAll();
    }


    @PostMapping("/post")
    @CrossOrigin
    public Post addPost(@RequestBody Post post)
    {
        return repo.save(post);
    }

    @DeleteMapping("/post/{id}")
    @CrossOrigin
    public void deletePost(@PathVariable String id) {
        System.out.println("repodeleteByIid");
        repo.deleteById(id);
    }

    @PutMapping("/post/{id}")
    @CrossOrigin
    public Post updatePost(@PathVariable String id, @RequestBody Post updatedPost) {
        return repo.findById(id).map(post -> {
            post.setProfile(updatedPost.getProfile());
            post.setDesc(updatedPost.getDesc());
            post.setExp(updatedPost.getExp());
            post.setTechs(updatedPost.getTechs());
            return repo.save(post);
        }).orElseGet(() -> {
            updatedPost.setId(id); // Make sure Post.java has an id field
            return repo.save(updatedPost);
        });
    }

//    Atlas search - example query is "java"
    @GetMapping("/posts/{text}")
    @CrossOrigin
    public List<Post> search(@PathVariable String text)
    {
        return srepo.findByText(text);
    }


}
