package com.emelyan.services;

import com.emelyan.models.Interval;
import com.emelyan.repositories.IntervalRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IntervalService {
    private final IntervalRepository receptionIntervalRepository;

    public IntervalService(IntervalRepository receptionIntervalRepository) {
        this.receptionIntervalRepository = receptionIntervalRepository;
    }

    public List<String> receptionIntervalList(){
        return receptionIntervalRepository.findInterval();
    }

    public List<Interval> getAll(){
        return receptionIntervalRepository.findAll();
    }
}
