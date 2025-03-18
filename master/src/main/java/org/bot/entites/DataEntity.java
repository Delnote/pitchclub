package org.bot.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "data_store")
public class DataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String fio;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String telegram;

    @Column(nullable = false)
    private String about;

    @Column(nullable = false)
    private String vision;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isActivated;

    @Column(nullable = false)
    private Instant updateDate;

    @Column(nullable = false)
    private Instant joinDate;
}