package com.mserrano.bitcoinindex.service;

import com.mserrano.bitcoinindex.entity.Article;
import com.mserrano.bitcoinindex.repository.ArticleRepository;
import com.mserrano.bitcoinindex.service.proxy.NewsProxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author Matthew Serrano
 */
public class ArticleService {
    @Autowired
    private ArticleRepository repository;
    @Autowired
    private NewsProxy newsProxy;

    public Article getArticle(String id) {
        return repository.findOne(id);
    }

    public Collection<Article> getArticlesByDate(String date) {
        Collection<Article> articles = repository.findByDate(date);
        if (articles.isEmpty()) {
            articles = refreshArticles(date);
        }
        return articles;
    }

    public Collection<Article> refreshArticles(String date) {
        Collection<Article> articles = newsProxy.retrieveArticlesByDate(date);
        repository.save(articles);
        return articles;
    }
}
