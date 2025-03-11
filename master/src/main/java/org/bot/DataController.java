package org.bot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveData(@RequestParam String key, @RequestParam String value) {
        dataService.save(key, value);
        return ResponseEntity.ok("Saved successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<String> getData(@RequestParam String key) {
        return dataService.get(key)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Key not found"));
    }
}
