package org.yahid.grandLine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yahid.grandLine.model.Business;
import org.yahid.grandLine.repository.BusinessRepository;
import org.yahid.grandLine.repository.CategoryRepository;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@RestController
@RequestMapping("/business")
public class BusinessController {
    private final CategoryRepository categoryRepository;
    private final BusinessRepository businessRepository;

    public BusinessController(CategoryRepository categoryRepository, BusinessRepository businessRepository) {
        this.categoryRepository = categoryRepository;
        this.businessRepository = businessRepository;
    }

    record BusinessSignupPayload(
            @NotEmpty(message = "Owner_name is required")
            String name,
            @NotEmpty(message = "Email is required")
            @Email
            String email,
            @NotEmpty(message = "password is required")
            String password,
            @NotEmpty(message = "Business Name is required")
            String businessName,
            String businessCategory
    ){}

    record BusinessLoginPayload(
            @NotEmpty(message = "Email is required")
            String email,
            @NotEmpty
            String password
    ){}

    @PostMapping("/signup")
    ResponseEntity<String> signupBusiness(@Valid @RequestBody BusinessSignupPayload payload){
        var categoryObj = categoryRepository.getCategoryByName(payload.businessCategory);
        var business = new Business();
        business.setOwnerName(payload.name);
        business.setEmail(payload.email);
        business.setPassword(payload.password);
        business.setBusinessName(payload.businessName);
        business.setCreatedAt(Instant.now());
        business.setUpdatedAt(Instant.now());
        if(payload.businessCategory != null && categoryObj != null) business.setCategory(categoryObj);
        var signedUpBusiness = businessRepository.save(business);
        business.setCategory(null);
        try {
            System.out.println(new ObjectMapper().writeValueAsString(business));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("testing..");
    }

    @PostMapping("/login")
    ResponseEntity<String> loginBusiness(@Valid @RequestBody BusinessLoginPayload payload){
        var business = businessRepository.getBusinessesByEmail(payload.email);
        if(business.isPresent() && business.get().getPassword().equals(payload.password)){
            try {
                return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(business.get()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'message':'email or password is incoreect'}");

    }
}
