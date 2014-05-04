package com.mserrano.bitcoinindex;

import com.mserrano.bitcoinindex.entity.BitcoinDailyHistory;
import com.mserrano.bitcoinindex.repository.ArticleRepository;
import com.mserrano.bitcoinindex.repository.BitcoinRepository;
import com.mserrano.bitcoinindex.service.ArticleService;
import com.mserrano.bitcoinindex.service.BitcoinHistoryService;
import com.mserrano.bitcoinindex.service.proxy.BitcoinHistoryProxy;
import com.mserrano.bitcoinindex.service.proxy.NewsProxy;
import com.mserrano.bitcoinindex.service.proxy.impl.bitcoinaverage.BitcoinAverageProxyImpl;
import com.mserrano.bitcoinindex.service.proxy.impl.feedzilla.FeedzillaProxyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Matthew Serrano
 */
@ComponentScan
@EnableAutoConfiguration
public class Application {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private BitcoinRepository btcRepository;

    @Bean
    public ArticleService articleService() {
        return new ArticleService();
    }

    @Bean
    public NewsProxy newsProxy() {
        return new FeedzillaProxyImpl();
    }

    @Bean
    public BitcoinHistoryService bitcoinHistoryService() {
        return new BitcoinHistoryService();
    }

    @Bean
    public BitcoinHistoryProxy bitcoinHistoryProxy() {
        return new BitcoinAverageProxyImpl("USD");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
