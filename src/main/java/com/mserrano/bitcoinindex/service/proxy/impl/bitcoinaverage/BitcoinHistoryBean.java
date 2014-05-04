package com.mserrano.bitcoinindex.service.proxy.impl.bitcoinaverage;

import java.util.Date;

/**
 * @author Matthew Serrano
 */
public class BitcoinHistoryBean {
    private Date datetime;
    private double high;
    private double low;
    private double average;
    private double volume;

    public BitcoinHistoryBean() {
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitcoinHistoryBean that = (BitcoinHistoryBean) o;

        if (Double.compare(that.average, average) != 0) return false;
        if (Double.compare(that.high, high) != 0) return false;
        if (Double.compare(that.low, low) != 0) return false;
        if (Double.compare(that.volume, volume) != 0) return false;
        if (!datetime.equals(that.datetime)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = datetime.hashCode();
        temp = Double.doubleToLongBits(high);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(low);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(average);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(volume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "BitcoinHistoryBean{" +
                "datetime='" + datetime.toString() + '\'' +
                ", high=" + high +
                ", low=" + low +
                ", average=" + average +
                ", volume=" + volume +
                '}';
    }
}
