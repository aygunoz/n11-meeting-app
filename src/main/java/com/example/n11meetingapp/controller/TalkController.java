package com.example.n11meetingapp.controller;

import com.example.n11meetingapp.dto.requestDTO.TalkRequestDTO;
import com.example.n11meetingapp.dto.responseDTO.TalkResponseDTO;
import com.example.n11meetingapp.entity.Talk;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/talk")
public class TalkController extends AbstractController<Talk, TalkRequestDTO, TalkResponseDTO>{
}
