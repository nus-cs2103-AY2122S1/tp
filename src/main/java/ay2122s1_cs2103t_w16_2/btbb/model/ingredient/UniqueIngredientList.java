package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of ingrdients that enforces uniqueness between its elements and does not allow nulls.
 * An ingredient is considered unique by comparing using {@code Ingredient#isSameIngredient(Ingredient)}.
 * As such, adding and updating of ingredients uses Ingredient#isSameIngredient(Ingredient) for equality so as to
 * ensure that the ingredient being added or updated is unique in terms of identity in the UniqueIngredientList.
 * However, the removal of an ingredent uses Ingredient#equals(Object) so as to ensure that the ingredient with exactly
 * the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Ingredient#isSameIngredient(Ingredient)
 */
public class UniqueIngredientList implements Iterable<Ingredient> {
    private final ObservableList<Ingredient> internalList = FXCollections.observableArrayList();
    private final ObservableList<Ingredient> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent ingredient as the given argument.
     *
     * @param toCheck Ingredient.
     * @return true if ingredient is already in the internalList, false otherwise.
     */
    public boolean contains(Ingredient toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameIngredient);
    }

    /**
     * Adds an ingredient to the list.
     * The ingredient must not already exist in the list.
     *
     * @param toAdd Ingredient to add to internalList.
     */
    public void add(Ingredient toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
        sortIngredients();
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     * {@code replacement} must not contain duplicate ingredients.
     *
     * @param replacement another ingredient list to copy from.
     */
    public void setIngredients(UniqueIngredientList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        sortIngredients();
    }

    /**
     * Replaces the contents of this list with {@code ingredients}.
     * {@code ingredients} must not contain duplicate ingredients.
     *
     * @param ingredients another ingredient list to copy from.
     */
    public void setIngredients(List<Ingredient> ingredients) {
        requireAllNonNull(ingredients);
        internalList.setAll(ingredients);
        sortIngredients();
    }

    /**
     * Replaces the similar ingredient that is in the address book with a new ingredient whose quantity is increased
     * by the quantity in {@code target} times the multiplier if it exists.
     *
     * @param target The target ingredient.
     * @param multiplier The multiplier.
     */
    public void addIngredientQuantity(Ingredient target, Quantity multiplier) {
        requireAllNonNull(target, multiplier);

        Ingredient currentIngredient = internalList.stream()
                .filter(target::isSameIngredient).findFirst().orElse(null);

        if (currentIngredient != null) {
            Ingredient ingredientWithNewQuantity = new Ingredient(
                    currentIngredient.getName(),
                    currentIngredient.getQuantity().addQuantityBy(target.getQuantity(), multiplier),
                    currentIngredient.getUnit());

            int index = internalList.indexOf(currentIngredient);
            internalList.set(index, ingredientWithNewQuantity);
        }
    }

    /**
     * Replaces the similar ingredient that is in the address book with a new ingredient whose quantity is reduced
     * by the quantity in {@code target} times the multiplier if it exists.
     *
     * @param target The target ingredient.
     * @param multiplier The multiplier.
     */
    public void minusIngredientQuantity(Ingredient target, Quantity multiplier) {
        requireAllNonNull(target, multiplier);

        Ingredient currentIngredient = internalList.stream()
                .filter(target::isSameIngredient).findFirst().orElse(null);

        if (currentIngredient != null) {
            Ingredient ingredientWithNewQuantity = new Ingredient(
                    currentIngredient.getName(),
                    currentIngredient.getQuantity().minusQuantityBy(target.getQuantity(), multiplier),
                    currentIngredient.getUnit());

            int index = internalList.indexOf(currentIngredient);
            internalList.set(index, ingredientWithNewQuantity);
        }
    }

    /**
     * Removes the equivalent ingredient from the list of ingredients.
     * The ingredient must exist in the list.
     *
     * @param toRemove The ingredient to remove from the list.
     * @throws NotFoundException when there is no equivalent ingredient found in the list.
     */
    public void remove(Ingredient toRemove) throws NotFoundException {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new NotFoundException(Ingredient.class.getName());
        }
        sortIngredients();
    }

    /**
     * Replaces the existing target Ingredient in the list with an edited Ingredient.
     *
     * @param target The target ingredient to replace.
     * @param editedIngredient The edited ingredient to replace with.
     * @throws NotFoundException if the target Ingredient does not exist in the list.
     */
    public void setIngredient(Ingredient target, Ingredient editedIngredient) throws NotFoundException {
        requireAllNonNull(target, editedIngredient);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new NotFoundException(Ingredient.class.getName());
        }
        internalList.set(index, editedIngredient);
        sortIngredients();
    }

    private void sortIngredients() {
        internalList.sort(Ingredient::compareTo);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return unmodifiable ingredient list.
     */
    public ObservableList<Ingredient> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Ingredient> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueIngredientList // instanceof handles nulls
                && internalList.equals(((UniqueIngredientList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
