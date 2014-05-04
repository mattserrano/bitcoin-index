package com.mserrano.bitcoinindex.controller;

import com.mserrano.bitcoinindex.entity.BitcoinDailyHistory;
import com.mserrano.bitcoinindex.service.BitcoinHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matthew Serrano
 */
@RestController
@RequestMapping("/btcHistory")
public class BitcoinHistoryController {
    @Autowired
    private BitcoinHistoryService btcService;

    @RequestMapping("/{date}")
    public BitcoinDailyHistory getBtcHistoryByDate(@PathVariable
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                   String date) {
        return btcService.getHistoryByDate(date);
    }
}
