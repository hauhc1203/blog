package com.codegym.service;

import com.codegym.models.Comment;
import com.codegym.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    ICommentRepo iCommentRepo;

    public List<Comment> findAllByBlog(Long id){
        return iCommentRepo.findAllByIdBlog(id);
    }

    public void save(Comment comment){
        iCommentRepo.save(comment);
    }
}
