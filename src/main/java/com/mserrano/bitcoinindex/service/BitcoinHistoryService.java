package com.mserrano.bitcoinindex.service;

import com.mserrano.bitcoinindex.entity.BitcoinDailyHistory;
import com.mserrano.bitcoinindex.repository.BitcoinRepository;
import com.mserrano.bitcoinindex.service.proxy.BitcoinHistoryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Matthew Serrano
 */
@Service
public class BitcoinHistoryService {
    @Autowired
    private BitcoinRepository bitcoinRepository;
    @Autowired
    private BitcoinHistoryProxy historyProxy;

    public BitcoinDailyHistory getHistoryByDate(String date) {
        BitcoinDailyHistory btcDailyHistory = bitcoinRepository.findByDate(date);
        if (btcDailyHistory == null) {
            btcDailyHistory = refreshBitcoinHistory(date);
        }

        return btcDailyHistory;
    }

    private BitcoinDailyHistory refreshBitcoinHistory(String date) {
        BitcoinDailyHistory btcDailyHistory = historyProxy.getDailyHistoryByDate(date);
        bitcoinRepository.save(btcDailyHistory);
        return btcDailyHistory;
    }
}
