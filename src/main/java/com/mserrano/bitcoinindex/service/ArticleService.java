package com.mserrano.bitcoinindex.service;

import com.mserrano.bitcoinindex.entity.Article;
import com.mserrano.bitcoinindex.repository.ArticleRepository;
import com.mserrano.bitcoinindex.service.proxy.NewsProxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Matthew Serrano
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository repository;
    @Autowired
    private NewsProxy newsProxy;
    private static Log log = LogFactory.getLog(ArticleService.class);

    public Article getArticle(String id) {
        return repository.findOne(id);
    }

    public Collection<Article> getArticlesByDate(String date) {
        Collection<Article> articles = repository.findByDate(date);
        if (articles.isEmpty()) {
            log.info("No persisted articles found, fetching articles.");
            articles = refreshArticles(date);
        }

        log.info("Retrieved " + articles.size() + " persisted articles.");
        return articles;
    }

    public Collection<Article> refreshArticles(String date) {
        Collection<Article> articles = newsProxy.retrieveArticlesByDate(date);
        log.info("Fetched " + articles.size() + " articles.");
        repository.save(articles);
        log.info("Saved " + articles.size() + " articles.");
        return articles;
    }
}
