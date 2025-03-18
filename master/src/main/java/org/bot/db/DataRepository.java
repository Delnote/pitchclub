package org.bot.db;

import org.bot.entites.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Long> {
    Optional<DataEntity> findById(UUID id);
    Optional<DataEntity> findByEmail(String email);
}
