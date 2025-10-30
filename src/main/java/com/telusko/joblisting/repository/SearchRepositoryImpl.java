package com.telusko.joblisting.repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.telusko.joblisting.model.Post;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class SearchRepositoryImpl implements SearchRepository{

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByText(String text) {

        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("telusko");
        MongoCollection<Document> collection = database.getCollection("JobPost");

//        FYI, Both the below logic gives the same result for the dat in MongoDB now
        //        My Telusko Data example
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                        new Document("query", text)
                        .append("path", Arrays.asList("techs", "desc", "profile")))),
                        new Document("$sort",
                        new Document("exp", 1L)),
                        new Document("$limit", 5L)));

//        My Collection Data example
//        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
//                new Document("$search",
//                        new Document("index", "default")
//                                .append("text",
//                                        new Document("query", "java")
//                                                .append("path", "techs")
//                                )
//                )
//        ));

        result.forEach(doc -> posts.add(converter.read(Post.class,doc)));
        System.out.println("search result"+posts);
        return posts;
    }
}

