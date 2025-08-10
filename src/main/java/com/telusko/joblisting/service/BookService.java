package com.telusko.joblisting.service;
import com.telusko.joblisting.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Book> getHighRatedTechnologyBooks() {
        Query query = new Query();
        query.addCriteria(Criteria.where("genre").is("Technology")
                .and("rating").gt(4));
        System.out.println("mongoTemplate.find(query, Book.class);"+mongoTemplate.find(query, Book.class));
        return mongoTemplate.find(query, Book.class);
    }
}
