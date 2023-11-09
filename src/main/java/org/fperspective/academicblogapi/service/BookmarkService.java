package org.fperspective.academicblogapi.service;

import java.util.Collection;

import org.fperspective.academicblogapi.controller.BookmarkController;
import org.fperspective.academicblogapi.model.Bookmark;
import org.fperspective.academicblogapi.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    public Collection<Bookmark> get() {
        return bookmarkRepository.findAll();
    }

    public Bookmark get(String userId) {
        return bookmarkRepository.findById(userId).orElse(null);
    }

    public void remove(String userId) {
        bookmarkRepository.deleteById(userId);
    }

    public Bookmark save(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    public Bookmark update(Bookmark bookmark) {
        Bookmark existingBookmark = bookmarkRepository.findById(bookmark.getUserId()).get();
        existingBookmark.setBookmarkedPost(bookmark.getBookmarkedPost());
        return bookmarkRepository.save(existingBookmark);
    }
    
}
