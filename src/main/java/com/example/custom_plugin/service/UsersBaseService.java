package com.example.custom_plugin.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.custom_plugin.model.atest.Users;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * This is a generated BaseService for demonstration purposes.
 */
@Service
public class UsersBaseService {
    /** This is an example repository. */
    private UsersRepository repository;

    /** This is an example modelAssembler. */
    private UsersModelAssembler assembler;

    public UsersBaseService() {
    }

    @Autowired
    public UsersBaseService(UsersRepository repository, UsersModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public CustomPageImpl<Users> all(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Users> usersPage = repository.findAll(pageable).toList();
        return new CustomPageImpl<Users>(usersPage, pageable, (long)(usersPage.size()));
    }

    public Users create(@RequestBody Users newUsers) {
        return repository.save(newUsers);
    }

    public Users one(@PathVariable Long id) {
        Users users = repository.findById(id)
        .orElseThrow(() -> new UsersNotFoundException(id));
        return users;
    }

    public Users replaceUsers(@RequestBody Users newUsers, @PathVariable Long id) {
        Users _updateModel = repository.findById(id)
        .map(_newUsers -> {
            //_newTestUser.setId(newTestUser.getId());
            return repository.save(_newUsers);
        })
        .orElseThrow(() -> new UsersNotFoundException(id));
        EntityModel<Users> entityModel = assembler.toModel(_updateModel);
        return _updateModel;
    }

    public Boolean deleteUsers(@PathVariable Long id) {
        repository.deleteById(id);
        return true;
    }
}