package com.codegym.controllers;

import com.codegym.models.Account;
import com.codegym.service.AccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    AccService accService;

    @GetMapping("/login")
    public ModelAndView showFormlogin(){
        ModelAndView modelAndView=new ModelAndView("login");
        return modelAndView;
    }
    @GetMapping("/logout")
    public ModelAndView logout(){
        Account account= (Account) httpSession.getAttribute("acc");
        account.setLogged(false);
        httpSession.setAttribute("acc",account);
        accService.save(account);
        ModelAndView modelAndView=new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String userName,@RequestParam("pass") String pass){
        Account account=accService.check(userName,pass);
        ModelAndView modelAndView;

        if (account!=null){
            account.setLogged(true);
            httpSession.setAttribute("acc",account);
            accService.save(account);
            modelAndView=new ModelAndView("redirect:/blogs");

        }else {
            modelAndView=new ModelAndView("redirect:/login");
        }
        return modelAndView;
    }


}
