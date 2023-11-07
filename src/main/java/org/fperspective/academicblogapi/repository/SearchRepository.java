package org.fperspective.academicblogapi.repository;

import java.util.List;
import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.model.Credential;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository {

    //Blog method
    List<Blog> searchBlogByText(String text, String time);

    List<Blog> searchBlogByCategory(String category, String time);

    List<Blog> searchBlogBySubject(String subject, String time);

    List<Blog> searchBlogByTag(String tag, String time);

    List<Blog> searchBlogByUser(String userId, String time);

    List<String> findMostLikedBlogByText(String text);

    List<String> findMostLikedBlogByCategory(String category);

    List<String> findMostLikedBlogBySubject(String subject);

    List<String> findMostLikedBlogByTag(String tag);

    List<String> findMostLikedBlogByUser(String userId);

    List<Blog> sortLatestBlog();

    List<String> sortBlogAll();

    List<String> sortBlogByYear(String year);

    List<String> sortBlogByMonth(String year, String month);

    List<String> sortBlogByWeek(String year, String month, String week);

    List<Blog> findUnapprovedBlogs(String operator);

    // String findMostLikedBlog();

    //User method
    List<Credential> searchUserByText(String text);

    List<Credential> searchUserByCampus(String campus);

    Credential searchUserByEmail(String email);

    List<String> findRecommendedUser(String search);

    //Tag method
    List<String> findMostUsedTag(String limit);

    Integer findMostUsedTagCount(String tagName);

}
