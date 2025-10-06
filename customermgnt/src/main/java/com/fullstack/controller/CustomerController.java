package com.fullstack.controller;

import com.fullstack.entity.Customer;
import com.fullstack.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final ICustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<Customer> signup(@RequestBody Customer customer) {
        log.info(" **** Trying to Save Data for : " + customer.getCustName());
        return new ResponseEntity<>(customerService.signUp(customer), HttpStatus.CREATED);
    }

    @GetMapping("/signin/{custEmailId}/{custPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String custEmailId, @PathVariable String custPassword){
        return new ResponseEntity<>(customerService.signIn(custEmailId, custPassword), HttpStatus.OK);
    }

    @GetMapping("/findbyid")
    public ResponseEntity<List<Customer>> findByIDs(@RequestParam int ids[]){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/saveall")
    public ResponseEntity<List<Customer>> saveAll(@RequestBody List<Customer> customers) {
        return new ResponseEntity<>(customerService.saveAll(customers), HttpStatus.CREATED);
    }

    @GetMapping("/findbyid/{custId}")
    public ResponseEntity<Optional<Customer>> findById(@PathVariable long custId) {
        return new ResponseEntity<>(customerService.findById(custId), HttpStatus.OK);
    }

    @GetMapping("/findbyname/{custName}")
    public ResponseEntity<List<Customer>> findByName(@PathVariable String custName) {
        return new ResponseEntity<>(customerService.findByName(custName), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Customer>> findAll() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findbydob/{custDOB}")
    public ResponseEntity<List<Customer>> findByDOB(@PathVariable String custDOB) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return new ResponseEntity<>(customerService.findAll().stream().filter(cust -> simpleDateFormat.format(cust.getCustDOB()).equals(custDOB)).toList(), HttpStatus.OK);
    }

    @GetMapping("/findbyanyinput/{input}")
    public ResponseEntity<List<Customer>> findByAnyInput(@PathVariable String input) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return new ResponseEntity<>(customerService.findAll().stream().filter(cust -> cust.getCustName().equals(input)
                || String.valueOf(cust.getCustId()).equals(input)
                || String.valueOf(cust.getCustContactNumber()).equals(input)
                || cust.getCustEmailId().equals(input)
                || simpleDateFormat.format(cust.getCustDOB()).equals(input)).toList(), HttpStatus.OK);
    }

    @PutMapping("/update/{custId}")
    public ResponseEntity<Customer> update(@PathVariable long custId, @RequestBody Customer customer){
         return new ResponseEntity<>(customerService.update(custId, customer), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid/{custId}")
    public ResponseEntity<String> deleteById(@PathVariable long custId){
        customerService.deleteById(custId);
        return new ResponseEntity<>("Data Deleted Successfully. ", HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll(){
        customerService.deleteAll();
        return new ResponseEntity<>("All Data Deleted Successfully." , HttpStatus.OK);
    }
}
