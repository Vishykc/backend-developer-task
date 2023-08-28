package com.github.backenddevelopertask.controller;

import com.github.backenddevelopertask.model.Computer;
import com.github.backenddevelopertask.model.Customer;
import com.github.backenddevelopertask.repo.ComputerRepo;
import com.github.backenddevelopertask.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ComputerRepo computerRepo;

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {

        try {
            List<Customer> customerList = new ArrayList<>();
            customerRepo.findAll().forEach(customerList::add);

            if(customerList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customerList, HttpStatus.OK);

        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/getAllCustomersByFirstName/{firstName}")
    public ResponseEntity<List<Customer>> getAllCustomersByFirstName(@PathVariable String firstName) {

        try {
            List<Customer> customerList = new ArrayList<>();
            customerRepo.findAll().forEach(customer -> {
                        if (customer.getFirstName().equals(firstName)) {
                            customerList.add(customer);
                        }
                    }
            );

            if (customerList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customerList, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
        @GetMapping("/getAllCustomersByLastName/{lastName}")
        public ResponseEntity<List<Customer>> getAllCustomersByLastName(@PathVariable String lastName) {

            try {
                List<Customer> customerList = new ArrayList<>();
                customerRepo.findAll().forEach(customer -> {
                            if (customer.getLastName().equals(lastName)) {
                                customerList.add(customer);
                            }
                        }
                );

                if(customerList.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(customerList, HttpStatus.OK);

            } catch(Exception ex) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

            }

    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customerData = customerRepo.findById(id);

        if (customerData.isPresent()) {
            return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getComputersByCustomerId/{id}")
    public ResponseEntity<List<Computer>> getComputersByCustomerId(@PathVariable Long id) {
        Optional<Customer> customerData = customerRepo.findById(id);

        if (customerData.isPresent()) {
            List<Computer> computerList = new ArrayList<>(customerData.get().getComputers());
            return new ResponseEntity<>(computerList, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer customerObj = customerRepo.save(customer);

        return new ResponseEntity<>(customerObj, HttpStatus.OK);

    }

    @PostMapping("/updateCustomerById/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer newCustomerData) {
        Optional<Customer> oldCustomerData = customerRepo.findById(id);

        if(oldCustomerData.isPresent()) {
            Customer updatedCustomerData = oldCustomerData.get();
            updatedCustomerData.setFirstName(newCustomerData.getFirstName());
            updatedCustomerData.setLastName(newCustomerData.getLastName());
            updatedCustomerData.setEmail(newCustomerData.getEmail());
            updatedCustomerData.setAddress(newCustomerData.getAddress());
            updatedCustomerData.setComputers(newCustomerData.getComputers());

            Customer customerObj = customerRepo.save(updatedCustomerData);
            return new ResponseEntity<>(customerObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
