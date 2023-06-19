package com.sinan.javademo.smscore.model.cart;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * A director to create predefined types of carts in conjunction with {@link CartBuilder}.
 * The director is not used in this demo but implemented for further code extensions and for showcase purposes.
 *
 * @author Sinan Wannous
 * @see <a href="https://refactoring.guru/design-patterns/builder">Builder Design Pattern</a>
 * @since 1.0
 */
@ApplicationScoped
public class CartDirector {

    @Inject
    private ICartBuilder cartBuilder;

    public void constructEmptyCart() {
        //Nothing to do
    }

    public void constructVeganCart() {
        List<String> veganItems = new ArrayList<>(List.of("Bread", "Beans", "Tofu", "Nuts", "Salad", "Tahini"));
        cartBuilder.setItems(veganItems);
    }

    public void constructBirthdayCart() {
        List<String> birthdayItems = new ArrayList<>(List.of("Cake", "Champagne"));
        cartBuilder.setItems(birthdayItems);
    }
}
