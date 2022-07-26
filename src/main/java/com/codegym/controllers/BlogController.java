package com.codegym.controllers;

import com.codegym.models.*;
import com.codegym.service.BlogService;
import com.codegym.service.CategoryService;
import com.codegym.service.CommentService;
import com.codegym.service.LikeService;
import com.codegym.validate.ValidateBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ValidateBlog validateBlog;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;
    @ModelAttribute(name = "like")
    public Like like(){
        return new Like();
    }
    @ModelAttribute(name = "blog")
    public Blogs blogs(){
        return new Blogs();
    }

    @ModelAttribute(name = "categories")
    public List<Category> categories(){
        return categoryService.getAll();
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("index");
        Account account= (Account) httpSession.getAttribute("acc");
        modelAndView.addObject("account",account);

        return modelAndView;
    }

    @GetMapping("/blogs")
    public ModelAndView show(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        Account account= (Account) httpSession.getAttribute("acc");
        modelAndView.addObject("account",account);
        modelAndView.addObject("blogs", blogService.getAll(PageRequest.of(page, 2, Sort.by("date"))));
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam(defaultValue = "0") int page, @RequestParam String search) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("blogs", blogService.getAllByTitle(PageRequest.of(page, 2, Sort.by("date")), search));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView;
    }
    @GetMapping("/show")
    public ModelAndView showBlog(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("single-post");
        modelAndView.addObject("comments",commentService.findAllByBlog(id));
        modelAndView.addObject("blog_show", blogService.findById(id).get());
        modelAndView.addObject("likeNum",likeService.count(id));
        Account account= (Account) httpSession.getAttribute("acc");
        modelAndView.addObject("account",account);


        return modelAndView;
    }
    @PostMapping("/comment/{idBlog}")
    public ModelAndView comment(Comment comment,@PathVariable("idBlog") long idBlog) {
        Blogs blogs = blogService.findById(idBlog).get();
        comment.setBlogs(blogs);
        commentService.save(comment);
        ModelAndView modelAndView = new ModelAndView("redirect:/show?id="+idBlog);
        return modelAndView;
    }
    @GetMapping("/like/{idBlog}")
    public ModelAndView like(Like like, @PathVariable("idBlog") long idBlog) {
        Blogs blogs = blogService.findById(idBlog).get();
        like.setBlogs(blogs);
        likeService.save(like);
        ModelAndView modelAndView = new ModelAndView("redirect:/show?id="+idBlog);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute("blog") Blogs blogs,
                               BindingResult bindingResult,
                               @RequestParam MultipartFile upImg) {
        validateBlog.validate(blogs,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }
        String nameImg = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("/home/hauhc1203/Desktop/Blog_C0322g1/src/main/webapp/WEB-INF/img/" + nameImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blogs.setImg("/img/" + nameImg);
        blogService.save(blogs);
        ModelAndView modelAndView = new ModelAndView("redirect:/blogs");
        return modelAndView;
    }

}
