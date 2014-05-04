package com.mserrano.bitcoinindex.service.proxy;

import com.mserrano.bitcoinindex.entity.BitcoinDailyHistory;

import java.util.Map;

/**
 * @author Matthew Serrano
 */
public interface BitcoinHistoryProxy extends AbstractProxy {
    public Map<String, BitcoinDailyHistory> retrieveDailyHistory();
    public BitcoinDailyHistory getDailyHistoryByDate(String date);
}
