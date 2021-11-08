package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_THIRD;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.AVOCADO;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.CHICKEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import javafx.collections.ObservableList;

public class UniqueIngredientListTest {
    private final UniqueIngredientList uniqueIngredientList = new UniqueIngredientList();

    @Test
    public void contains_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.contains(null));
    }

    @Test
    public void contains_ingredientNotInList_returnsFalse() {
        assertFalse(uniqueIngredientList.contains(APPLE));
    }

    @Test
    public void contains_ingredientInList_returnsTrue() {
        uniqueIngredientList.add(APPLE);
        assertTrue(uniqueIngredientList.contains(APPLE));
    }

    @Test
    public void contains_ingredientWithSameIdentityFieldsInList_returnsTrue() {
        uniqueIngredientList.add(APPLE);
        Ingredient editedApple = new IngredientBuilder(APPLE).withUnit("whole").build();
        assertTrue(uniqueIngredientList.contains(editedApple));
    }

    @Test
    public void add_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.add(null));
    }

    @Test
    public void add_validIngredient_listInSortedOrder() {
        uniqueIngredientList.add(BEEF);
        uniqueIngredientList.add(APPLE);
        ObservableList<Ingredient> ingredientList = uniqueIngredientList.asUnmodifiableObservableList();
        assertEquals(APPLE, ingredientList.get(INDEX_FIRST.getZeroBased()));
        assertEquals(BEEF, ingredientList.get(INDEX_SECOND.getZeroBased()));
    }

    @Test
    public void setIngredients_nullUniqueIngredientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList
                .setIngredients((UniqueIngredientList) null));
    }

    @Test
    public void setIngredients_uniqueIngredientList_replacesOwnListWithProvidedUniqueIngredientList() {
        uniqueIngredientList.add(APPLE);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(BEEF);
        uniqueIngredientList.setIngredients(expectedUniqueIngredientList);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.setIngredients((List<Ingredient>) null));
    }

    @Test
    public void setIngredients_list_replacesOwnListWithProvidedList() {
        uniqueIngredientList.add(APPLE);
        List<Ingredient> ingredientList = Collections.singletonList(BEEF);
        uniqueIngredientList.setIngredients(ingredientList);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(BEEF);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredient_editedIngredientHasDifferentName_listInSortedOrder() throws NotFoundException {
        uniqueIngredientList.add(APPLE);
        uniqueIngredientList.add(BEEF);
        uniqueIngredientList.add(CHICKEN);
        uniqueIngredientList.setIngredient(CHICKEN, AVOCADO);
        ObservableList<Ingredient> ingredientList = uniqueIngredientList.asUnmodifiableObservableList();
        assertEquals(APPLE, ingredientList.get(INDEX_FIRST.getZeroBased()));
        assertEquals(AVOCADO, ingredientList.get(INDEX_SECOND.getZeroBased()));
        assertEquals(BEEF, ingredientList.get(INDEX_THIRD.getZeroBased()));
    }

    @Test
    public void setIngredients_uniqueIngredientsList_listInSortedOrder() {
        uniqueIngredientList.add(APPLE);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(CHICKEN);
        expectedUniqueIngredientList.add(BEEF);
        uniqueIngredientList.setIngredients(expectedUniqueIngredientList);
        ObservableList<Ingredient> ingredientObservableList = uniqueIngredientList.asUnmodifiableObservableList();
        assertEquals(BEEF, ingredientObservableList.get(INDEX_FIRST.getZeroBased()));
        assertEquals(CHICKEN, ingredientObservableList.get(INDEX_SECOND.getZeroBased()));
    }

    @Test
    public void setIngredients_list_listInSortedOrder() {
        List<Ingredient> ingredientList = List.of(BEEF, APPLE, CHICKEN);
        uniqueIngredientList.setIngredients(ingredientList);
        ObservableList<Ingredient> ingredientObservableList = uniqueIngredientList.asUnmodifiableObservableList();
        assertEquals(APPLE, ingredientObservableList.get(INDEX_FIRST.getZeroBased()));
        assertEquals(BEEF, ingredientObservableList.get(INDEX_SECOND.getZeroBased()));
        assertEquals(CHICKEN, ingredientObservableList.get(INDEX_THIRD.getZeroBased()));
    }

    @Test
    public void addIngredientQuantity_ingredientInList_addsQuantity() {
        GenericString name = new GenericString("Garlic");
        GenericString unit = new GenericString("whole");
        Ingredient toAdd = new Ingredient(name, new Quantity("100"), unit);
        Ingredient target = new Ingredient(name, new Quantity("10"), unit);
        Ingredient expected = new Ingredient(name, new Quantity("110"), unit);
        Ingredient expected2 = new Ingredient(name, new Quantity("150"), unit);

        UniqueIngredientList uniqueIngredientList = new UniqueIngredientList();
        uniqueIngredientList.add(toAdd);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(expected);
        UniqueIngredientList expectedUniqueIngredientList2 = new UniqueIngredientList();
        expectedUniqueIngredientList2.add(expected2);

        uniqueIngredientList.addIngredientQuantity(target, new Quantity("1"));
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);

        uniqueIngredientList.addIngredientQuantity(target, new Quantity("4"));
        assertEquals(expectedUniqueIngredientList2, uniqueIngredientList);
    }

    @Test
    public void minusIngredientQuantity_ingredientInList_reducesQuantity() {
        GenericString name = new GenericString("Garlic");
        GenericString unit = new GenericString("whole");
        Ingredient toAdd = new Ingredient(name, new Quantity("100"), unit);
        Ingredient target = new Ingredient(name, new Quantity("10"), unit);
        Ingredient expected = new Ingredient(name, new Quantity("90"), unit);
        Ingredient expected2 = new Ingredient(name, new Quantity("50"), unit);

        UniqueIngredientList uniqueIngredientList = new UniqueIngredientList();
        uniqueIngredientList.add(toAdd);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(expected);
        UniqueIngredientList expectedUniqueIngredientList2 = new UniqueIngredientList();
        expectedUniqueIngredientList2.add(expected2);

        uniqueIngredientList.minusIngredientQuantity(target, new Quantity("1"));
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);

        uniqueIngredientList.minusIngredientQuantity(target, new Quantity("4"));
        assertEquals(expectedUniqueIngredientList2, uniqueIngredientList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueIngredientList.asUnmodifiableObservableList().remove(0));
    }
}
