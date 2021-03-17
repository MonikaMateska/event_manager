package wp_project.event_manager.service;

import wp_project.event_manager.model.Location;

import java.util.List;

public interface LocationService {

    List<Location> listAll();

    Location findById(Long id);

    Location create(Long latitude, Long longitude, String name);

    Location update(Long id, Long latitude, Long longitude, String name);

    Location delete(Long id);
}
