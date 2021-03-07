package wp_project.event_manager.service;

import wp_project.event_manager.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listAll();

    Comment create(String text, String username, List<Long> imageIds);

    Comment delete(Long id);
}
