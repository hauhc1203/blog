package com.codegym.repository;

import com.codegym.models.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ILikeRepo extends CrudRepository<Like,Integer> {
    int countLikeByBlogs_Id(long id);
}
