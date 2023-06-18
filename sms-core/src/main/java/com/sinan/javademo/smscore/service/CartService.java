package com.sinan.javademo.smscore.service;

import com.sinan.javademo.smscore.exception.CartItemNotFoundException;
import com.sinan.javademo.smscore.exception.CartNotFoundException;
import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.cart.Cart;
import com.sinan.javademo.smscore.model.cart.CartBuilder;
import com.sinan.javademo.smscore.model.cart.CartDirector;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.repository.carts.ICartsRepository;
import com.sinan.javademo.smscore.repository.carts.CartsRepositoryFactory;
import com.sinan.javademo.smscore.repository.items.IItemsRepository;
import com.sinan.javademo.smscore.repository.items.ItemsRepositoryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

/**
 * A backend service to provide cart related functionalities.
 *
 * @author Sinan Wannous
 * @since 1.0
 */
@ApplicationScoped
public class CartService {

    /**
     * The backend service for offer functionalities.
     */
    @Inject
    private OfferService offerService;

    /**
     * The cart builder to create carts.
     */
    @Inject
    private CartBuilder cartBuilder;

    /**
     * The factory of carts repository instances.
     */
    @Inject
    private CartsRepositoryFactory cartsRepositoryFactory;

    /**
     * The factory of items repository instances.
     */
    @Inject
    private ItemsRepositoryFactory itemsRepositoryFactory;

    private ICartsRepository cartsRepository;
    private IItemsRepository itemsRepository;


    @PostConstruct
    private void init() {
        cartsRepository = cartsRepositoryFactory.createInstance();
        itemsRepository = itemsRepositoryFactory.createInstance();
    }

    /**
     * Returns the details of a specific cart from repository.
     *
     * @param cartId the cart identifier.
     * @return the {@link Cart} object with its details.
     * @throws CartNotFoundException if cart identifier is not mapped to an existing cart in the repository.
     */
    public Cart getCartInfo(String cartId) {
        return cartsRepository.getCart(cartId);
    }

    /**
     * Creates a new cart out of a list of items and adds it to repository.
     *
     * @param itemsIdentifiers a list of items identifiers to be added to the cart (<em>it may be empty</em>).
     * @return the created {@link Cart} object with its details.
     * @throws ItemNotFoundException if one of the item identifiers is not mapped to an existing item in the repository.
     */
    public Cart createCart(List<String> itemsIdentifiers) {

        //Creating cart object using cart director
        CartDirector cartDirector = new CartDirector(cartBuilder);
        if (itemsIdentifiers != null && itemsIdentifiers.size() > 0) {
            cartDirector.createCartWithItems(itemsIdentifiers);
        } else {
            cartDirector.createEmptyCart();
        }
        Cart cart = cartBuilder.build();

        //Saving cart to repository
        cartsRepository.saveCart(cart);

        return cart;
    }

    /**
     * Adds one unit of an item to the cart and saves it to repository.
     *
     * @param cartId         the cart identifier.
     * @param itemIdentifier the item identifier.
     * @return the updated {@link Cart} object with its details.
     * @throws CartNotFoundException if cart identifier is not mapped to an existing cart in the repository.
     * @throws ItemNotFoundException if item identifier is not mapped to an existing item in the repository.
     */
    public Cart addItem(String cartId, String itemIdentifier) {

        //Retrieving cart and item information
        Cart cart = cartsRepository.getCart(cartId);
        Item item = itemsRepository.getItem(itemIdentifier);

        //Adding item to cart
        cart.addItem(item);

        //Saving cart to repository
        cartsRepository.saveCart(cart);

        return cart;
    }

    /**
     * Removes one unit of an item from the cart and saves it to repository.
     *
     * @param cartId         the cart identifier.
     * @param itemIdentifier the item identifier.
     * @return the updated {@link Cart} object with its details.
     * @throws CartNotFoundException     if cart identifier is not mapped to an existing cart in the repository.
     * @throws ItemNotFoundException     if item identifier is not mapped to an existing item in the repository.
     * @throws CartItemNotFoundException if item identifier is not mapped to an item in the cart.
     */
    public Cart removeItem(String cartId, String itemIdentifier) {

        //Retrieving cart and item information
        Cart cart = cartsRepository.getCart(cartId);
        Item item = itemsRepository.getItem(itemIdentifier);

        //Removing item from cart
        cart.removeItem(item);

        //Saving cart to repository
        cartsRepository.saveCart(cart);
        return cart;
    }

    /**
     * Checks out the cart, apply discounts, and retrieve its final price and discount information.
     *
     * @param cartId the cart identifier.
     * @return the checked out {@link Cart} object with its details.
     * @throws CartNotFoundException if cart identifier is not mapped to an existing cart in the repository.
     */
    public Cart checkoutCart(String cartId) {

        //Retrieving cart information
        Cart cart = cartsRepository.getCart(cartId);

        //Retrieving currently active offers and iterating through them
        var activeOffers = offerService.getActiveOffers();
        for (var offer : activeOffers) {
            if (offer.getConditionStrategy().isApplicable(cart)) {
                //Offer is applicable to the cart, apply it
                double discountValue = offer.getExecutionStrategy().apply(cart);
                if (discountValue > 0) {
                    //The offer has led to discount, add offer to cart
                    cart.addOffer(offer, discountValue);
                }
            } else {
                //Offer is no longer applicable, remove it if existed
                cart.removeOfferIfExist(offer);
            }
        }

        //Saving cart to repository
        cartsRepository.saveCart(cart);

        return cart;
    }

}
