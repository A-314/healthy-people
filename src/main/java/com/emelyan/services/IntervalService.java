package com.emelyan.services;

import com.emelyan.models.Interval;
import com.emelyan.repositories.IntervalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IntervalService {

    private final IntervalRepository receptionIntervalRepository;

    public List<String> receptionIntervalList(){
        return receptionIntervalRepository.findInterval();
    }

    public List<Interval> getAll(){
        return receptionIntervalRepository.findAll();
    }
}
