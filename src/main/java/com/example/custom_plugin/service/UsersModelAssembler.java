package com.example.custom_plugin.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.custom_plugin.model.atest.Users;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * This is a generated NotFoundException for demonstration purposes.
 */
@Component
public class UsersModelAssembler implements RepresentationModelAssembler<Users, EntityModel<Users>> {
    @Override
    public EntityModel<Users> toModel(@NonNull Users users) {
        return EntityModel.of(users,
        linkTo(methodOn(UsersController.class).one(users.getId())).withSelfRel(),
        linkTo(methodOn(UsersController.class).all(0, 10)).withRel("users"));
    }
}