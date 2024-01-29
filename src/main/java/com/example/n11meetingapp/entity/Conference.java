package com.example.n11meetingapp.entity;

import com.example.n11meetingapp.entity.BaseEntity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Conference extends AbstractEntity {
    @OneToMany(fetch = FetchType.LAZY)
    private List<Talk> morningTalks = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY)
    private List<Talk> afternoonTalks = new ArrayList<>();
    private Boolean isFull = false;
}
