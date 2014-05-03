package com.mserrano.bitcoinindex.repository;

import com.mserrano.bitcoinindex.entity.BitcoinDailyHistory;

/**
 * @author Matthew Serrano
 */
public interface BitcoinRepository {
    public BitcoinDailyHistory findByDate(String date);
}
