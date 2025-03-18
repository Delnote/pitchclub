package org.bot.controllers;

import org.bot.db.DataService;
import org.bot.entites.DataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DataController {

    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveData(@RequestBody DataEntity payload) {
        payload.setIsActivated(false);
        payload.setJoinDate(Instant.now());
        payload.setUpdateDate(Instant.now());
        dataService.save(payload);
        return ResponseEntity.ok("Data saved successfully");
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

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
