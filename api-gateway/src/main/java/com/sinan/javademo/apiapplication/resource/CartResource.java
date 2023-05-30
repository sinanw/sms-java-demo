package com.sinan.javademo.apiapplication.resource;

import com.sinan.javademo.apiapplication.exception.InvalidEmptyCartException;
import com.sinan.javademo.apiapplication.model.CartCheckoutResponse;
import com.sinan.javademo.smscore.model.Cart;
import com.sinan.javademo.smscore.service.CartService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cart")
public class CartResource extends SMSResource {

    private final CartService cartService = new CartService();

    public CartResource() {
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCart(List<String> items) {
        if (items.size() == 0) {
            throw new InvalidEmptyCartException();
        }

        Cart cart = cartService.createCart(items);
        cartService.checkoutCart(cart);
        CartCheckoutResponse cartCheckoutResponse = new CartCheckoutResponse(cart);
        return Response.status(Response.Status.CREATED).entity(gson.toJson(cartCheckoutResponse)).build();
    }


}
