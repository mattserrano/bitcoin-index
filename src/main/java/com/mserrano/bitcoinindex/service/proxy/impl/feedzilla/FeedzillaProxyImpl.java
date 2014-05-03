package com.mserrano.bitcoinindex.service.proxy.impl.feedzilla;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mserrano.bitcoinindex.entity.Article;
import com.mserrano.bitcoinindex.service.proxy.NewsProxy;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
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
        FeedzillaArticlesDTO response = restTemplate.getForObject(getEndpointUri(), FeedzillaArticlesDTO.class, date);
        // filter out articles that do not match specified date - API limitation
        List<Article> articles = response.articles
                .stream()
                .filter(p -> LocalDate.parse(p.getDate(), DateTimeFormatter.RFC_1123_DATE_TIME)
                        .isEqual(LocalDate.parse(date)))
                .collect(Collectors.toList());
        // lazily normalize dates in articles to ISO_LOCAL_DATE
        return normalizeDates(articles);
    }

    private Collection<Article> normalizeDates(List<Article> articles) {
        ListIterator<Article> iterator = articles.listIterator();
        while (iterator.hasNext()) {
            Article a = iterator.next();
            a.setDate(LocalDate.parse(a.getDate(), DateTimeFormatter.RFC_1123_DATE_TIME)
                    .format(DateTimeFormatter.ISO_LOCAL_DATE));
            iterator.set(a);
        }

        return articles;
    }
}

