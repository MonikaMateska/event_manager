package wp_project.event_manager.service.impl;

import org.springframework.stereotype.Service;
import wp_project.event_manager.model.Comment;
import wp_project.event_manager.model.Image;
import wp_project.event_manager.model.User;
import wp_project.event_manager.model.exceptions.InvalidCommentIdException;
import wp_project.event_manager.model.exceptions.InvalidUserIdException;
import wp_project.event_manager.repository.CommentRepository;
import wp_project.event_manager.repository.ImageRepository;
import wp_project.event_manager.repository.UserRepository;
import wp_project.event_manager.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
   private final CommentRepository commentRepository;
   private final UserRepository userRepository;
   private final ImageRepository imageRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              UserRepository userRepository,
                              ImageRepository imageRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Comment> listAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public Comment create(String text, String username, List<Long> imageIds) {
        User user = this.userRepository.findById(username).orElseThrow(InvalidUserIdException::new);
        List<Image> images = this.imageRepository.findAllById(imageIds);

        Comment comment = new Comment(text, user, images);

        return this.commentRepository.save(comment);
    }

    @Override
    public Comment delete(Long id) {
        Comment comment = this.commentRepository.findById(id).orElseThrow(InvalidCommentIdException::new);
        this.commentRepository.delete(comment);

        return comment;
    }
}
