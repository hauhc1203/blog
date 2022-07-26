package com.codegym.repository;

import com.codegym.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface IAccRepo extends CrudRepository<Account, Integer> {
}
