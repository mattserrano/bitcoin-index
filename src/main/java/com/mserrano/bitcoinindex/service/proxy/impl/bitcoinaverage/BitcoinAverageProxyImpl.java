package com.mserrano.bitcoinindex.service.proxy.impl.bitcoinaverage;

import com.mserrano.bitcoinindex.entity.BitcoinDailyHistory;
import com.mserrano.bitcoinindex.service.proxy.BitcoinHistoryProxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Matthew Serrano
 */
public class BitcoinAverageProxyImpl implements BitcoinHistoryProxy {
    private String currency;
    private Map<String, BitcoinDailyHistory> dailyHistoryMap = new LinkedHashMap<>();
    private static final String ENDPOINT = "https://api.bitcoinaverage.com/history/";
    private static final String ENDPOINT_SUFFIX = "/per_day_all_time_history.csv";
    private static final String CSV_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static Log log = LogFactory.getLog(BitcoinAverageProxyImpl.class);

    public BitcoinAverageProxyImpl() {
    }

    public BitcoinAverageProxyImpl(String currency) {
        this.currency = currency;
    }

    @Override
    public String getEndpointUri() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(ENDPOINT).append(this.currency).append(ENDPOINT_SUFFIX).toString();
    }

    @Override
    public BitcoinDailyHistory getDailyHistoryByDate(String date) {
        if (dailyHistoryMap.isEmpty()) {
            retrieveDailyHistory();
        }

        return dailyHistoryMap.get(date);
    }
    @Override
    public Map<String, BitcoinDailyHistory> retrieveDailyHistory() {
        try {
            this.dailyHistoryMap = retrieveCsv();
        } catch (IOException|ParseException ex) {
            log.error("Could not retrieve CSV from " + getEndpointUri(), ex);
        }

        if (this.dailyHistoryMap == null) {
            return Collections.EMPTY_MAP;
        }

        return this.dailyHistoryMap;
    }

    private Map<String, BitcoinDailyHistory> retrieveCsv() throws IOException, ParseException {
        final URL url = new URL(getEndpointUri());
        URLConnection connection = url.openConnection();
        ICsvBeanReader beanReader = new CsvBeanReader(
                new InputStreamReader(connection.getInputStream()),
                CsvPreference.STANDARD_PREFERENCE);
        final String[] header = beanReader.getHeader(true);
        BitcoinHistoryBean btcHistoryBean;
        while ((btcHistoryBean = beanReader.read(BitcoinHistoryBean.class,
                header,
                getProcessors())
        ) != null) {
            BitcoinDailyHistory btcDailyHistory = populateBitcoinDailyHistory(btcHistoryBean);
            String key = btcDailyHistory.getDate();
            dailyHistoryMap.put(key, btcDailyHistory);
        }

        return dailyHistoryMap;
    }

    private static CellProcessor[] getProcessors() {
        final CellProcessor[] processors = new CellProcessor[]{
                new ParseDate(CSV_DATE_TIME_FORMAT),    // datetime
                new Optional(new ParseDouble()),        // high
                new Optional(new ParseDouble()),        // low
                new ParseDouble(),                      // average
                new ParseDouble()                       // volume
        };

        return processors;
    }

    private static BitcoinDailyHistory populateBitcoinDailyHistory(BitcoinHistoryBean btcHistoryBean)
            throws ParseException {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(btcHistoryBean.getDatetime());
        double high = btcHistoryBean.getHigh();
        double low = btcHistoryBean.getLow();
        double average = btcHistoryBean.getAverage();
        double volume = btcHistoryBean.getVolume();
        return new BitcoinDailyHistory(high, low, average, volume, date);
    }
}

