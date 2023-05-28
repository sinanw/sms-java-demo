package com.sinan.javademo.smscore.model;

import java.time.LocalDateTime;

public abstract class ProductOffer extends Offer{

    private LocalDateTime startTime, endTime;
    protected Item item;
    private boolean isTimeLimited;
    protected double percentage;

    public ProductOffer(String title, Item item, double percentage) {
        super(title);
        this.item = item;
        this.percentage = percentage;
        this.isTimeLimited = false;
    }

    public void setTimeRange(LocalDateTime startTime, LocalDateTime endTime){
        this.isTimeLimited = true;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ProductOffer(String title, Item item, double percentage, LocalDateTime startTime, LocalDateTime endTime) {
        super(title);
        this.item = item;
        this.percentage = percentage;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public final boolean isActive() {
        if (!isTimeLimited){
            return true;
        }
        LocalDateTime now = LocalDateTime.now();
        return (startTime.compareTo(now)<=0 && endTime.compareTo(now)>=0);
    }
}
