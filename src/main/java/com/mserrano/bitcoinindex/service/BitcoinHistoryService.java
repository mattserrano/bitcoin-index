package com.mserrano.bitcoinindex.service;

import com.mserrano.bitcoinindex.entity.BitcoinDailyHistory;
import com.mserrano.bitcoinindex.repository.BitcoinRepository;
import com.mserrano.bitcoinindex.service.proxy.BitcoinHistoryProxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Matthew Serrano
 */
@Service
public class BitcoinHistoryService {
    @Autowired
    private BitcoinRepository bitcoinRepository;
    @Autowired
    private BitcoinHistoryProxy historyProxy;
    private static Log log = LogFactory.getLog(BitcoinHistoryService.class);

    public BitcoinDailyHistory getHistoryByDate(String date) {
        BitcoinDailyHistory btcDailyHistory = bitcoinRepository.findByDate(date);
        if (btcDailyHistory == null) {
            log.info("No persisted BitcoinDailyHistory found, fetching articles.");
            btcDailyHistory = refreshBitcoinHistory(date);
            log.info("Retrieved BitcoinDailyHistory " + btcDailyHistory);
        }

        return btcDailyHistory;
    }

    private BitcoinDailyHistory refreshBitcoinHistory(String date) {
        Map<String, BitcoinDailyHistory> btcDailyHistoryMap = historyProxy.retrieveDailyHistory();
        for (Map.Entry<String, BitcoinDailyHistory> entry : btcDailyHistoryMap.entrySet()) {
            bitcoinRepository.save(entry.getValue());
        }
        return btcDailyHistoryMap.get(date);
    }
}
