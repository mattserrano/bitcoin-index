package com.mserrano.bitcoinindex.repository;

import com.mserrano.bitcoinindex.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Collection;

/**
 * @author Matthew Serrano
 */
public interface ArticleRepository extends MongoRepository<Article, String> {
    public Article findByTitle(String title);
    public Collection<Article> findByAuthor(String author);
    public Collection<Article> findByDate(String date);
}
