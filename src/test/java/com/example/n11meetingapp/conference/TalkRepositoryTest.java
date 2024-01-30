package com.example.n11meetingapp.conference;

import com.example.n11meetingapp.common.Mapper;
import com.example.n11meetingapp.dto.responseDTO.TalkResponseDTO;
import com.example.n11meetingapp.entity.Talk;
import com.example.n11meetingapp.repository.TalkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TalkRepositoryTest {

    @Autowired
    private TalkRepository talkRepository;

    @Test
    public void testCreateTalk() {
        Mapper mapper = new Mapper();

        // Create a Talk object to return when saveAndFlush is called
        Talk savedTalk = new Talk("Architecting Your Codebase", 60, null);
        savedTalk = this.talkRepository.saveAndFlush(savedTalk);
        // Assuming getTestTalks() creates a TalkRequestDTO with the same values
        TalkResponseDTO expectedTalkResponseDTO = new TalkResponseDTO("Architecting Your Codebase", 60);

        // Convert the Talk object back to TalkResponseDTO for comparison
        TalkResponseDTO actualTalkResponseDTO = (TalkResponseDTO) mapper.convertToType(savedTalk, TalkResponseDTO.class);

        // Assert that the converted TalkResponseDTO is equal to the expected one
        Assertions.assertThat(actualTalkResponseDTO).isEqualToComparingFieldByField(expectedTalkResponseDTO);
    }

    public static List<Talk> getTestTalks() {
        List<Talk> talkList = new ArrayList<>();
        talkList.add(new Talk("Architecting Your Codebase", 60, null));
        talkList.add(new Talk("Overdoing it in Python", 30, null));
        talkList.add(new Talk("Flavors of Concurrency in Java", 30, null));
        talkList.add(new Talk("Ruby Errors from Mismatched Gem Versions", 45, null));
        talkList.add(new Talk("JUnit 5 - Shaping the Future of Testing on the JVM", 45, null));
        talkList.add(new Talk("Cloud Native Java lightning", 60, null));
        talkList.add(new Talk("Communicating Over Distance", 30, null));
        talkList.add(new Talk("AWS Technical Essentials", 45, null));
        talkList.add(new Talk("Continuous Delivery 30min", 30, null));
        talkList.add(new Talk("Monitoring Reactive Applications", 30, null));
        talkList.add(new Talk("Pair Programming vs Noise", 45, null));
        talkList.add(new Talk("Rails Magic", 60, null));
        talkList.add(new Talk("Microservices \"Just Right\"", 60, null));
        talkList.add(new Talk("Clojure Ate Scala (on my project)", 45, null));
        talkList.add(new Talk("Perfect Scalability", 30, null));
        talkList.add(new Talk("Apache Spark", 30, null));
        talkList.add(new Talk("Async Testing on JVM", 60, null));
        talkList.add(new Talk("A World Without HackerNews", 30, null));
        talkList.add(new Talk("User Interface CSS in Apps", 30, null));
        return talkList;
    }
}
