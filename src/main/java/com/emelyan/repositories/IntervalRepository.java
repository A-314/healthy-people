package com.emelyan.repositories;

import com.emelyan.models.Interval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IntervalRepository extends JpaRepository<Interval,Integer> {

    @Query("select concat(function('to_char', i.startTime, 'HH24:MI'),'-',function('to_char', i.endTime, 'HH24:MI'))  from Interval i")
    List<String> findInterval();

    List<Interval> findAll();
}
