package wp_project.event_manager.service;

import wp_project.event_manager.model.Image;

public interface ImageService {

    Image findById(Long id);

    Image create(byte[] content, String name);

    Image update(Long id, byte[] content, String name);

    Image delete(Long id);
}
