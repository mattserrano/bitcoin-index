package com.mserrano.bitcoinindex.service.proxy.impl.feedzilla;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;

/**
 * @author Matthew Serrano
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class FeedzillaArticlesDTO {
    public Collection<FeedzillaArticleDTO> articles;
}
