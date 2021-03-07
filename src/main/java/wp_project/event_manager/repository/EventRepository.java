package wp_project.event_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wp_project.event_manager.model.Event;
import wp_project.event_manager.model.Location;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByNameLike(String name);

    List<Event> findAllByTime(Date time);

    List<Event> findAllByLocation(Location location);

    List<Event> findAllByNameLikeAndTime(String name, Date time);

    List<Event> findAllByNameLikeAndLocation(String name, Location location);

    List<Event> findAllByLocationAndTime(Location location, Date time);

    List<Event> findAllByNameLikeAndTimeAndLocation(String name, Date time, Location location);
}
