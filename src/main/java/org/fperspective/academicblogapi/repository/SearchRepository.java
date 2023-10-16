package org.fperspective.academicblogapi.repository;

import java.util.List;

import org.fperspective.academicblogapi.model.BTag;
import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository {
    List<Blog> searchBlogByText(String text);

    List<User> searchUserByText(String text);

    List<BTag> findMostUsedTag();

    List<Integer> findMostUsedTagCount();

}
