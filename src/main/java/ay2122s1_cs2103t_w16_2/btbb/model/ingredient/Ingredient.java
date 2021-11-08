package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * Represents an Ingredient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Ingredient implements Comparable<Ingredient> {
    private GenericString name;
    private Quantity quantity;
    private GenericString unit;

    /**
     * Constructs a {@code Ingredient}.
     * Every field must be present and not null.
     *
     * @param name of ingredient.
     * @param quantity of ingredient.
     * @param unit that quantity is measured in.
     */
    public Ingredient(GenericString name, Quantity quantity, GenericString unit) {
        requireAllNonNull(name, quantity, unit);
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     * Returns true if the given strings can be used to create a valid ingredients.
     *
     * @param name The name of the ingredient.
     * @param quantity The quantity of the ingredient.
     * @param unit The unit of the ingredient.
     * @return True if it is a valid ingredient. False otherwise.
     */
    public static boolean isValidIngredient(String name, String quantity, String unit) {
        return GenericString.isValidGenericString(name)
                && Quantity.isValidQuantity(quantity)
                && GenericString.isValidGenericString(unit);
    }

    /**
     * Gets name of this ingredient.
     *
     * @return name of this ingredient.
     */
    public GenericString getName() {
        return name;
    }

    /**
     * Gets quantity for ingredient.
     *
     * @return quantity of this ingredient.
     */
    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Gets the unit for ingredient.
     *
     * @return unit of this ingredient.
     */
    public GenericString getUnit() {
        return unit;
    }

    /**
     * Returns true if both ingredients have the same ingredient name and units.
     * This defines a weaker notion of equality between two ingredients.
     *
     * @param otherIngredient to compare this ingredient to.
     * @return boolean of whether the two ingredients match.
     */
    public boolean isSameIngredient(Ingredient otherIngredient) {
        if (otherIngredient == this) {
            return true;
        }

        return otherIngredient != null
                && otherIngredient.getName().isSameGenericString(getName())
                && otherIngredient.getUnit().isSameGenericString(getUnit());
    }

    /**
     * Returns true if both ingredients have the same ingredient name, unit and quantity.
     * This defines a stronger notion of equality between two ingredients.
     *
     * @param other object to compare this ingredient to.
     * @return boolean of whether ingredient and other object match.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Ingredient)) {
            return false;
        }

        Ingredient otherIngredient = (Ingredient) other;
        return otherIngredient.getName().equals(getName())
                && otherIngredient.getQuantity().equals(getQuantity())
                && otherIngredient.getUnit().equals(getUnit());
    }

    /**
     * Implements hashcode for ingredients.
     *
     * @return hashcode of ingredient.
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, quantity, unit);
    }

    /**
     * Converts ingredient object into its String representation.
     *
     * @return String representation of ingredient.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" x ")
                .append(getQuantity())
                .append(" ")
                .append(getUnit());

        return builder.toString();
    }

    @Override
    public int compareTo(Ingredient other) {
        return name.compareTo(other.getName());
    }
}
