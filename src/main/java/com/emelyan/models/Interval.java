package com.emelyan.models;

import lombok.*;
import javax.persistence.*;
import java.sql.Time;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "interval")
public class Interval {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "end_time")
    private Time startTime;

    @Column(name = "start_time")
    private Time endTime;
}
