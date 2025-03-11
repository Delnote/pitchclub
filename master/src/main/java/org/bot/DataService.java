package org.bot;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataService {

    private final DataRepository repository;

    public DataService(DataRepository repository) {
        this.repository = repository;
    }

    public void save(String key, String value) {
        repository.save(key, value);
    }

    public Optional<String> get(String key) {
        return repository.get(key);
    }
}
