package com.codegym.repository;

import com.codegym.models.Blogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface IBlogRepo extends PagingAndSortingRepository<Blogs,Long> {
    Page<Blogs> findByTitleContaining(Pageable pageable, String title);
    Optional<Blogs> findByTitle(String title);

}
