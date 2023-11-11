package org.fperspective.academicblogapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.fperspective.academicblogapi.model.Comment;
import org.fperspective.academicblogapi.model.Comment;
import org.fperspective.academicblogapi.repository.CommentRepository;
import org.fperspective.academicblogapi.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private SearchRepository searchRepository;

    public Collection<Comment> get() {
        return commentRepository.findAll();
    }

    public Comment get(String commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public List<Comment> findMostPopularComment(String blogId){
        List<String> comments = searchRepository.findMostLikedCommentByBlog(blogId);
        List<Comment> commentList = new ArrayList<>();
        comments.forEach((comment) -> commentList.add(commentRepository.findById(comment).get()));
        return commentList;
    }

    public List<Comment> sortLatestComment(String blogId) {
        List<String> comments = searchRepository.sortLatestComment(blogId);
        List<Comment> commentList = new ArrayList<>();
        comments.forEach((comment) -> commentList.add(commentRepository.findById(comment).get()));
        return commentList;
    }

    public Comment remove(String commentId) {
        Comment existingComment = commentRepository.findById(commentId).get();
        existingComment.setStatus(false);
        return commentRepository.save(existingComment);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment update(Comment comment) {
        Comment existingComment = commentRepository.findById(comment.getCommentId()).get();
        existingComment.setCommentContent(comment.getCommentContent());
        return commentRepository.save(existingComment);
    }

    public Comment like(Comment comment) {
        Comment existingComment = commentRepository.findById(comment.getCommentId()).get();
        String[] newlike = comment.getLike();
        Integer count = newlike.length - 1;
        String[] existingLike = existingComment.getLike();
        Boolean check = true;
        for(String like : existingLike){
            if(like.equals(newlike[count])){
                check = false;
            }
        }
        if(check == true){
            existingComment.setLike(comment.getLike());
        }
        return commentRepository.save(existingComment);
    }

    public Comment unlike(Comment comment) {
        Comment existingComment = commentRepository.findById(comment.getCommentId()).get();
        existingComment.setLike(comment.getLike());
        return commentRepository.save(existingComment);
    }
    
}
