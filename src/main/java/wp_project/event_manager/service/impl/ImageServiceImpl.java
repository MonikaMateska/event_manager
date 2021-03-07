package wp_project.event_manager.service.impl;

import org.springframework.stereotype.Service;
import wp_project.event_manager.model.Image;
import wp_project.event_manager.model.exceptions.InvalidImageIdException;
import wp_project.event_manager.repository.ImageRepository;
import wp_project.event_manager.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image findById(Long id) {
        return this.imageRepository.findById(id).orElseThrow(InvalidImageIdException::new);
    }

    @Override
    public Image create(byte[] content, String name) {
        Image image = new Image(content, name);
        return this.imageRepository.save(image);
    }

    @Override
    public Image update(Long id, byte[] content, String name) {
        Image image = this.findById(id);
        image.setContent(content);
        image.setName(name);
        return this.imageRepository.save(image);
    }

    @Override
    public Image delete(Long id) {
        Image image = this.findById(id);
        this.imageRepository.delete(image);
        return image;
    }
}
