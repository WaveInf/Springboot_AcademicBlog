package org.fperspective.academicblogapi.repository;

import java.util.List;
import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.model.Credential;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository {

    //Blog method
    List<Blog> searchBlogByText(String text);

    List<Blog> searchBlogByCategory(String category);

    List<String> findMostLikedBlog(String limit);

    List<Blog> sortLatestBlog();

    // String findMostLikedBlog();

    //User method
    List<Credential> searchUserByText(String text);

    List<Credential> searchUserByCampus(String campus);

    //Tag method
    List<String> findMostUsedTag(String limit);

    Integer findMostUsedTagCount(String tagName);

}
