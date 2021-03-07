package wp_project.event_manager.model;

import org.apache.xpath.operations.Bool;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comment {
    public Comment() {
    }

    public Comment(String text, User user, List<Image> images) {
        this.text = text;
        this.user = user;
        this.images = images;
    }

    @Id
    @GeneratedValue
    Long id;

    private String text;

    @OneToOne
    private User user;

    @OneToMany
    private List<Image> images;

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
