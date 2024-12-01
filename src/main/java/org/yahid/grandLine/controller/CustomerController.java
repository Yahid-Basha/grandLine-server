package org.yahid.grandLine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import org.yahid.grandLine.model.Customer;
import org.yahid.grandLine.model.CustomerInfo;
import org.yahid.grandLine.repository.CustomerRepository;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@RestController
@RequestMapping("/customer/auth")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final HandlerMapping resourceHandlerMapping;
    public CustomerController(CustomerRepository customerRepository, HandlerMapping resourceHandlerMapping) {
        this.customerRepository = customerRepository;
        this.resourceHandlerMapping = resourceHandlerMapping;
    }

    record CustomerSignUpPayload(
            @NotEmpty(message = "Name is required")
            String name,
            @NotEmpty(message = "Email is required")
            @Email
            String email,
            @NotEmpty(message = "password is required")
            String password
    ) {}

    record CustomerLoginPayload(
            @NotEmpty(message = "Email is reqired")
            @Email
            String email,
            @NotEmpty(message = "password is required")
            String password
    ){}

    @PostMapping("/signup")
    ResponseEntity<Customer> customerSignup(@Valid @RequestBody CustomerController.CustomerSignUpPayload payload){
        var customer = new Customer();
        customer.setUserName(payload.name);
        customer.setEmail(payload.email);
        customer.setPassword(payload.password);
        var signedUpCustomer = customerRepository.save(customer);

        signedUpCustomer.setPassword(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(signedUpCustomer);
    }

    @PostMapping("/login")
    ResponseEntity<String> customerLogin(@Valid @RequestBody CustomerController.CustomerLoginPayload payload){
        System.out.println(payload);

        var customer = customerRepository.getCustomersByEmail(payload.email);
        if(customer.isPresent() && payload.password.equals(customer.get().getPassword())){
              System.out.println("-================User Found==============");
            try {
                return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(customer.get()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("-================Not Found==============");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ 'message' : 'email or password is incorrect'}");
    }
}
