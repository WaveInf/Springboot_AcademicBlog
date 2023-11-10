package org.fperspective.academicblogapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.fperspective.academicblogapi.model.BTag;
import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.model.Credential;
import org.fperspective.academicblogapi.model.Subject;
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
                                                                                                                                                2L)))),
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
                                                                                                                                1L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                4L)))),

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
                                                                                                                                1L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                4L)))),

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
                                                                                                                                1L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                4L)))),

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
        public List<Blog> searchAllBlogByUser(String userId) {

                final List<Blog> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                                new Document("$match",
                                                new Document("userId",
                                                                userId))));

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
                                                                                                                                1L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                4L)))),
                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$unwind",
                                                new Document("path", "$like")
                                                                .append("preserveNullAndEmptyArrays", true)),
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
                                                                                                                                1L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                4L)))),
                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$unwind",
                                                new Document("path", "$like")
                                                                .append("preserveNullAndEmptyArrays", true)),
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
                                                                                                                                1L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                4L)))),
                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$unwind",
                                                new Document("path", "$like")
                                                                .append("preserveNullAndEmptyArrays", true)),
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
                                                                                                                                1L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                4L)))),
                                new Document("$match",
                                                new Document("status", true)
                                                                .append("deleted", false)),
                                new Document("$unwind",
                                                new Document("path", "$like")
                                                                .append("preserveNullAndEmptyArrays", true)),
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
                                                                .append("preserveNullAndEmptyArrays", true)),
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
        public List<String> sortBlogByYearAndSubject(String year, String subject) {

                Long yearLong = Long.parseLong(year);

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)),
                                new Document("$group",
                                                new Document("_id",
                                                                new Document("blogId", "$_id")
                                                                                .append("subject", "$subject")
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
                                new Document("$unwind",
                                                new Document("path", "$_id.subject")
                                                                .append("preserveNullAndEmptyArrays", true)),
                                new Document("$sort",
                                                new Document("_id.numberOfLike", -1L)),
                                new Document("$match",
                                                new Document("_id.subject.subjectName", subject)),
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
        public List<String> sortBlogByMonthAndSubject(String year, String month, String subject) {

                Long yearLong = Long.parseLong(year);

                Long monthLong = Long.parseLong(month);

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)),
                                new Document("$group",
                                                new Document("_id",
                                                                new Document("blogId", "$_id")
                                                                                .append("subject", "$subject")
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
                                new Document("$unwind",
                                                new Document("path", "$_id.subject")
                                                                .append("preserveNullAndEmptyArrays", true)),
                                new Document("$sort",
                                                new Document("_id.numberOfLike", -1L)),
                                new Document("$match",
                                                new Document("_id.subject.subjectName", subject)),
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
        public List<String> sortBlogByWeekAndSubject(String year, String month, String week, String subject) {

                Long yearLong = Long.parseLong(year);

                Long monthLong = Long.parseLong(month);

                Long weekLong = Long.parseLong(week);

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)),
                                new Document("$group",
                                                new Document("_id",
                                                                new Document("blogId", "$_id")
                                                                                .append("subject", "$subject")
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
                                new Document("$unwind",
                                                new Document("path", "$_id.subject")
                                                                .append("preserveNullAndEmptyArrays", true)),
                                new Document("$sort",
                                                new Document("_id.numberOfLike", -1L)),
                                new Document("$match",
                                                new Document("_id.subject.subjectName", subject)),
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
        public List<String> sortBlogByYearAndTag(String year, String tag) {

                Long yearLong = Long.parseLong(year);

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)),
                                new Document("$group",
                                                new Document("_id",
                                                                new Document("blogId", "$_id")
                                                                                .append("btag", "$btag")
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
                                                new Document("_id.year", yearLong)),
                                new Document("$unwind",
                                                new Document("path", "$_id.subject")
                                                                .append("preserveNullAndEmptyArrays", true)),
                                new Document("$sort",
                                                new Document("_id.numberOfLike", -1L)),
                                new Document("$match",
                                                new Document("_id.btag.tagName", tag)),
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
        public List<String> sortBlogByMonthAndTag(String year, String month, String tag) {

                Long yearLong = Long.parseLong(year);

                Long monthLong = Long.parseLong(month);

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)),
                                new Document("$group",
                                                new Document("_id",
                                                                new Document("blogId", "$_id")
                                                                                .append("btag", "$btag")
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
                                                                .append("_id.month", monthLong)),
                                new Document("$unwind",
                                                new Document("path", "$_id.subject")
                                                                .append("preserveNullAndEmptyArrays", true)),
                                new Document("$sort",
                                                new Document("_id.numberOfLike", -1L)),
                                new Document("$match",
                                                new Document("_id.btag.tagName", tag)),
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
        public List<String> sortBlogByWeekAndTag(String year, String month, String week, String tag) {

                Long yearLong = Long.parseLong(year);

                Long monthLong = Long.parseLong(month);

                Long weekLong = Long.parseLong(week);

                final List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)),
                                new Document("$group",
                                                new Document("_id",
                                                                new Document("blogId", "$_id")
                                                                                .append("btag", "$btag")
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
                                new Document("$unwind",
                                                new Document("path", "$_id.subject")
                                                                .append("preserveNullAndEmptyArrays", true)),
                                new Document("$sort",
                                                new Document("_id.numberOfLike", -1L)),
                                new Document("$match",
                                                new Document("_id.btag.tagName", tag)),
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

        @Override
        public List<String> findBlogContainTag(String tagName) {

                List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("index", "blog")
                                                .append("text",
                                                                new Document("query", tagName)
                                                                                .append("path", "btag.tagName"))),
                                new Document("$match",
                                                new Document("deleted", false)),
                                new Document("$unwind",
                                                new Document("path", "$btag")
                                                                .append("includeArrayIndex", "index")
                                                                .append("preserveNullAndEmptyArrays", false)),
                                new Document("$match",
                                                new Document("btag.tagName", tagName)),
                                new Document("$group",
                                                new Document("_id",
                                                                new Document("blogId", "$_id")
                                                                                .append("index", "$index"))),
                                new Document("$project",
                                                new Document("_id", "$_id.blogId"))));

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
        public Integer getIndexTagByBlog(String tagName, String blogId) {

                List<String> test = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("index", "blog")
                                                .append("text",
                                                                new Document("query", tagName)
                                                                                .append("path", "btag.tagName"))),
                                new Document("$match",
                                                new Document("deleted", false)
                                                                .append("_id",
                                                                                new ObjectId(blogId))),
                                new Document("$unwind",
                                                new Document("path", "$btag")
                                                                .append("includeArrayIndex", "index")
                                                                .append("preserveNullAndEmptyArrays", false)),
                                new Document("$match",
                                                new Document("btag.tagName", tagName)),
                                new Document("$project",
                                                new Document("_id", "$_id")
                                                                .append("index", "$index")),
                                new Document("$unset", "_id")));

                result.forEach((doc) -> {
                        ObjectMapper objectMapper = new ObjectMapper();

                        // Parse the JSON string
                        JsonNode jsonNode;
                        try {
                                jsonNode = objectMapper.readTree(converter.read(String.class, doc));
                                // Access the value associated with "$oid"
                                String oidValue = jsonNode.get("index")
                                                // .get("$oid")
                                                .asText();
                                test.add(oidValue);
                        } catch (JsonMappingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (JsonProcessingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                });
                // Integer index = count.get(0);

                return Integer.parseInt(test.get(0));
        }

        @Override
        public List<String> findBlogContainSubject(String subjectName) {

                List<String> blogs = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("index", "blog")
                                                .append("text",
                                                                new Document("query", subjectName)
                                                                                .append("path", "subject.subjectName"))),
                                new Document("$match",
                                                new Document("deleted", false)),
                                new Document("$unwind",
                                                new Document("path", "$subject")
                                                                .append("includeArrayIndex", "index")
                                                                .append("preserveNullAndEmptyArrays", false)),
                                new Document("$match",
                                                new Document("subject.subjectName", subjectName)),
                                new Document("$group",
                                                new Document("_id",
                                                                new Document("blogId", "$_id")
                                                                                .append("index", "$index"))),
                                new Document("$project",
                                                new Document("_id", "$_id.blogId"))));

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
        public Integer getIndexSubjectByBlog(String subjectName, String blogId) {

                List<String> test = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("index", "blog")
                                                .append("text",
                                                                new Document("query", subjectName)
                                                                                .append("path", "subject.subjectName"))),
                                new Document("$match",
                                                new Document("deleted", false)
                                                                .append("_id",
                                                                                new ObjectId(blogId))),
                                new Document("$unwind",
                                                new Document("path", "$subject")
                                                                .append("includeArrayIndex", "index")
                                                                .append("preserveNullAndEmptyArrays", false)),
                                new Document("$match",
                                                new Document("subject.subjectName", subjectName)),
                                new Document("$project",
                                                new Document("_id", "$_id")
                                                                .append("index", "$index")),
                                new Document("$unset", "_id")));

                result.forEach((doc) -> {
                        ObjectMapper objectMapper = new ObjectMapper();

                        // Parse the JSON string
                        JsonNode jsonNode;
                        try {
                                jsonNode = objectMapper.readTree(converter.read(String.class, doc));
                                // Access the value associated with "$oid"
                                String oidValue = jsonNode.get("index")
                                                // .get("$oid")
                                                .asText();
                                test.add(oidValue);
                        } catch (JsonMappingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (JsonProcessingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                });
                // Integer index = count.get(0);

                return Integer.parseInt(test.get(0));
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
                                                                                                .append("path", Arrays
                                                                                                                .asList("userrname",
                                                                                                                                "fullname"))
                                                                                                .append("fuzzy",
                                                                                                                new Document("maxEdits",
                                                                                                                                1L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                4L)))),
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
                                                                                                                                1L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                4L)))),

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
        public List<String> findRecommendedUser(String search, String currentUser) {
                final List<String> users = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result;
                if ("all".equals(search)) {
                        result = collection.aggregate(Arrays.asList(new Document("$match",
                                        new Document("status", true)
                                                        .append("userId",
                                                                        new Document("$ne", currentUser))),
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
                                                        new Document("status", true)
                                                                        .append("userId",
                                                                                        new Document("$ne",
                                                                                                        currentUser))),
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

                final List<String> tags = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result;
                if ("all".equals(limit)) {

                        result = collection.aggregate(Arrays.asList(
                                        new Document("$match",
                                                        new Document("status", true)),
                                        new Document("$unwind",
                                                        new Document("path", "$btag").append(
                                                                        "preserveNullAndEmptyArrays",
                                                                        false)),
                                        new Document("$match",
                                                        new Document("btag.status", true)),
                                        new Document("$sortByCount", "$btag._id")));
                } else {
                        Long limitLong = Long.parseLong(limit);

                        result = collection.aggregate(Arrays.asList(
                                        new Document("$match",
                                                        new Document("status", true)),
                                        new Document("$unwind",
                                                        new Document("path", "$btag").append(
                                                                        "preserveNullAndEmptyArrays",
                                                                        false)),
                                        new Document("$match",
                                                        new Document("btag.status", true)),
                                        new Document("$sortByCount", "$btag._id"),
                                        new Document("$limit", limitLong)));
                }

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
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)
                                                .append("btag.tagName", tagName))));
                result.forEach((doc) -> count.add(converter.read(BTag.class, doc)));

                Integer tagCount = count.size();

                return tagCount;
        }

        @Override
        public List<String> findTagByBlog(String blogId) {

                List<String> tags = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("_id",
                                                new ObjectId(blogId))),
                                new Document("$unwind",
                                                new Document("path", "$btag")
                                                                .append("preserveNullAndEmptyArrays", false)),
                                new Document("$project",
                                                new Document("_id", "$btag._id"))));

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
        public BTag findTagByName(String text) {
                final List<BTag> tags = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("BTag");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                                new Document("$match",
                                                new Document("tagName", text))));

                result.forEach((doc) -> tags.add(converter.read(BTag.class, doc)));

                final BTag test = tags.get(0);

                return test;
        }

        @Override
        public List<BTag> findTagListByName(String text) {
                final List<BTag> tags = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("BTag");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(

                                new Document("$search",
                                                new Document("index", "btag")
                                                                .append("text",
                                                                                new Document("query", text)
                                                                                                .append("path", Arrays
                                                                                                                .asList("tagName"))
                                                                                                .append("fuzzy",
                                                                                                                new Document("maxEdits",
                                                                                                                                1L)
                                                                                                                                .append("prefixLength",
                                                                                                                                                4L)))),

                                new Document("$match",
                                                new Document("status", true))));

                result.forEach((doc) -> tags.add(converter.read(BTag.class, doc)));

                return tags;
        }

        /*
         * SUBJECT METHOD
         */

        @Override
        // Query to find most used tags and its used count
        public List<String> findMostUsedSubject(String limit) {

                final List<String> subjects = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result;
                if ("all".equals(limit)) {

                        result = collection.aggregate(Arrays.asList(
                                        new Document("$match",
                                                        new Document("status", true)),
                                        new Document("$unwind",
                                                        new Document("path", "$subject").append(
                                                                        "preserveNullAndEmptyArrays",
                                                                        false)),
                                        new Document("$match",
                                                        new Document("subject.status", true)),
                                        new Document("$sortByCount", "$subject._id")));
                } else {
                        Long limitLong = Long.parseLong(limit);

                        result = collection.aggregate(Arrays.asList(
                                        new Document("$match",
                                                        new Document("status", true)),
                                        new Document("$unwind",
                                                        new Document("path", "$subject").append(
                                                                        "preserveNullAndEmptyArrays",
                                                                        false)),
                                        new Document("$match",
                                                        new Document("subject.status", true)),
                                        new Document("$sortByCount", "$subject._id"),
                                        new Document("$limit", limitLong)));
                }

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
                                subjects.add(oidValue);
                        } catch (JsonMappingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (JsonProcessingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                });

                return subjects;
        }

        @Override
        public Integer findMostUsedSubjectCount(String subjectName) {

                final List<Subject> count = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)
                                                .append("subject.subjectName", subjectName))));
                result.forEach((doc) -> count.add(converter.read(Subject.class, doc)));

                Integer subjectCount = count.size();

                return subjectCount;
        }

        @Override
        public List<String> findSubjectByBlog(String blogId) {

                List<String> subjects = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Blog");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("_id",
                                                new ObjectId(blogId))),
                                new Document("$unwind",
                                                new Document("path", "$subject")
                                                                .append("preserveNullAndEmptyArrays", false)),
                                new Document("$project",
                                                new Document("_id", "$subject._id"))));

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
                                subjects.add(oidValue);
                        } catch (JsonMappingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (JsonProcessingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                });

                return subjects;
        }

        @Override
        public Subject findSubjectByName(String text) {
                final List<Subject> subjects = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Subject");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                                new Document("$match",
                                                new Document("subjectName", text))));

                result.forEach((doc) -> subjects.add(converter.read(Subject.class, doc)));

                return subjects.get(0);
        }

        @Override
        public List<Subject> findSubjectListByName(String text) {
                final List<Subject> subjects = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Subject");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                                new Document("$match",
                                                new Document("status", true)
                                                                .append("subjectName", text))));

                result.forEach((doc) -> subjects.add(converter.read(Subject.class, doc)));

                return subjects;
        }

        /*
         * COMMENT METHOD
         */

        @Override
        public List<String> findMostLikedCommentByBlog(String blogId) {

                List<String> comments = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Comment");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)
                                                .append("blogId", blogId)),
                                new Document("$unwind",
                                                new Document("path", "$like")
                                                                .append("preserveNullAndEmptyArrays", true)),
                                new Document("$sortByCount", "$_id"),
                                new Document("$unset", "count")));

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
                                comments.add(oidValue);
                        } catch (JsonMappingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (JsonProcessingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                });

                return comments;
        }

        @Override
        public List<String> sortLatestComment(String blogId) {

                List<String> comments = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Comment");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                                new Document("status", true)
                                                .append("blogId", blogId)),
                                new Document("$sort",
                                                new Document("uploadDate", -1L))));

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
                                comments.add(oidValue);
                        } catch (JsonMappingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (JsonProcessingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                });

                return comments;
        }

        /*
         * FOLLOW METHOD
         */

        @Override
        public List<String> findFollowerCount(String userId) {

                List<String> followers = new ArrayList<>();

                MongoDatabase database = client.getDatabase("Main");
                MongoCollection<Document> collection = database.getCollection("Follow");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$unwind",
                                new Document("path", "$followedUser")
                                                .append("preserveNullAndEmptyArrays", false)),
                                new Document("$match",
                                                new Document("followedUser", userId)),
                                new Document("$unset", Arrays.asList("_class", "followedUser"))));

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
                                followers.add(oidValue);
                        } catch (JsonMappingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (JsonProcessingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                });                
                return followers;
        }

}
