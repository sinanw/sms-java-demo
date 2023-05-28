package com.sinan.javademo.apiapplication.resource;

import com.sinan.javademo.apiapplication.model.APIError;
import com.sinan.javademo.apiapplication.model.CartCheckoutResponse;
import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.Cart;
import com.sinan.javademo.smscore.service.CartService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/cart")
public class CartResource extends SMSResource{

    private final CartService cartService = new CartService();

    public CartResource() {}

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCart(List<String> items){
        try{
            Cart cart = cartService.createCart(items);
            double subTotalPrice = cart.getTotalPrice();
            List<String> offers = new ArrayList<>();
            double totalPrice = cartService.checkoutCart(cart, offers);
            CartCheckoutResponse cartCheckoutResponse = new CartCheckoutResponse(subTotalPrice,totalPrice,offers);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(cartCheckoutResponse)).build();
        }catch (ItemNotFoundException ex){
            APIError apiError = new APIError(ex.getMessage(),"This error happened when we couldn't find the required item in our system, please check the items list and make sure you are selecting valid item names");
            return Response.status(Response.Status.NOT_FOUND).entity(gson.toJson(apiError)).build();
        }
    }


}
