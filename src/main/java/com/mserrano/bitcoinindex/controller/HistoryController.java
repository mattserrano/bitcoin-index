package com.mserrano.bitcoinindex.controller;

import com.mserrano.bitcoinindex.entity.Article;
import com.mserrano.bitcoinindex.entity.BitcoinDailyHistory;
import com.mserrano.bitcoinindex.service.ArticleService;
import com.mserrano.bitcoinindex.service.BitcoinHistoryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Matthew Serrano
 */
@RestController
@RequestMapping("/api")
public class HistoryController {
    @Autowired
    private BitcoinHistoryService btcService;
    @Autowired
    private ArticleService articleService;
    private static Log log = LogFactory.getLog(HistoryController.class);

    @RequestMapping("/{date}")
    public Map<BitcoinDailyHistory, Collection<Article>> getBtcHistoryWithArticlesByDate(@PathVariable
                                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                                         String date) {
        BitcoinDailyHistory btcDailyHistory = btcService.getHistoryByDate(date);
        Collection<Article> articles =  articleService.getArticlesByDate(date);
        Map<BitcoinDailyHistory, Collection<Article>> btcHistoryMap = new HashMap<>();
        if (btcDailyHistory == null || articles == null ) {
            log.warn("Couldn't retrieve data for date " + date);
        }
        btcHistoryMap.put(btcDailyHistory, articles);
        return btcHistoryMap;
    }

    @RequestMapping("/{date}/price")
    public BitcoinDailyHistory getBtcDailyHistoryByDate(@PathVariable
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        String date) {
        return btcService.getHistoryByDate(date);
    }


    @RequestMapping("/{date}/news")
    public Collection<Article> getArticlesByDate(@PathVariable
                                                 @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
                                                 String date) {
        return articleService.getArticlesByDate(date);
    }

}
