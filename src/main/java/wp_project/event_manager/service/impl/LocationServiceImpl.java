package wp_project.event_manager.service.impl;

import org.springframework.stereotype.Service;
import wp_project.event_manager.model.Location;
import wp_project.event_manager.model.exceptions.InvalidLocationIdException;
import wp_project.event_manager.repository.LocationRepository;
import wp_project.event_manager.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location findById(Long id) {
        return this.locationRepository.findById(id).orElseThrow(InvalidLocationIdException::new);
    }

    @Override
    public Location create(Long latitude, Long longitude, String name) {
        Location location = new Location(latitude, longitude, name);

        return this.locationRepository.save(location);
    }

    @Override
    public Location update(Long id, Long latitude, Long longitude, String name) {
        Location location = this.findById(id);

        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setName(name);

        return this.locationRepository.save(location);
    }

    @Override
    public Location delete(Long id) {
        Location location = this.findById(id);

        this.locationRepository.delete(location);

        return location;
    }
}
