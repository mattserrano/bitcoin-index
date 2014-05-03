package com.mserrano.bitcoinindex;

import com.mserrano.bitcoinindex.repository.ArticleRepository;
import com.mserrano.bitcoinindex.service.ArticleService;
import com.mserrano.bitcoinindex.service.proxy.NewsProxy;
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

    @Bean
    public ArticleService articleService() {
        return new ArticleService();
    }

    @Bean
    public NewsProxy newsProxy() {
        return new FeedzillaProxyImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
