package com.telusko.joblisting.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "books")
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private String genre;
    private List<String> tags;
    private double rating;
    private int publishedYear;
    private String summary;
    private boolean available;

}


