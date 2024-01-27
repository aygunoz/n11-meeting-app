package com.example.n11meetingapp.dto.responseDTO;

import com.example.n11meetingapp.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TalkResponseDTO extends AbstractDTO {
    private String title;
    private int duration; // minute
}
