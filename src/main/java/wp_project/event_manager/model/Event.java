package wp_project.event_manager.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Event {
    public Event() {
    }

    public Event(String name, List<User> attendees, Location location, Date time, List<Comment> comments) {
        this.name = name;
        this.attendees = attendees;
        this.location = location;
        this.time = time;
        this.comments = comments;
    }

    @Id
    @GeneratedValue
    Long id;

    private String name;

    @ManyToMany
    private List<User> attendees;

    @ManyToOne
    private Location location;

    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @OneToMany
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
