package com.example.custom_plugin.service;

import com.example.buddy_match.model.Team;
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
@RequestMapping("/team")
public class TeamController {
    /** This is an example service. */
    @Resource
    private TeamServiceImpl service;

    public TeamController(TeamServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Team>> all() {
        return service.all();
    }

    @PostMapping("/create")
    public Team create(@RequestBody Team newTeam) {
        return service.create(newTeam);
    }

    @GetMapping("/one/{id}")
    public EntityModel<Team> one(@PathVariable Long id) {
        return service.one(id);
    }

    @PutMapping("/replaceTeam/{id}")
    public ResponseEntity<?> replaceTeam(@RequestBody Team newTeam, @PathVariable Long id) {
        return service.replaceTeam(newTeam,id);
    }

    @DeleteMapping("/deleteTeam/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        return service.deleteTeam(id);
    }
}