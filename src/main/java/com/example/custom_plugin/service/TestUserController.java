package com.example.custom_plugin.service;

import com.example.custom_plugin.model.TestUser;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a generated interface for demonstration purposes.
 */
@RestController
@RequestMapping("/testuser")
public class TestUserController {
    /** This is an example service. */
    @Resource
    private TestUserServiceImpl service;

    public TestUserController(TestUserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return ApiResponse.success(service.all());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TestUser newTestUser) {
        return ApiResponse.success(service.create(newTestUser));
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return ApiResponse.success(service.one(id));
    }

    @PutMapping("/replaceTestUser/{id}")
    public ResponseEntity<?> replaceTestUser(@RequestBody TestUser newTestUser, @PathVariable Long id) {
        return ApiResponse.success(service.replaceTestUser(newTestUser,id));
    }

    @DeleteMapping("/deleteTestUser/{id}")
    public ResponseEntity<?> deleteTestUser(@PathVariable Long id) {
        return ApiResponse.success(service.deleteTestUser(id));
    }
}