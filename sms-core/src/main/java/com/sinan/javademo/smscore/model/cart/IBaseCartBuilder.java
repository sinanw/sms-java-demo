package com.sinan.javademo.smscore.model.cart;

import java.util.List;

public interface IBaseCartBuilder {
    void populateItems(List<String> items);

    void reset();
}
