package com.sinan.javademo.apiapplication.resource;

import com.sinan.javademo.apiapplication.contract.CartCheckoutResponse;
import com.sinan.javademo.apiapplication.contract.CartDetailsResponse;
import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.service.CartService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.sinan.javademo.smscore.exception.*;

import java.util.List;

/**
 * The main resource to provide APIs to support the creation of shopping cart and manage its items.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@Path("/cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource extends SMSResource {

    /**
     * The cart service to provide backend logic for cart related functionalities.
     */
    private CartService cartService;

    @Inject
    public CartResource(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * An endpoint to create new cart with/without items.
     *
     * @param itemsIdentifiers a list of identifiers of the items to be added to the cart when created (<em>it may be empty</em>).
     * @return a response with the created cart details ({@link CartDetailsResponse}) in the response entity.
     * @throws ItemNotFoundException if one of item identifiers is not mapped to an existing item in the system.
     */
    @POST
    public Response createCart(List<String> itemsIdentifiers) {
        Cart cart = cartService.createCart(itemsIdentifiers);
        CartDetailsResponse response = new CartDetailsResponse(cart);
        return Response.status(Response.Status.CREATED).entity(gson.toJson(response)).build();
    }

    /**
     * An endpoint to retrieve cart details.
     *
     * @param cartId the cart identifier.
     * @return a response with cart details ({@link CartDetailsResponse}) in the response entity.
     * @throws CartNotFoundException if the cart identifier is not mapped to an existing cart in the system.
     */
    @GET
    @Path("{id}")
    public Response getCartDetails(@PathParam("id") String cartId) {
        Cart cart = cartService.getCartInfo(cartId);
        CartDetailsResponse response = new CartDetailsResponse(cart);
        return Response.status(Response.Status.OK).entity(gson.toJson(response)).build();
    }

    /**
     * An endpoint to add one unit of an item to the cart.
     *
     * @param cartId         the cart identifier.
     * @param itemIdentifier the item identifier.
     * @return a response with cart details ({@link CartDetailsResponse} with the item added/quantity increased) in the response entity.
     * @throws CartNotFoundException if the cart identifier is not mapped to an existing cart in the system.
     * @throws ItemNotFoundException if the item identifier is not mapped to an existing item in the system.
     */
    @POST
    @Path("/{cartId}/items")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response addItemUnit(@PathParam("cartId") String cartId, String itemIdentifier) {
        Cart cart = cartService.addItem(cartId, itemIdentifier);
        CartDetailsResponse response = new CartDetailsResponse(cart);
        return Response.status(Response.Status.OK).entity(gson.toJson(response)).build();
    }

    /**
     * An endpoint to remove one unit of an item from the cart.
     *
     * @param cartId         the cart identifier.
     * @param itemIdentifier the item identifier.
     * @return a response with cart details ({@link CartDetailsResponse} with the item removed/quantity decreased) in the response entity.
     * @throws CartNotFoundException     if the cart identifier is not mapped to an existing cart in the system.
     * @throws ItemNotFoundException     if the item identifier is not mapped to an existing item in the system.
     * @throws CartItemNotFoundException if the item identifier is not mapped to an item in the cart.
     */
    @DELETE
    @Path("/{cartId}/items/{itemId}")
    public Response removeItemUnit(@PathParam("cartId") String cartId, @PathParam("itemId") String itemIdentifier) {
        Cart cart = cartService.removeItem(cartId, itemIdentifier);
        CartDetailsResponse response = new CartDetailsResponse(cart);
        return Response.status(Response.Status.OK).entity(gson.toJson(response)).build();
    }

    /**
     * An endpoint to check out cart and get details about total price and applied discounts.
     *
     * @param cartId the cart identifier.
     * @return a response with cart summary and applied discounts ({@link CartCheckoutResponse}) in the response entity.
     * @throws CartNotFoundException if the cart identifier is not mapped to an existing cart in the system.
     */
    @POST
    @Path("/checkout/{id}")
    public Response checkoutCart(@PathParam("id") String cartId) {
        Cart cart = cartService.checkoutCart(cartId);
        CartCheckoutResponse response = new CartCheckoutResponse(cart);
        return Response.status(Response.Status.OK).entity(gson.toJson(response)).build();
    }


}
