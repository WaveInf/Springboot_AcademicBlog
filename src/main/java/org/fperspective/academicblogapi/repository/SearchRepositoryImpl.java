package org.fperspective.academicblogapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.fperspective.academicblogapi.model.BTag;
import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import org.bson.Document;
import org.bson.types.ObjectId;

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
        @Lazy
        MongoClient client;

        @Autowired
        @Lazy
        MongoConverter converter;

        /*
         * BLOG METHOD
         */

        @Override
        // Blog search by blogName / tagName with autocorrect by 2 letters at 3rd index
        public List<Blog> searchBlogByText(String text, String time) {

                Long timeLong = Long.parseLong(time);

                final List<Blog> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(

                                new Document("$search",
                                                new Document("index", "blog")
                                                                .append("text",
                                                                                new Document("query", text)
                                                                                                .append("path", Arrays
                                                                                                                .asList("blogTitle"))
                                                                                                .append("fuzzy",
                                                                                                                new Document("maxEdits",
                                                                                                                                2L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                3L)))),
                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$sort",
                                                new Document("uploadDate", timeLong)),
                                new Document("$limit", 5L)));

                result.forEach((doc) -> blogs.add(converter.read(Blog.class, doc)));

                return blogs;
        }

        @Override
        // Blog search by categoryName with autocorrect by 2 letters at 3rd index
        public List<Blog> searchBlogByCategory(String category, String time) {

                Long timeLong = Long.parseLong(time);

                final List<Blog> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                                new Document("$search",
                                                new Document("index", "blog")
                                                                .append("text",
                                                                                new Document("query", category)
                                                                                                .append("path", Arrays
                                                                                                                .asList(
                                                                                                                                "category.categoryName"))
                                                                                                .append("fuzzy",
                                                                                                                new Document("maxEdits",
                                                                                                                                2L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                3L)))),

                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$sort",
                                                new Document("uploadDate", timeLong)),
                                new Document("$limit", 5L)));

                result.forEach((doc) -> blogs.add(converter.read(Blog.class, doc)));

                return blogs;
        }

        @Override
        // Blog search by categoryName with autocorrect by 2 letters at 3rd index
        public List<Blog> searchBlogBySubject(String subject, String time) {

                Long timeLong = Long.parseLong(time);

                final List<Blog> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                                new Document("$search",
                                                new Document("index", "blog")
                                                                .append("text",
                                                                                new Document("query", subject)
                                                                                                .append("path", Arrays
                                                                                                                .asList(
                                                                                                                                "subject.subjectName"))
                                                                                                .append("fuzzy",
                                                                                                                new Document("maxEdits",
                                                                                                                                2L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                3L)))),

                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$sort",
                                                new Document("uploadDate", timeLong)),
                                new Document("$limit", 5L)));

                result.forEach((doc) -> blogs.add(converter.read(Blog.class, doc)));

                return blogs;
        }

        @Override
        // Blog search by categoryName with autocorrect by 2 letters at 3rd index
        public List<Blog> searchBlogByTag(String tag, String time) {

                Long timeLong = Long.parseLong(time);

                final List<Blog> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                                new Document("$search",
                                                new Document("index", "blog")
                                                                .append("text",
                                                                                new Document("query", tag)
                                                                                                .append("path", Arrays
                                                                                                                .asList(
                                                                                                                                "btag.tagName"))
                                                                                                .append("fuzzy",
                                                                                                                new Document("maxEdits",
                                                                                                                                2L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                3L)))),

                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$sort",
                                                new Document("uploadDate", timeLong)),
                                new Document("$limit", 5L)));

                result.forEach((doc) -> blogs.add(converter.read(Blog.class, doc)));

                return blogs;
        }

        @Override
        public List<Blog> searchBlogByUser(String userId, String time) {

                Long timeLong = Long.parseLong(time);

                final List<Blog> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$match",
                                                new Document("userId",
                                                                userId)),
                                new Document("$sort",
                                                new Document("uploadDate", timeLong))));

                result.forEach((doc) -> blogs.add(converter.read(Blog.class, doc)));

                return blogs;
        }

        @Override
        public List<String> findMostLikedBlogByText(String text) {

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result;
                result = collection.aggregate(Arrays.asList(
                                new Document("$search",
                                                new Document("index", "blog")
                                                                .append("text",
                                                                                new Document("query", text)
                                                                                                .append("path", "blogTitle")
                                                                                                .append("fuzzy",
                                                                                                                new Document("maxEdits",
                                                                                                                                2L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                3L)))),
                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$unwind",
                                                new Document("path", "$like")
                                                                .append("preserveNullAndEmptyArrays", false)),
                                new Document("$group",
                                                new Document("_id", "$_id")
                                                                .append("like",
                                                                                new Document("$sum", 1L))),
                                new Document("$sort",
                                                new Document("like", -1L)),
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

        @Override
        public List<String> findMostLikedBlogByCategory(String category) {

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result;
                result = collection.aggregate(Arrays.asList(
                                new Document("$search",
                                                new Document("index", "blog")
                                                                .append("text",
                                                                                new Document("query", category)
                                                                                                .append("path", "category.categoryName")
                                                                                                .append("fuzzy",
                                                                                                                new Document("maxEdits",
                                                                                                                                2L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                3L)))),
                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$unwind",
                                                new Document("path", "$like")
                                                                .append("preserveNullAndEmptyArrays", false)),
                                new Document("$group",
                                                new Document("_id", "$_id")
                                                                .append("like",
                                                                                new Document("$sum", 1L))),
                                new Document("$sort",
                                                new Document("like", -1L)),
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

        @Override
        public List<String> findMostLikedBlogBySubject(String subject) {

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result;
                result = collection.aggregate(Arrays.asList(
                                new Document("$search",
                                                new Document("index", "blog")
                                                                .append("text",
                                                                                new Document("query", subject)
                                                                                                .append("path", "subject.subjectName")
                                                                                                .append("fuzzy",
                                                                                                                new Document("maxEdits",
                                                                                                                                2L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                3L)))),
                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$unwind",
                                                new Document("path", "$like")
                                                                .append("preserveNullAndEmptyArrays", false)),
                                new Document("$group",
                                                new Document("_id", "$_id")
                                                                .append("like",
                                                                                new Document("$sum", 1L))),
                                new Document("$sort",
                                                new Document("like", -1L)),
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

        @Override
        public List<String> findMostLikedBlogByTag(String tag) {

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result;
                result = collection.aggregate(Arrays.asList(
                                new Document("$search",
                                                new Document("index", "blog")
                                                                .append("text",
                                                                                new Document("query", tag)
                                                                                                .append("path", "btag.tagName")
                                                                                                .append("fuzzy",
                                                                                                                new Document("maxEdits",
                                                                                                                                2L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                3L)))),
                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$unwind",
                                                new Document("path", "$like")
                                                                .append("preserveNullAndEmptyArrays", false)),
                                new Document("$group",
                                                new Document("_id", "$_id")
                                                                .append("like",
                                                                                new Document("$sum", 1L))),
                                new Document("$sort",
                                                new Document("like", -1L)),
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

        @Override
        public List<String> findMostLikedBlogByUser(String userId) {

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result;
                result = collection.aggregate(Arrays.asList(
                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$match",
                                                new Document("userId",
                                                                userId)),
                                new Document("$unwind",
                                                new Document("path", "$like")
                                                                .append("preserveNullAndEmptyArrays", false)),
                                new Document("$group",
                                                new Document("_id", "$_id")
                                                                .append("like",
                                                                                new Document("$sum", 1L))),
                                new Document("$sort",
                                                new Document("like", -1L)),
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

        @Override
        public List<Blog> sortLatestBlog() {

                final List<Blog> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$sort",
                                                new Document("uploadDate", -1L))));

                result.forEach((blog) -> blogs.add(converter.read(Blog.class, blog)));

                return blogs;
        }

        @Override
        public List<String> sortBlogAll() {

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)
                                                .append("deleted", false)),
                                new Document("$group",
                                                new Document("_id",
                                                                new Document("blogId", "$_id")
                                                                                .append("numberOfLike",
                                                                                                new Document("$cond",
                                                                                                                new Document("if",
                                                                                                                                new Document("$isArray",
                                                                                                                                                "$like"))
                                                                                                                                .append("then",
                                                                                                                                                new Document("$size",
                                                                                                                                                                "$like"))
                                                                                                                                .append("else", "NA"))))),
                                new Document("$sort",
                                                new Document("_id.numberOfLike", -1L)),
                                new Document("$project",
                                                new Document("_id", "$_id.blogId"))));

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

        @Override
        public List<String> sortBlogByYear(String year) {

                Long yearLong = Long.parseLong(year);

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)
                                                .append("deleted", false)),
                                new Document("$group",
                                                new Document("_id",
                                                                new Document("blogId", "$_id")
                                                                                .append("year",
                                                                                                new Document("$year",
                                                                                                                "$uploadDate"))
                                                                                .append("numberOfLike",
                                                                                                new Document("$cond",
                                                                                                                new Document("if",
                                                                                                                                new Document("$isArray",
                                                                                                                                                "$like"))
                                                                                                                                .append("then",
                                                                                                                                                new Document("$size",
                                                                                                                                                                "$like"))
                                                                                                                                .append("else", "NA"))))),
                                new Document("$match",
                                                new Document("_id.year", yearLong)),
                                new Document("$sort",
                                                new Document("_id.numberOfLike", -1L)),
                                new Document("$project",
                                                new Document("_id", "$_id.blogId"))));

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

        @Override
        public List<String> sortBlogByMonth(String year, String month) {

                Long yearLong = Long.parseLong(year);

                Long monthLong = Long.parseLong(month);

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)
                                                .append("deleted", false)),
                                new Document("$group",
                                                new Document("_id",
                                                                new Document("blogId", "$_id")
                                                                                .append("year",
                                                                                                new Document("$year",
                                                                                                                "$uploadDate"))
                                                                                .append("month",
                                                                                                new Document("$month",
                                                                                                                "$uploadDate"))
                                                                                .append("numberOfLike",
                                                                                                new Document("$cond",
                                                                                                                new Document("if",
                                                                                                                                new Document("$isArray",
                                                                                                                                                "$like"))
                                                                                                                                .append("then",
                                                                                                                                                new Document("$size",
                                                                                                                                                                "$like"))
                                                                                                                                .append("else", "NA"))))),
                                new Document("$match",
                                                new Document("_id.year", yearLong)
                                                                .append("_id.month", monthLong)),
                                new Document("$sort",
                                                new Document("_id.numberOfLike", -1L)),
                                new Document("$project",
                                                new Document("_id", "$_id.blogId"))));
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

        @Override
        public List<String> sortBlogByWeek(String year, String month, String week) {

                Long yearLong = Long.parseLong(year);

                Long monthLong = Long.parseLong(month);

                Long weekLong = Long.parseLong(week);

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)
                                                .append("deleted", false)),
                                new Document("$group",
                                                new Document("_id",
                                                                new Document("blogId", "$_id")
                                                                                .append("year",
                                                                                                new Document("$year",
                                                                                                                "$uploadDate"))
                                                                                .append("month",
                                                                                                new Document("$month",
                                                                                                                "$uploadDate"))
                                                                                .append("week",
                                                                                                new Document("$week",
                                                                                                                "$uploadDate"))
                                                                                .append("day",
                                                                                                new Document("$dayOfMonth",
                                                                                                                "$uploadDate"))
                                                                                .append("numberOfLike",
                                                                                                new Document("$cond",
                                                                                                                new Document("if",
                                                                                                                                new Document("$isArray",
                                                                                                                                                "$like"))
                                                                                                                                .append("then",
                                                                                                                                                new Document("$size",
                                                                                                                                                                "$like"))
                                                                                                                                .append("else", "NA"))))),
                                new Document("$match",
                                                new Document("_id.year", yearLong)
                                                .append("_id.month", monthLong)
                                                .append("_id.week", weekLong)),
                                new Document("$sort",
                                                new Document("_id.numberOfLike", -1L)),
                                new Document("$project",
                                                new Document("_id", "$_id.blogId"))));
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

        @Override
        public List<Blog> findUnapprovedBlogs(String operator) {

                final List<Blog> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result;
                if ("all".equals(operator)) {
                        result = collection.aggregate(Arrays.asList(new Document("$match",
                                        new Document("status", false)
                                                        .append("deleted", false))));
                } else {
                        result = collection.aggregate(Arrays.asList(new Document("$search",
                                        new Document("index", "blog")
                                                        .append("text",
                                                                        new Document("query", "Software Engineer")
                                                                                        .append("path", "category.categoryName"))),
                                        new Document("$match",
                                                        new Document("status", false)
                                                                        .append("deleted", false)),
                                        new Document("$sort",
                                                        new Document("uploadDate", 1L))));
                }

                result.forEach((doc) -> blogs.add(converter.read(Blog.class, doc)));

                return blogs;
        }

        /*
         * USER METHOD
         */

        @Override
        // User search by userName with autocorrect by 2 letters at 3rd index
        public List<Credential> searchUserByText(String text) {

                final List<Credential> users = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Credential");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(

                                new Document("$search",
                                                new Document("index", "credential")
                                                                .append("text",
                                                                                new Document("query", text)
                                                                                                .append("path", "username"))),
                                new Document("$match",
                                                new Document("status", true)),
                                new Document("$sort",
                                                new Document("username", -1L)),
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
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(

                                new Document("$search",
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

                                new Document("$match",
                                                new Document("status", true)),
                                new Document("$sort",
                                                new Document("userName", 1L)),
                                new Document("$limit", 5L)));

                result.forEach((doc) -> users.add(converter.read(Credential.class, doc)));

                return users;
        }

        @Override
        public Credential searchUserByEmail(String email) {
                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Credential");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("email", email))));

                return converter.read(Credential.class, result.first());
        }

        @Override
        public List<String> findRecommendedUser(String search) {
                final List<String> users = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result;
                if ("all".equals(search)) {
                        result = collection.aggregate(Arrays.asList(new Document("$match",
                                        new Document("status", true)),
                                        new Document("$sortByCount", "$userId"),
                                        new Document("$unset", "count"),
                                        new Document("$limit", 3L)));
                } else {
                        result = collection.aggregate(Arrays.asList(
                                        new Document("$search",
                                                        new Document("index", "blog")
                                                                        .append("text",
                                                                                        new Document("query",
                                                                                                        Arrays.asList(search))
                                                                                                        .append("path", Arrays
                                                                                                                        .asList(
                                                                                                                                        "subject.subjectName",
                                                                                                                                        "btag.tagName",
                                                                                                                                        "category.categoryName")))),
                                        new Document("$match",
                                                        new Document("status", true)),
                                        new Document("$sortByCount", "$userId"),
                                        new Document("$unset", "count"),
                                        new Document("$limit", 3L)));
                }
                // result.forEach((doc) -> users.add(converter.read(String.class, doc)));
                result.forEach((doc) -> {
                        ObjectMapper objectMapper = new ObjectMapper();

                        // Parse the JSON string
                        JsonNode jsonNode;
                        try {
                                jsonNode = objectMapper.readTree(converter.read(String.class, doc));
                                // Access the value associated with "$oid"
                                String oidValue = jsonNode.get("_id")
                                                // .get("$oid")
                                                .asText();
                                users.add(oidValue);
                        } catch (JsonMappingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (JsonProcessingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                });

                return users;
        }

        /*
         * TAG METHOD
         */

        @Override
        // Query to find most used tags and its used count
        public List<String> findMostUsedTag(String limit) {

                Long limitLong = Long.parseLong(limit);

                final List<String> tags = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                                new Document("$match",
                                                new Document("status", true)),
                                new Document("$unwind",
                                                new Document("path", "$btag").append("preserveNullAndEmptyArrays",
                                                                false)),
                                new Document("$sortByCount", "$btag._id"),
                                // new Document("$unset", "count"),
                                new Document("$limit", limitLong)));

                result.forEach((doc) -> {
                        ObjectMapper objectMapper = new ObjectMapper();

                        // Parse the JSON string
                        JsonNode jsonNode;
                        try {
                                jsonNode = objectMapper.readTree(converter.read(String.class, doc));
                                // Access the value associated with "$oid"
                                String oidValue = jsonNode.get("_id")
                                                .get("$oid")
                                                .asText();
                                tags.add(oidValue);
                        } catch (JsonMappingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (JsonProcessingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                });

                return tags;
        }

        @Override
        public Integer findMostUsedTagCount(String tagName) {

                final List<BTag> count = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                                new Document("$search",
                                                new Document("index", "blog")
                                                                .append("text",
                                                                                new Document("query", tagName)
                                                                                                .append("path", "btag.tagName"))),
                                new Document("$match",
                                                new Document("status", true))));
                result.forEach((doc) -> count.add(converter.read(BTag.class, doc)));

                Integer tagCount = count.size();

                return tagCount;
        }

}
