package com.nexio.ecommerce.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.OffsetDateTime;

@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private final long id;

    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    @NotNull
    @Getter
    private OffsetDateTime creationDate;

    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    @NotNull
    @Getter
    private OffsetDateTime lastUpdateDate;

    protected AbstractBaseEntity(){
        id = 0;
    }

    public AbstractBaseEntity(long id){
        this.id = id;
        creationDate = OffsetDateTime.now();
        lastUpdateDate = OffsetDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        OffsetDateTime currentDate = OffsetDateTime.now();
        creationDate = currentDate;
        lastUpdateDate = currentDate;
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdateDate = OffsetDateTime.now();
    }
}
