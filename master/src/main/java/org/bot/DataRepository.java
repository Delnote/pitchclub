package org.bot;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DataRepository {

    private final Map<String, String> storage = new ConcurrentHashMap<>();

    public void save(String key, String value) {
        storage.put(key, value);
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(storage.get(key));
    }
}
