package com.fullstack.repository;

import com.fullstack.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByCustEmailIdAndCustPassword(String custEmailId, String custPassword);

    List<Customer> findByCustName(String custName);

}
