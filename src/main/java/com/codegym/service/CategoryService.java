package com.codegym.service;

import com.codegym.models.Category;
import com.codegym.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService {
    @Autowired
    ICategoryRepo iCategoryRepo;

    public List<Category> getAll(){
        return (List<Category>) iCategoryRepo.findAll();
    }
    public Category findById(long id){
        return iCategoryRepo.findById(id).get();
    }
}
