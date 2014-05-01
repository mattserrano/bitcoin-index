package com.mserrano.bitcoinindex.service;

import com.mserrano.bitcoinindex.entity.Article;
import com.mserrano.bitcoinindex.repository.ArticleRepository;
import com.mserrano.bitcoinindex.service.proxy.NewsProxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Collection;

/**
 * @author Matthew Serrano
 */
public class ArticleService {
    @Autowired
    private ArticleRepository repository;
    @Autowired
    private NewsProxy newsProxy;

    public void createArticle(String title, String author, String source, String sourceUrl, String publishDate) {
        Article article = new Article();
        article.setTitle(title);
        article.setAuthor(author);
        article.setSource(source);
        article.setSourceUrl(sourceUrl);
        article.setDate(publishDate);
        repository.save(article);
    }

    public Article getArticle(String id) {
        return repository.findOne(id);
    }

    public Collection<Article> getArticlesByDate(LocalDate date) {
        Collection<Article> articles = repository.findByDate(date);
        if (articles.isEmpty()) {
            articles = refreshArticles(date);
        }
        return articles;
    }

    public Collection<Article> refreshArticles(LocalDate date) {
        Collection<Article> articles = newsProxy.retrieveArticlesByDate(date.toString());
        repository.save(articles);
        return articles;
    }
}
