package org.fperspective.academicblogapi.repository;

import java.util.List;
import java.util.Map;

import org.fperspective.academicblogapi.model.BTag;
import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.model.Credential;
import org.fperspective.academicblogapi.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository {

    //Blog method
    List<Blog> searchBlogByText(String text);

    List<Blog> searchBlogByCategory(String category);

    List<String> findMostLikedBlog();

    // String findMostLikedBlog();

    //User method
    List<Credential> searchUserByText(String text);

    List<Credential> searchUserByCampus(String campus);

    //Tag method
    Map<BTag, Integer> findMostUsedTag();

}
