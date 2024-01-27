package com.example.n11meetingapp.service;

import com.example.n11meetingapp.dto.requestDTO.TalkRequestDTO;
import com.example.n11meetingapp.dto.responseDTO.TalkResponseDTO;
import com.example.n11meetingapp.entity.Talk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TalkService extends AbstractService<Talk, TalkRequestDTO, TalkResponseDTO>{
}
