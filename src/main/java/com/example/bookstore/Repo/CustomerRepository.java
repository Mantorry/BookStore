package com.example.bookstore.Repo;
import com.example.bookstore.Data.Book;
import com.example.bookstore.Data.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {}


