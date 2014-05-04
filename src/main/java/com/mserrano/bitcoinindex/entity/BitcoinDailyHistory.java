package com.mserrano.bitcoinindex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

/**
 * @author Matthew Serrano
 */
public class BitcoinDailyHistory {
    @JsonIgnore
    @Id
    private String id;
    private double high;
    private double low;
    private double average;
    private double volume;
    private String date;

    public BitcoinDailyHistory() { }

    public BitcoinDailyHistory(double high, double low, double average, double volume, String date) {
        this.high = high;
        this.low = low;
        this.average = average;
        this.volume = volume;
        this.date = date;
    }

    public String getId() {
        return this.id;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BitcoinDailyHistory{" +
                "high=" + high +
                ", low=" + low +
                ", average=" + average +
                ", volume=" + volume +
                ", date='" + date + '\'' +
                '}';
    }
}
