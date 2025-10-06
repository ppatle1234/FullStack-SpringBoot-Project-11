package com.fullstack.service;

import com.fullstack.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    Customer signUp(Customer customer);

    List<Customer> saveAll(List<Customer> customers);

    boolean signIn(String custEmailId, String custPassword);

    Optional<Customer> findById(long custId);

    List<Customer> findByName(String custName);

    List<Customer> findAll();

    Customer update(long custId, Customer customer);

    void deleteById(long custId);

    void deleteAll();

}
