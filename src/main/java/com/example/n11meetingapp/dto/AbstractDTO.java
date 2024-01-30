package com.example.n11meetingapp.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractDTO implements GenericDTO{
    private Long id;
    private Long version = 0L;

}
