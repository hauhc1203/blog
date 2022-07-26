package com.codegym.service;

import com.codegym.models.Account;
import com.codegym.repository.IAccRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccService {
    @Autowired
    IAccRepo iAccRepo;

    public List<Account> getAll(){
        return (List<Account>) iAccRepo.findAll();
    }
    public Account check(String userName,String pass){
        for (Account a :
                getAll()) {
            if (a.getUserName().equals(userName)&&a.getPass().equals(pass)&&a.isLogged()==false){
                return a;
            }
        }
        return null;
    }
    public void save(Account account){
        iAccRepo.save(account);
    }

}
