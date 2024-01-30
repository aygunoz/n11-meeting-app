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
            if (scheduleTaskTime(availableConferences.get(i), talk)) {
                break;
            }
            if (i == availableConferences.size() - 1) {
                Conference newConference = new Conference();
                this.repository.saveAndFlush(newConference);
                scheduleTaskTime(newConference, talk);
            }
        }
    }

    private boolean scheduleTaskTime(Conference conference, Talk talk) {
        // Morning talk's Durations
        int totalDurationMorning = getTotalTalkDuration(conference.getMorningTalks());
        // Afternoon talk's Durations
        int totalDurationAfternoon = getTotalTalkDuration(conference.getAfternoonTalks());

        if (checkMorningAvailability(talk, totalDurationMorning)) {
            conference.getMorningTalks().add(talk);
            this.conferenceRepository.save(conference);
            return true;
        } else if (checkAfternoonAvailability(talk, totalDurationAfternoon)) {
            conference.getAfternoonTalks().add(talk);
            this.conferenceRepository.save(conference);
            return true;
        } else {
            if (totalDurationMorning + totalDurationAfternoon >= 420) {
                conference.setIsFull(true);
                this.conferenceRepository.save(conference);
            }
            return false;
        }
    }

    public List<ConferenceResponseDTO> setStartDate(List<ConferenceResponseDTO> conferences, Boolean networkingEventScheduled) {
        for (ConferenceResponseDTO conference : conferences) {
            LocalTime startMorningTime = LocalTime.of(9, 0);
            addTalkTimes(conference.getMorningTalks(), startMorningTime, "morning", networkingEventScheduled);
            LocalTime afterNoonMorningTime = LocalTime.of(1, 0);
            addTalkTimes(conference.getAfternoonTalks(), afterNoonMorningTime, "afternoon", networkingEventScheduled);
        }
        return conferences;
    }

    public void addTalkTimes(List<Talk> talks, LocalTime startTime, String whichMeeting, Boolean networkingEventScheduled) {
        for (Talk talk : talks) {
            talk.setStartTime(startTime);
            startTime = startTime.plusMinutes(talk.getDuration());
        }
        if (whichMeeting.equals("morning")) {
            talks.add(new Talk("Lunch", 60,LocalTime.of(12, 0)));
        }
        if (whichMeeting.equals("afternoon") && networkingEventScheduled && getTotalTalkDuration(talks) <= 240) {
            LocalTime networkingEventTime = startTime.isBefore(LocalTime.of(4, 0)) ? LocalTime.of(4, 0) : startTime;
            talks.add(new Talk("Networking Event", 60, networkingEventTime));
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

    public static int getTotalTalkDuration(List<Talk> talkList) {
        return talkList.stream()
                .mapToInt(Talk::getDuration)
                .sum();
    }


}
