package com.mserrano.bitcoinindex.service.proxy.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mserrano.bitcoinindex.entity.Article;
import com.mserrano.bitcoinindex.service.proxy.NewsProxy;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Matthew Serrano
 */
public class FeedzillaProxyImpl implements NewsProxy {
    private final String endpoint = "http://api.feedzilla.com/v1/articles/search.json";
    private final String parameters = "?q=Bitcoin&order=date&since={date}";
    private RestTemplate restTemplate = new RestTemplate();


    @Override
    public String getEndpointUri() {
        return endpoint + parameters;
    }

    @Override
    public Collection<Article> retrieveArticlesByDate(String date) {
        Articles response = restTemplate.getForObject(getEndpointUri(), Articles.class, date);
        // filter out articles that do not match specified date - API limitation
        List<Article> articles = response.getArticles()
                .stream()
                .filter(p -> LocalDate.parse(p.getDate(), DateTimeFormatter.RFC_1123_DATE_TIME)
                        .isEqual(LocalDate.parse(date)))
                .collect(Collectors.toCollection(ArrayList::new));
        return articles;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Articles {
    protected Collection<Article> articles;

    public Collection<Article> getArticles() {
        return articles;
    }
}