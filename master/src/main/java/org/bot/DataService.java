package org.bot;

import org.bot.entites.DataEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataService {

    private final DataRepository repository;

    public DataService(DataRepository repository) {
        this.repository = repository;
    }

    public void save(DataEntity data) {
        repository.save(data);
    }

    public Optional<String> get(String key) {
        return repository.findByKey(key).map(DataEntity::getValue);
    }
}
