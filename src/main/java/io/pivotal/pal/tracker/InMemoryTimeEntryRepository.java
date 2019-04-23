package io.pivotal.pal.tracker;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements  TimeEntryRepository {

    private Map<Long, TimeEntry> entries = new HashMap<>();
    private Long sequenceNo = 1L;

    @Override
    public TimeEntry find(long timeEntryId) {

        return entries.get(timeEntryId);
    }

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setTimeEntryId(sequenceNo++);
        entries.put(timeEntry.getTimeEntryId(), timeEntry);
        return timeEntry;
    }

    @Override
    public List<TimeEntry> list() {
        return entries.values().stream().collect(Collectors.toList());
    }

    @Override
    public TimeEntry update(long id, TimeEntry entry) {
        if(entries.get(id) != null) {
            entry.setTimeEntryId(id);
            entries.put(id, entry);
            return entry;
        } else{ return null; }
    }

    @Override
    public void delete(long timeEntryId) {
        entries.remove(timeEntryId);
    }
}
