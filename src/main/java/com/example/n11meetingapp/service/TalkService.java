package com.example.n11meetingapp.service;

import com.example.n11meetingapp.dto.requestDTO.TalkRequestDTO;
import com.example.n11meetingapp.dto.responseDTO.TalkResponseDTO;
import com.example.n11meetingapp.entity.Conference;
import com.example.n11meetingapp.entity.Talk;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TalkService extends AbstractService<Talk, TalkRequestDTO, TalkResponseDTO>{
    private final ConferenceService conferenceService;

    @Override
    public Talk create(TalkRequestDTO requestDTO) throws Exception {
        Talk createdTalk = super.create(requestDTO);
        this.conferenceService.scheduleTaskInConference(createdTalk);
        return createdTalk;
    }
}
