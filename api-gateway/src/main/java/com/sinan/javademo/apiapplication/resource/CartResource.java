package com.sinan.javademo.apiapplication.resource;

import com.sinan.javademo.apiapplication.model.CartCheckoutResponse;
import com.sinan.javademo.apiapplication.model.CartDetailsResponse;
import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.service.CartService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource extends SMSResource {

    private final CartService cartService = new CartService();

    public CartResource() {
    }

    @POST
    public Response createCart(List<String> items) {
        Cart cart = cartService.createCart(items);
        CartDetailsResponse response = new CartDetailsResponse(cart);
        return Response.status(Response.Status.CREATED).entity(gson.toJson(response)).build();
    }

    @POST
    @Path("/checkout/{id}")
    public Response checkoutCart(@PathParam("id") String cartId) {
        Cart cart = cartService.checkoutCart(cartId);
        CartCheckoutResponse response = new CartCheckoutResponse(cart);
        return Response.status(Response.Status.OK).entity(gson.toJson(response)).build();
    }

    @GET
    @Path("{id}")
    public Response getCartDetails(@PathParam("id") String cartId) {
        Cart cart = cartService.getCartInfo(cartId);
        CartDetailsResponse response = new CartDetailsResponse(cart);
        return Response.status(Response.Status.OK).entity(gson.toJson(response)).build();

    }


}
