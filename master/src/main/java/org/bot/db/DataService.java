package org.bot.db;

import org.bot.entites.DataEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DataService {

    private final DataRepository repository;

    public DataService(DataRepository repository) {
        this.repository = repository;
    }

    public void save(DataEntity data) {
        repository.save(data);
    }

    public Optional<DataEntity> get(UUID id) {
        return repository.findById(id);
    }
}
