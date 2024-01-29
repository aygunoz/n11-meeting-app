package com.example.n11meetingapp.dto.requestDTO;

import com.example.n11meetingapp.dto.AbstractDTO;
import com.example.n11meetingapp.entity.Talk;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ConferenceRequestDTO extends AbstractDTO {
    private Boolean isFull;
    private List<Talk> allTalks;
}
