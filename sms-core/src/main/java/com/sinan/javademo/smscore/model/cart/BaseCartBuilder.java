package com.sinan.javademo.smscore.model.cart;

import java.util.List;

public interface BaseCartBuilder {
    void populateItems(List<String> items);
    void reset();
}
