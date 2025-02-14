package com.example.custom_plugin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is a generated Service for demonstration purposes.
 */
@Service
public class UsersServiceImpl extends UsersBaseService implements UsersService {
    /** This is an example repository. */
    private UsersRepository repository;

    /** This is an example modelAssembler. */
    private UsersModelAssembler assembler;

    public UsersServiceImpl() {
        super();
    }

    @Autowired
    public UsersServiceImpl(UsersRepository repository, UsersModelAssembler assembler) {
        super(repository,assembler);
        this.repository = repository;
        this.assembler = assembler;
    }
}