package com.example.n11meetingapp.controller;

import com.example.n11meetingapp.dto.requestDTO.ConferenceRequestDTO;
import com.example.n11meetingapp.dto.responseDTO.ConferenceResponseDTO;
import com.example.n11meetingapp.entity.Conference;
import com.example.n11meetingapp.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conference")
@RequiredArgsConstructor
public class ConferenceController extends AbstractController<Conference, ConferenceRequestDTO, ConferenceResponseDTO>{
    private final ConferenceService conferenceService;
    @GetMapping("/findAll")
    public ResponseEntity<List<ConferenceResponseDTO>> findAll() {
        List<ConferenceResponseDTO> conferenceResponseDTOS = mapper.convertListToType(conferenceService.findAll(), this.abstractService.getSourceTypeResponse());
        return new ResponseEntity<>(conferenceService.setStartDate(conferenceResponseDTOS, true), HttpStatus.CREATED);
    }
}
