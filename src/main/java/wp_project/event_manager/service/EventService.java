package wp_project.event_manager.service;

import wp_project.event_manager.model.Event;

import java.util.Date;
import java.util.List;

public interface EventService {

    List<Event> listAll();

    Event findById(Long id);

    Event create(String name, List<String> attendeeUsernames, Long locationId, Date time, List<Long> commentIds);

    Event update(Long id, String name, List<String> attendeeUsernames, Long locationId, Date time, List<Long> commentIds);

    Event delete(Long id);

    List<Event> listEventsByNameAndLocationAndTime(String name, Long locationId, Date time);
}
