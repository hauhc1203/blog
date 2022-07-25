package com.codegym.repository;

import com.codegym.models.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICommentRepo extends CrudRepository<Comment, Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM blog_c0322g1.Comment where blogs_id =:id")
    List<Comment> findAllByIdBlog(@Param("id") Long id);
}
