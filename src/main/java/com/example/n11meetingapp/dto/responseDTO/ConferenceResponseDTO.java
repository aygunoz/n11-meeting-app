package com.example.n11meetingapp.dto.responseDTO;

import com.example.n11meetingapp.dto.AbstractDTO;
import com.example.n11meetingapp.entity.Talk;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ConferenceResponseDTO extends AbstractDTO {
    private Boolean isFull;
    private List<Talk> morningTalks;
    private List<Talk> afternoonTalks;
}
