package com.sinan.javademo.smscore.model;


public abstract class Offer {
    private String title;

    public Offer(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract boolean isActive();

    public abstract boolean isApplicable(Cart cart);
    public abstract AppliedOfferResult apply(Cart cart);
}
