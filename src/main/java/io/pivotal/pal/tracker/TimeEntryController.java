package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TimeEntryController {

    private TimeEntryRepository repo;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repo = timeEntryRepository;
    }

    public ResponseEntity create(TimeEntry timeEntryToCreate) {
        TimeEntry newTimeEntry = repo.create(timeEntryToCreate);
        return new ResponseEntity(newTimeEntry, HttpStatus.CREATED);
    }

    public ResponseEntity<TimeEntry> read(long timeEntryId) {
        TimeEntry currentTimeEntry = repo.find(timeEntryId);
        if(currentTimeEntry != null) {
            return new ResponseEntity(currentTimeEntry, HttpStatus.OK);
        } else { return new ResponseEntity(currentTimeEntry, HttpStatus.NOT_FOUND); }
    }

    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(repo.list(), HttpStatus.OK);
    }

    public ResponseEntity update(long timeEntryId, TimeEntry timeEntry) {
        if(repo.find(timeEntryId) != null) {
            return new ResponseEntity(repo.update(timeEntryId, timeEntry), HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<TimeEntry> delete(long timeEntryId) {
        repo.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
