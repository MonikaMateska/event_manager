package wp_project.event_manager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Location {
    public Location() {
    }

    public Location(Long latitude, Long longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Long id;

    private Long latitude;

    private Long longitude;

    private String name;

    public Long getId() {
        return id;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
