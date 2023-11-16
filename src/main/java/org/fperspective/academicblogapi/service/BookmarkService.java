package org.fperspective.academicblogapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.model.Bookmark;
import org.fperspective.academicblogapi.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private BlogService blogService;

    public Collection<Bookmark> get() {
        return bookmarkRepository.findAll();
    }

    public Bookmark get(String userId) {
        checkExist(userId);
        return bookmarkRepository.findById(userId).orElse(null);
    }

    public List<Blog> getBookmarkedBlog(String userId) {
        checkExist(userId);
        Bookmark bookmark = bookmarkRepository.findById(userId).orElse(null);
        String[] blogs = bookmark.getBookmarkedPost();
        List<Blog> blogList = new ArrayList<>();
        for(int i = 0; i<= (blogs.length-1);i++){
            Blog newBlog = blogService.get(blogs[i]);
            if(newBlog!=null){
                blogList.add(newBlog);
            }
        }
        return blogList;
    }


    public void remove(String userId) {
        bookmarkRepository.deleteById(userId);
    }

    public Bookmark save(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    public Bookmark update(Bookmark bookmark) {
        checkExist(bookmark.getUserId());
        Bookmark existingBookmark = bookmarkRepository.findById(bookmark.getUserId()).get();
        String[] newPost = bookmark.getBookmarkedPost();
        Integer count = newPost.length - 1;
        String[] existingPost = existingBookmark.getBookmarkedPost();
        Boolean check = true;
        for(String post : existingPost){
            if(post.equals(newPost[count])){
                check = false;
            }
        }
        if(check == true){
            existingBookmark.setBookmarkedPost(bookmark.getBookmarkedPost());
        }
        return bookmarkRepository.save(existingBookmark);
    }

    public Bookmark unUpdate(Bookmark bookmark){
        Bookmark existingBookmark = bookmarkRepository.findById(bookmark.getUserId()).get();
        existingBookmark.setBookmarkedPost(bookmark.getBookmarkedPost());
        return bookmarkRepository.save(existingBookmark);
    }
    
    public void checkExist(String userId){
        Bookmark check = bookmarkRepository.findById(userId).orElse(null);
        if(check == null){
            check = new Bookmark(new ObjectId(userId), new String[0]);
            bookmarkRepository.save(check);
        }
    }
}
