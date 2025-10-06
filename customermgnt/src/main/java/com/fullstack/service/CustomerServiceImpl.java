package com.fullstack.service;

import com.fullstack.entity.Customer;
import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer signUp(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> saveAll(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    @Override
    public boolean signIn(String custEmailId, String custPassword) {

        boolean flag = false;

        Customer customer = customerRepository.findByCustEmailIdAndCustPassword(custEmailId, custPassword);

        if (customer != null){
            flag = true;
        }
        return flag;
    }

    @Override
    @Cacheable(value = "custId")
    public Optional<Customer> findById(long custId) {
        return Optional.ofNullable(customerRepository.findById(custId).orElseThrow(() -> new RecordNotFoundException("Customer #ID Does Not Exist.")));
    }

    @Override
    public List<Customer> findByName(String custName) {
        return customerRepository.findByCustName(custName);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(long custId, Customer customer) {

        Customer customer1 = findById(custId).get();
        customer1.setCustName(customer.getCustName());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustContactNumber(customer.getCustContactNumber());
        customer1.setCustDOB(customer.getCustDOB());
        customer1.setCustGender(customer.getCustGender());
        customer1.setCustEmailId(customer.getCustEmailId());
        customer1.setCustPassword(customer.getCustPassword());
        return customerRepository.save(customer1);
    }

    @Override
    public void deleteById(long custId) {
         customerRepository.deleteById(custId);
    }

    @Override
    public void deleteAll() {
         customerRepository.deleteAll();
    }
}
