package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository repo;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repo = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry newTimeEntry = repo.create(timeEntryToCreate);
        return new ResponseEntity(newTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry currentTimeEntry = repo.find(timeEntryId);
        if(currentTimeEntry != null) {
            return new ResponseEntity(currentTimeEntry, HttpStatus.OK);
        } else { return new ResponseEntity(currentTimeEntry, HttpStatus.NOT_FOUND); }
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(repo.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry temp = repo.update(timeEntryId, timeEntry);
        if(temp != null) {
            return new ResponseEntity(temp, HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long timeEntryId) {
        repo.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
