package wp_project.event_manager.service.impl;

import org.springframework.stereotype.Service;
import wp_project.event_manager.model.Comment;
import wp_project.event_manager.model.Event;
import wp_project.event_manager.model.Location;
import wp_project.event_manager.model.User;
import wp_project.event_manager.model.exceptions.InvalidEventIdException;
import wp_project.event_manager.model.exceptions.InvalidLocationIdException;
import wp_project.event_manager.repository.*;
import wp_project.event_manager.service.EventService;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final CommentRepository commentRepository;

    public EventServiceImpl(EventRepository eventRepository, ImageRepository imageRepository, UserRepository userRepository, LocationRepository locationRepository, CommentRepository commentRepository) {
        this.eventRepository = eventRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Event> listAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return this.eventRepository.findById(id).orElseThrow(InvalidEventIdException::new);
    }

    @Override
    public Event create(String name, List<String> attendeeUsernames, Long locationId, Date time, List<Long> commentIds) {
        List<User> attendees = this.userRepository.findAllById(attendeeUsernames);
        Location location = this.locationRepository.findById(locationId).orElseThrow(InvalidLocationIdException::new);
        List<Comment> comments = this.commentRepository.findAllById(commentIds);

        Event event = new Event(name, attendees, location, time, comments);

        return this.eventRepository.save(event);
    }

    @Override
    public Event update(Long id, String name, List<String> attendeeUsernames, Long locationId, Date time, List<Long> commentIds) {
        Event event = this.findById(id);
        event.setName(name);
        event.setTime(time);

        List<User> attendees = this.userRepository.findAllById(attendeeUsernames);
        event.setAttendees(attendees);

        Location location = this.locationRepository.findById(locationId).orElseThrow(InvalidLocationIdException::new);
        event.setLocation(location);

        List<Comment> comments = this.commentRepository.findAllById(commentIds);
        event.setComments(comments);

        return this.eventRepository.save(event);
    }

    @Override
    public Event delete(Long id) {
        Event event = this.findById(id);
        this.eventRepository.delete(event);
        return event;
    }

    @Override
    public List<Event> listEventsByNameAndLocationAndTime(String name, Long locationId, Date time) {

        if (name != null && !name.isEmpty() && locationId != null && time != null) {
            Location location = locationRepository.findById(locationId).orElseThrow(InvalidLocationIdException::new);
            return this.eventRepository.findAllByNameLikeAndTimeAndLocation(name, time, location);
        } else if (locationId != null && time != null) {
            Location location = locationRepository.findById(locationId).orElseThrow(InvalidLocationIdException::new);
            return this.eventRepository.findAllByLocationAndTime(location, time);
        } else if (locationId != null && name != null && !name.isEmpty()) {
            Location location = locationRepository.findById(locationId).orElseThrow(InvalidLocationIdException::new);
            return this.eventRepository.findAllByNameLikeAndLocation(name, location);
        } else if (time != null && name != null && !name.isEmpty()) {
            return this.eventRepository.findAllByNameLikeAndTime(name, time);
        }

        return this.listAll();
    }
}
