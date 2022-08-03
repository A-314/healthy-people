package com.emelyan.services;

import com.emelyan.controllers.RecordsController;
import com.emelyan.models.Interval;
import com.emelyan.models.Record;
import com.emelyan.repositories.IntervalRepository;
import com.emelyan.repositories.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.emelyan.util.DataTimeUtil.MILLISECONDS_IN_DAY;

@Service
@RequiredArgsConstructor
public class RecordService {

    protected static final Logger LOG = LoggerFactory.getLogger(RecordsController.class);

    private final IntervalRepository intervalRepository;
    private final RecordRepository repository;

    private List<Interval> receptionIntervals;
    private List<Record> records;

    private ArrayList<Record> weekSchedule;
    private ArrayList<ArrayList<Record>> scheduleTable;

    private Timestamp startPeriod;
    private Timestamp endPeriod;

    public ArrayList<ArrayList<Record>> getSchedule(Long startPeriodMillis) {

        receptionIntervals = intervalRepository.findAll();
        scheduleTable = new ArrayList<>();

        startPeriod = new Timestamp(startPeriodMillis);
        endPeriod   = new Timestamp(startPeriodMillis + 7 * MILLISECONDS_IN_DAY);

        records = repository.findAllByReception_dateIsBetween(startPeriod, endPeriod);

        LOG.info("Schedule of records. On "+ startPeriod +" to "+ endPeriod);
        LOG.info("All records patients on period: "+ records.size());

        //Grouping data to week
        for (int rowNumber = 0,currentRecord=0; rowNumber < receptionIntervals.size()-1; rowNumber++) {
            weekSchedule = new ArrayList<>();
            long dayOfWeek = startPeriodMillis;

            for (int j = 0; j < 7; j++) {

                if(records.size() > currentRecord){

                    if(records.get(currentRecord).getDate().getTime() == dayOfWeek &&
                       records.get(currentRecord).getInterval() == receptionIntervals.get(rowNumber)){

                          weekSchedule.add(records.get(currentRecord));
                          currentRecord++;
                        }else{weekSchedule.add(null);}

                }else{weekSchedule.add(null);}

                dayOfWeek = dayOfWeek + MILLISECONDS_IN_DAY;
            }
            LOG.info(weekSchedule.toString());
            scheduleTable.add(weekSchedule);
        }

        return scheduleTable;
    }

    public void save(Record record){repository.save(record);}

    public void update(Record updateRecord){repository.save(updateRecord);}


}
