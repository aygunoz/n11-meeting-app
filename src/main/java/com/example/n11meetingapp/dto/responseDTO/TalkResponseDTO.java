package com.example.n11meetingapp.dto.responseDTO;

import com.example.n11meetingapp.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalkResponseDTO extends AbstractDTO {
    private String title;
    private int duration; // minute
}
