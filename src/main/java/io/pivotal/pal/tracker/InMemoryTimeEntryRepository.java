package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> map;
    private long entryCounter = 0;

    public InMemoryTimeEntryRepository() {
        map = new HashMap<>();
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        entryCounter += 1L;
        TimeEntry newTimeEntry = new TimeEntry(entryCounter,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours());
        map.put(entryCounter, newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return map.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(map.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (!map.isEmpty()) {
            TimeEntry timeEntry1 = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
            map.put(id, timeEntry1);

        }
        return map.get(id);
    }

    @Override
    public void delete(long id) {
        map.remove(id);
    }

}
