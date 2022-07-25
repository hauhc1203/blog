package com.codegym.repository;

import com.codegym.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepo extends CrudRepository<Category, Long> {
}
