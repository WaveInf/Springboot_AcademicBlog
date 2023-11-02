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

    List<String> sortBlogByDate();

    // String findMostLikedBlog();

    //User method
    List<Credential> searchUserByText(String text);

    List<Credential> searchUserByCampus(String campus);

    List<String> findRecommendedUser(String[] tagName);

    //Tag method
    List<String> findMostUsedTag(String limit);

    Integer findMostUsedTagCount(String tagName);

}
