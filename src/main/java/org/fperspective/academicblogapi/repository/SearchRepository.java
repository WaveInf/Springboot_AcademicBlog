package org.fperspective.academicblogapi.repository;

import java.text.ParseException;
import java.util.List;

import org.fperspective.academicblogapi.model.BTag;
import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.model.Credential;
import org.fperspective.academicblogapi.model.Subject;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository {

    //Blog method
    List<Blog> searchBlogByText(String text, String time);

    List<Blog> searchBlogByCategory(String category, String time);

    List<Blog> searchBlogBySubject(String subject, String time);

    List<Blog> searchBlogByTag(String tag, String time);

    List<Blog> searchBlogByUser(String userId, String time);

    List<Blog> searchAllBlogByUser(String userId);

    List<String> findAllLikedBlog(String userId);

    List<String> findMostLikedBlogByText(String text);

    List<String> findMostLikedBlogByCategory(String category);

    List<String> findMostLikedBlogBySubject(String subject);

    List<String> findMostLikedBlogByTag(String tag);

    List<String> findMostLikedBlogByUser(String userId);

    List<Blog> sortLatestBlog(String time);

    List<String> sortBlogAll();

    List<String> sortBlogByDateAndSubject(String startDate, String endDate, String subject) throws ParseException;

    List<String> sortBlogByDateAndTag(String startDate, String endDate, String tag) throws ParseException;

    List<Blog> sortBlogByDateRange(String startDate, String endDate) throws ParseException;

    List<Blog> findUnapprovedBlogs(String operator);

    List<String> findBlogContainTag(String tagName);

    Integer getIndexTagByBlog(String tagName, String blogId);

    List<String> findBlogContainSubject(String subjectName);

    Integer getIndexSubjectByBlog(String subjectName, String blogId);

    //User method
    List<Credential> searchUserByText(String text);

    List<Credential> searchUserByCampus(String campus);

    Credential searchUserByEmail(String email);

    List<String> findRecommendedUser(String search, String currentUser);

    //Tag method
    List<String> findMostUsedTag(String limit);

    List<String> findMostUsedTagByDate(String limit, String startDate, String endDate) throws ParseException;

    Integer findMostUsedTagCount(String tagName);

    List<String> findTagByBlog(String blogId);

    BTag findTagByName(String text);

    List<BTag> findTagListByName(String text);


    //Subject method

    List<String> findMostUsedSubject(String limit);

    List<String> findMostUsedSubjectByDate(String limit, String startDate, String endDate) throws ParseException;

    Integer findMostUsedSubjectCount(String subjectName);

    List<String> findSubjectByBlog(String blogId);

    Subject findSubjectByName(String text);

    List<Subject> findSubjectListByName(String text);

    //Comment method

    List<String> findMostLikedCommentByBlog(String blogId);

    List<String> sortLatestComment(String blogId);

    //Follow method

    List<String> findFollowerCount(String userId);
}
