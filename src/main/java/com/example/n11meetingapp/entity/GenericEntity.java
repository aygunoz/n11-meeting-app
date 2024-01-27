package com.example.n11meetingapp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface GenericEntity<E> extends Serializable {
    Long getId();

    void setId(Long id);

    Long getVersion();

    void setVersion(Long version);

    LocalDateTime getCreateDate();

    void setCreateDate(LocalDateTime createDate);

}
