package com.codegym.service;

import com.codegym.models.Blogs;
import com.codegym.models.Like;
import com.codegym.repository.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    ILikeRepo iLikeRepo;

    public void save(Like like) {
        iLikeRepo.save(like);
    }
    public int count(long id){
        return iLikeRepo.countLikeByBlogs_Id(id);
    }

}
