package com.example.n11meetingapp.conference;

import com.example.n11meetingapp.entity.Talk;
import com.example.n11meetingapp.repository.ConferenceRepository;
import com.example.n11meetingapp.repository.TalkRepository;
import com.example.n11meetingapp.service.ConferenceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ConferenceServiceTest {
    @InjectMocks
    private ConferenceService conferenceService;
    @Mock
    private ConferenceRepository conferenceRepository;

    @Mock
    private TalkRepository talkRepository;

    @Test
    void testAddTalkTimes() {

        // Morning talks
        List<Talk> morningTalks = TalkRepositoryTest.getTestTalks().subList(0, 3);
        conferenceService.addTalkTimes(morningTalks, LocalTime.of(9, 0), "morning", false);
        // Afternoon talks
        List<Talk> afternoonTalks = TalkRepositoryTest.getTestTalks().subList(3, 7);
        conferenceService.addTalkTimes(afternoonTalks, LocalTime.of(13, 0), "afternoon", true);

        // Check lunch
        List<Talk> allTalks = new ArrayList<>(morningTalks);
        allTalks.addAll(afternoonTalks);
        assertTrue(ConferenceService.getTotalTalkDuration(allTalks) <= 420, "Toplam konuşma süresi 420'den küçük veya eşit olmalı");

        // Check networking event
        boolean networkingEventAdded = false;
        for (Talk talk : allTalks) {
            if ("Networking Event".equals(talk.getTitle())) {
                networkingEventAdded = true;
                assertTrue(talk.getStartTime().isAfter(LocalTime.of(16, 0))
                        || talk.getStartTime().equals(LocalTime.of(16, 0)));
            }
        }
        assertTrue(networkingEventAdded, "Networking Event not added");
    }

}
