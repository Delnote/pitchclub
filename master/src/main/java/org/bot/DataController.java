package org.bot;

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
        dataService.save(payload);
        return ResponseEntity.ok("Data saved successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<String> getData(@RequestParam(name = "key") String key) {
        return dataService.get(key)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Key not found"));
    }
}
