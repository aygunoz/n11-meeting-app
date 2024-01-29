package com.example.n11meetingapp.entity;

import com.example.n11meetingapp.dto.requestDTO.TalkRequestDTO;
import com.example.n11meetingapp.entity.BaseEntity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Talk extends AbstractEntity {
    private String title;
    private int duration; // minute
    private LocalTime startTime;
}
