package com.mserrano.bitcoinindex.service.proxy;

import com.mserrano.bitcoinindex.entity.Article;

import java.util.Collection;

/**
 * @author Matthew Serrano
 */
public interface NewsProxy extends AbstractProxy {
    public Collection<Article> retrieveArticlesByDate(String date);
}
