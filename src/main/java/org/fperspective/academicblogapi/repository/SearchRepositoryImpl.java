package org.fperspective.academicblogapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.fperspective.academicblogapi.model.BTag;
import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Log;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import java.util.concurrent.TimeUnit;

import javax.print.Doc;

import org.bson.Document;
import com.mongodb.client.AggregateIterable;

import com.mongodb.client.MongoClient;

@Component
public class SearchRepositoryImpl implements SearchRepository {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Blog> searchBlogByText(String text) {

        final List<Blog> blogs = new ArrayList<>();

        MongoDatabase database = client.getDatabase("Main");
        MongoCollection<Document> collection = database.getCollection("Blog");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("index", "blog")
                        .append("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("blogTitle", "btag.tagName"))
                                        .append("fuzzy",
                                new Document("maxEdits", 2L)
                                        .append("prefixLength", 3L)))),
                new Document("$sort",
                        new Document("blogTitle", 1L)),
                new Document("$limit", 5L)));

        result.forEach((doc) -> blogs.add(converter.read(Blog.class, doc)));

        return blogs;
    }

    @Override
    public List<User> searchUserByText(String text) {

        final List<User> users = new ArrayList<>();

        MongoDatabase database = client.getDatabase("Main");
        MongoCollection<Document> collection = database.getCollection("User");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("index", "user")
                        .append("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("userName"))
                                        .append("fuzzy",
                                new Document("maxEdits", 2L)
                                        .append("prefixLength", 3L)))),
                new Document("$sort",
                        new Document("userName", 1L)),
                new Document("$limit", 5L)));

        result.forEach((doc) -> users.add(converter.read(User.class, doc)));

        return users;
    }

    @Override
    public List<BTag> findMostUsedTag() {
        final List<BTag> tags = new ArrayList<>();

        final List<Integer> count = new ArrayList<>();

        MongoDatabase database = client.getDatabase("Main");
        MongoCollection<Document> collection = database.getCollection("Blog");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$unwind",
                new Document("path", "$btag")),
                new Document("$sortByCount", "$btag.tagName")));

        result.forEach((doc) -> tags.add(converter.read(BTag.class, doc)));

        result.forEach((doc) ->
            count.add(doc.getInteger("count"))
        );

        return tags;        
    }

    @Override
    public List<Integer> findMostUsedTagCount() {
        final List<Integer> count = new ArrayList<>();

        MongoDatabase database = client.getDatabase("Main");
        MongoCollection<Document> collection = database.getCollection("Blog");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$unwind",
                new Document("path", "$btag")),
                new Document("$sortByCount", "$btag")));

        result.forEach((doc) ->
            count.add(doc.getInteger("count"))
        );

        return count;        
    }


}
