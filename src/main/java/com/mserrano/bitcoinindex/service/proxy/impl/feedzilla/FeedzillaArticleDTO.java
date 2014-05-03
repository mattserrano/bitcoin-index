package com.mserrano.bitcoinindex.service.proxy.impl.feedzilla;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mserrano.bitcoinindex.entity.Article;

/**
 * @author Matthew Serrano
 */
class FeedzillaArticleDTO extends Article {
    @JsonProperty("source_url")
    public String getSource() {
        return super.getSource();
    }

    @JsonProperty("publish_date")
    public String getDate() {
        return super.getDate();
    }
}
