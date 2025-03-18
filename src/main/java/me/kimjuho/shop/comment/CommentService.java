package me.kimjuho.shop.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
    public List<Comment> getComments(Long id) {
        return commentRepository.findAllByParentId(id);
    }
}
