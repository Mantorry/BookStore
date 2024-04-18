package com.example.bookstore.Repo;

import com.example.bookstore.Data.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Long>
{ Users findByUsername(String username);}