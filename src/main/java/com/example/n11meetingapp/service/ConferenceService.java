package com.example.n11meetingapp.service;

import com.example.n11meetingapp.dto.requestDTO.ConferenceRequestDTO;
import com.example.n11meetingapp.dto.responseDTO.ConferenceResponseDTO;
import com.example.n11meetingapp.entity.Conference;
import com.example.n11meetingapp.entity.Talk;
import com.example.n11meetingapp.repository.ConferenceRepository;
import com.example.n11meetingapp.repository.TalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConferenceService extends AbstractService<Conference, ConferenceRequestDTO, ConferenceResponseDTO> {
    private final ConferenceRepository conferenceRepository;
    private final TalkRepository talkRepository;

    public void scheduleTaskInConference(Talk talk) {
        boolean networkingEventScheduled = true;

        List<Conference> availableConferences = this.conferenceRepository.findConferenceByIsFull(false)
                .map(conferences -> {
                    if (conferences.isEmpty()) {
                        // Eğer sorgu sonucunda boş liste döndüyse, yeni bir konferans oluşturup kaydet
                        Conference newConference = new Conference();
                        this.repository.saveAndFlush(newConference);
                        return List.of(newConference);
                    } else {
                        // Eğer sorgu sonucunda bir konferans bulunduysa, onu kullan
                        return conferences;
                    }
                })
                .orElseGet(() -> {
                    // Eğer sorgu sonucunda hiç konferans bulunamadıysa, yeni bir konferans oluşturup kaydet
                    Conference newConference = new Conference();
                    this.repository.saveAndFlush(newConference);
                    return List.of(newConference);
                });

        for (int i = 0; i < availableConferences.size(); i++) {
            if (scheduleTaskTime(availableConferences.get(i), talk, networkingEventScheduled)) {
                break;
            }
            if (i == availableConferences.size() - 1) {
                Conference newConference = new Conference();
                this.repository.saveAndFlush(newConference);
                scheduleTaskTime(newConference, talk, networkingEventScheduled);
            }
        }
    }

    private boolean scheduleTaskTime(Conference conference, Talk talk, boolean networkingEventScheduled) {
        // Morning talk's Durations
        int totalDurationMorning = getTotalMorningTalkDuration(conference.getMorningTalks());
        // Afternoon talk's Durations
        int totalDurationAfternoon = getTotalMorningTalkDuration(conference.getAfternoonTalks());

        if (checkMorningAvailability(talk, totalDurationMorning)) {
            conference.getMorningTalks().add(talk);
            this.conferenceRepository.save(conference);
            return true;
        } else if (checkAfternoonAvailability(talk, totalDurationAfternoon)) {
            conference.getAfternoonTalks().add(talk);
            this.conferenceRepository.save(conference);
            return true;
        } else {
            if (totalDurationMorning + totalDurationAfternoon >= 360) {
                conference.setIsFull(true);
                this.conferenceRepository.save(conference);
            }
            return false;
        }
    }

    public List<ConferenceResponseDTO> setStartDate(List<ConferenceResponseDTO> conferences) {
        for (ConferenceResponseDTO conference : conferences) {
            LocalTime startMorningTime = LocalTime.of(9, 0);
            addTalkTimes(conference.getMorningTalks(), startMorningTime);
            LocalTime afterNoonMorningTime = LocalTime.of(1, 0);
            addTalkTimes(conference.getAfternoonTalks(), afterNoonMorningTime);
        }
        return conferences;
    }

    private static void addTalkTimes(List<Talk> talks, LocalTime startTime) {
        for (Talk talk : talks) {
            talk.setStartTime(startTime);
            startTime = startTime.plusMinutes(talk.getDuration());
        }
    }

    public List<Conference> findAll() {
        return repository.findAll();
    }

    public boolean checkMorningAvailability(Talk talk, int totalDurationMorning) {
        return talk.getDuration() + totalDurationMorning <= 60 * 3;
    }

    public boolean checkAfternoonAvailability(Talk talk, int totalDurationAfternoon) {
        return talk.getDuration() + totalDurationAfternoon <= 60 * 4;
    }

    public int getTotalMorningTalkDuration(List<Talk> talkList) {
        return talkList.stream()
                .mapToInt(Talk::getDuration)
                .sum();
    }


}
