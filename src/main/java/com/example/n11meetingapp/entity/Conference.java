package com.example.n11meetingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Conference extends AbstractEntity {
    @OneToMany(mappedBy = "conference")
    private List<Talk> allTalks;
}
