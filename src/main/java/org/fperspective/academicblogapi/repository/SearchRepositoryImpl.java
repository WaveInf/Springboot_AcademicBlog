package org.fperspective.academicblogapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

import org.fperspective.academicblogapi.model.BTag;
import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.AggregateIterable;

import com.mongodb.client.MongoClient;

@Component
public class SearchRepositoryImpl implements SearchRepository {

        @Autowired
        // @Lazy
        MongoClient client;

        @Autowired
        // @Lazy
        MongoConverter converter;

        @Override
        // Blog search by blogName / tagName with autocorrect by 2 letters at 3rd index
        public List<Blog> searchBlogByText(String text) {

                final List<Blog> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("index", "blog")
                                                .append("text",
                                                                new Document("query", text)
                                                                                .append("path", Arrays.asList(
                                                                                                "blogTitle",
                                                                                                "btag.tagName"))
                                                                                .append("fuzzy",
                                                                                                new Document("maxEdits",
                                                                                                                2L)
                                                                                                                .append("prefixLength",
                                                                                                                                3L)))),
                                new Document("$sort",
                                                new Document("blogTitle", 1L)),
                                new Document("$limit", 5L)));

                result.forEach((doc) -> blogs.add(converter.read(Blog.class, doc)));

                return blogs;
        }

        @Override
        // Blog search by categoryName with autocorrect by 2 letters at 3rd index
        public List<Blog> searchBlogByCategory(String category) {

                final List<Blog> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("index", "blog")
                                                .append("text",
                                                                new Document("query", category)
                                                                                .append("path", Arrays.asList(
                                                                                                "categoryName"))
                                                                                .append("fuzzy",
                                                                                                new Document("maxEdits",
                                                                                                                2L)
                                                                                                                .append("prefixLength",
                                                                                                                                3L)))),
                                new Document("$sort",
                                                new Document("blogTitle", 1L)),
                                new Document("$limit", 5L)));

                result.forEach((doc) -> blogs.add(converter.read(Blog.class, doc)));

                return blogs;
        }

        @Override
        // User search by userName with autocorrect by 2 letters at 3rd index
        public List<Credential> searchUserByText(String text) {

                final List<Credential> users = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Credential");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("index", "credential")
                                                .append("text",
                                                                new Document("query", text)
                                                                                .append("path", Arrays
                                                                                                .asList("username"))
                                                                                .append("fuzzy",
                                                                                                new Document("maxEdits",
                                                                                                                1L)
                                                                                                                .append("prefixLength",
                                                                                                                                3L)))),
                                new Document("$sort",
                                                new Document("username", 1L)),
                                new Document("$limit", 5L)));

                result.forEach((doc) -> users.add(converter.read(Credential.class, doc)));

                return users;
        }

        @Override
        // User search by userName with autocorrect by 2 letters at 3rd index
        public List<Credential> searchUserByCampus(String campus) {

                final List<Credential> users = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Credential");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("index", "credential")
                                                .append("text",
                                                                new Document("query", campus)
                                                                                .append("path", Arrays
                                                                                                .asList("campus"))
                                                                                .append("fuzzy",
                                                                                                new Document("maxEdits",
                                                                                                                2L)
                                                                                                                .append("prefixLength",
                                                                                                                                3L)))),
                                new Document("$sort",
                                                new Document("userName", 1L)),
                                new Document("$limit", 5L)));

                result.forEach((doc) -> users.add(converter.read(Credential.class, doc)));

                return users;
        }

        @Override
        // Query to find most used tags and its used count
        public Map<BTag, Integer> findMostUsedTag() {

                final Map<BTag, Integer> tagList = new HashMap<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$unwind",
                                new Document("path", "$btag").append("preserveNullAndEmptyArrays", false)),
                                new Document("$sortByCount", "$btag"),
                                new Document("$project",
                                                new Document("_id", "$_id._id")
                                                                .append("tagName", "$_id.tagName")
                                                                .append("status", "$_id.status")
                                                                .append("count", "$count"))));

                result.forEach((doc) -> tagList.put(converter.read(BTag.class, doc), doc.getInteger("count")));

                return tagList;
        }

        @Override
        public List<String> findMostLikedBlog() {
                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$unwind",
                                new Document("path", "$like")
                                                .append("preserveNullAndEmptyArrays", false)),
                                new Document("$group",
                                                new Document("_id", "$_id")
                                                                .append("like",
                                                                                new Document("$sum", 1L))),
                                new Document("$sort",
                                                new Document("like", -1L)),
                                new Document("$limit", 50L),
                                new Document("$unset", "like")));
                result.forEach((doc) -> {
                        ObjectMapper objectMapper = new ObjectMapper();

                        // Parse the JSON string
                        JsonNode jsonNode;
                        try {
                                jsonNode = objectMapper.readTree(converter.read(String.class, doc));
                                // Access the value associated with "$oid"
                                String oidValue = jsonNode.get("_id").get("$oid").asText();
                                blogs.add(oidValue);
                        } catch (JsonMappingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (JsonProcessingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                });

                return blogs;
        }

        

}
