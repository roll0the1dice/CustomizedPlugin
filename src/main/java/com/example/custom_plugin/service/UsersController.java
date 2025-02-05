package com.example.custom_plugin.service;

import com.example.custom_plugin.model.Users;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a generated interface for demonstration purposes.
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    /** This is an example service. */
    @Resource
    private UsersServiceImpl service;

    public UsersController(UsersServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<?> all(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return ApiResponse.success(service.all(page,size));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Users newUsers) {
        return ApiResponse.success(service.create(newUsers));
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return ApiResponse.success(service.one(id));
    }

    @PutMapping("/replaceUsers/{id}")
    public ResponseEntity<?> replaceUsers(@RequestBody Users newUsers, @PathVariable Long id) {
        return ApiResponse.success(service.replaceUsers(newUsers,id));
    }

    @DeleteMapping("/deleteUsers/{id}")
    @AuthCheck(mustRole = UserRoleEnum.ADMIN)
    public ResponseEntity<?> deleteUsers(@PathVariable Long id) {
        return ApiResponse.success(service.deleteUsers(id));
    }
}