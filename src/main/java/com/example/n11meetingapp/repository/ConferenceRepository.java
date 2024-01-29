package com.example.n11meetingapp.repository;

import com.example.n11meetingapp.entity.Conference;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConferenceRepository extends GenericRepository<Conference>{
    Optional<List<Conference>> findConferenceByIsFull(Boolean isFull);
}
