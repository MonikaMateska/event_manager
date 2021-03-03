package wp_project.event_manager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity
public class Image {
    public Image() {
    }

    public Image(byte[] content, String name) {
        this.content = content;
        this.name = name;
    }

    @Id
    @GeneratedValue
    Long id;

    @Lob
    byte[] content;

    String name;

    public Long getId() {
        return id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}