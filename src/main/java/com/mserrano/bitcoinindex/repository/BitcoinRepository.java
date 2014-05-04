package com.mserrano.bitcoinindex.repository;

import com.mserrano.bitcoinindex.entity.BitcoinDailyHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Matthew Serrano
 */
public interface BitcoinRepository extends MongoRepository<BitcoinDailyHistory, String> {
    public BitcoinDailyHistory findByDate(String date);
}
