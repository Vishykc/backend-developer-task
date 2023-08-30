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

    // Retrieve all customers
    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {

        try {
            // Create a list to store customer data
            List<Customer> customerList = new ArrayList<>();
            // Fetch all customers from the repository and add to the list
            customerRepo.findAll().forEach(customerList::add);

            if(customerList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customerList, HttpStatus.OK);

        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    // Retrieve customers by first name
    @GetMapping("/getAllCustomersByFirstName/{firstName}")
    public ResponseEntity<List<Customer>> getAllCustomersByFirstName(@PathVariable String firstName) {

        try {
            List<Customer> customerList = new ArrayList<>();
            // Fetch all customers and filter by matching first name
            // add them to customerList
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

        // Retrieve customers by last name
        @GetMapping("/getAllCustomersByLastName/{lastName}")
        public ResponseEntity<List<Customer>> getAllCustomersByLastName(@PathVariable String lastName) {

            try {
                List<Customer> customerList = new ArrayList<>();
                // Fetch all customers and filter by matching last name
                //then add them to customersList
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

    // Retrieve customer by ID
    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customerData = customerRepo.findById(id);

        if (customerData.isPresent()) {
            return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Retrieve computers associated with a customer by ID
    @GetMapping("/getComputersByCustomerId/{id}")
    public ResponseEntity<List<Computer>> getComputersByCustomerId(@PathVariable Long id) {
        Optional<Customer> customerData = customerRepo.findById(id);

        if (customerData.isPresent()) {
            // Get the computer list from the customer and return it
            List<Computer> computerList = new ArrayList<>(customerData.get().getComputers());
            return new ResponseEntity<>(computerList, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add a new customer
    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer customerObj = customerRepo.save(customer);

        return new ResponseEntity<>(customerObj, HttpStatus.OK);
    }

    // Update customer by ID
    @PostMapping("/updateCustomerById/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer newCustomerData) {
        Optional<Customer> oldCustomerDataOptional = customerRepo.findById(id);

        if (oldCustomerDataOptional.isPresent()) {
            Customer oldCustomerData = oldCustomerDataOptional.get();
            List<Computer> newComputers = newCustomerData.getComputers();

            // Update customer attributes
            oldCustomerData.setFirstName(newCustomerData.getFirstName());
            oldCustomerData.setLastName(newCustomerData.getLastName());
            oldCustomerData.setEmail(newCustomerData.getEmail());
            oldCustomerData.setAddress(newCustomerData.getAddress());

            // Clear the old computers and assign the new ones
            oldCustomerData.getComputers().clear();
            oldCustomerData.getComputers().addAll(newComputers);

            // Save the updated customer
            Customer updatedCustomer = customerRepo.save(oldCustomerData);

            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}