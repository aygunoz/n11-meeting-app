package com.example.n11meetingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Talk extends AbstractEntity{
    private String title;
    private int duration; // minute
    private LocalTime startTime;
    @ManyToOne
    private Conference conference;
}
