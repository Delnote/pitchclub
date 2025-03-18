package org.bot.controllers;

import org.bot.db.DataService;
import org.bot.entites.DataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api")
public class DataController {

    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getData(@RequestParam(name = "id") UUID id) {
        Optional<DataEntity> entity = dataService.get(id);
        if (entity.isPresent()) {
            DataEntity dataEntity = entity.get();
            dataEntity.setPassword(null);
            dataEntity.setIsActivated(null);
            dataEntity.setJoinDate(null);
            return ResponseEntity.ok(dataEntity);
        }
        return ResponseEntity.status(NOT_FOUND).body("User not found");
    }
}
