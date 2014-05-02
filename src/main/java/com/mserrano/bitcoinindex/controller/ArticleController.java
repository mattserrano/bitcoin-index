package com.mserrano.bitcoinindex.controller;

import com.mserrano.bitcoinindex.entity.Article;
import com.mserrano.bitcoinindex.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

/**
 * @author Matthew Serrano
 */
@RestController
@RequestMapping("/news")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/{date}")
    public Collection<Article> getArticlesByDate(@PathVariable
                                                     @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
                                                     String date) {
        return articleService.getArticlesByDate(date);
    }
}
