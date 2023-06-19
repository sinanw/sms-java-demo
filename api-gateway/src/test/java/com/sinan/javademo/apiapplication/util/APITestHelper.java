package com.sinan.javademo.apiapplication.util;

import com.google.gson.Gson;
import com.sinan.javademo.apiapplication.contract.CartCheckoutResponse;
import com.sinan.javademo.apiapplication.contract.CartDetailsResponse;
import jakarta.ws.rs.core.Response;

public final class APITestHelper {

    public static CartDetailsResponse parseCartDetailsResponse(Response response) {
        return new Gson().fromJson((String) response.getEntity(), CartDetailsResponse.class);
    }

    public static CartCheckoutResponse parseCartCheckoutResponse(Response response) {
        return new Gson().fromJson((String) response.getEntity(), CartCheckoutResponse.class);
    }
}
