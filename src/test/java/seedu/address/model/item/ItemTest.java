package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALESPRICE_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POPULAR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE_PIE;
import static seedu.address.testutil.TypicalItems.BAGEL;
import static seedu.address.testutil.TypicalItems.BANANA_MUFFIN;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemBuilder;

public class ItemTest {

    @Test
    public void altConstructor() {
        Item item = new Item(APPLE_PIE, APPLE_PIE.getCount());

        assertTrue(item.equals(APPLE_PIE));
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Item item = new ItemBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> item.getTags().remove(0));
    }

    @Test
    public void isSameItem() {
        // same object -> returns true
        assertTrue(APPLE_PIE.isSameItem(APPLE_PIE));

        // null -> returns false
        assertFalse(APPLE_PIE.isSameItem(null));

        // same name, different id, all other attributes different -> returns true
        Item editedPie = new ItemBuilder(APPLE_PIE).withId("123456").build();
        assertTrue(APPLE_PIE.isSameItem(editedPie));

        // different name, same id, all other attributes different -> returns true
        editedPie = new ItemBuilder(APPLE_PIE).withName("Cherry Pie").build();
        assertTrue(APPLE_PIE.isSameItem(editedPie));

        // different name, different id, all other attributes same -> returns false
        editedPie = new ItemBuilder(APPLE_PIE)
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).build();
        assertFalse(APPLE_PIE.isSameItem(editedPie));

        // name differs in case, all other attributes same -> returns false
        Item editedBagel = new ItemBuilder(BAGEL)
                .withName(VALID_NAME_BAGEL.toUpperCase()).build();
        assertFalse(BANANA_MUFFIN.isSameItem(editedBagel));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Item pieCopy = new ItemBuilder(APPLE_PIE).build();
        assertTrue(APPLE_PIE.equals(pieCopy));

        // same object -> returns true
        assertTrue(APPLE_PIE.equals(APPLE_PIE));

        // null -> returns false
        assertFalse(APPLE_PIE.equals(null));

        // different type -> returns false
        assertFalse(APPLE_PIE.equals(5));

        // different item -> returns false
        assertFalse(APPLE_PIE.equals(BANANA_MUFFIN));

        // different name -> returns false
        Item editedPie = new ItemBuilder(APPLE_PIE).withName(VALID_NAME_BAGEL).build();
        assertFalse(APPLE_PIE.equals(editedPie));

        // different id -> returns false
        editedPie = new ItemBuilder(APPLE_PIE).withId(VALID_ID_BAGEL).build();
        assertFalse(APPLE_PIE.equals(editedPie));

        // different count -> returns false
        String diffCount = String.valueOf(APPLE_PIE.getCount() + 1);
        editedPie = new ItemBuilder(APPLE_PIE).withCount(diffCount).build();
        assertFalse(APPLE_PIE.equals(editedPie));

        // different tags -> returns false
        editedPie = new ItemBuilder(APPLE_PIE).withTags(VALID_TAG_POPULAR).build();
        assertFalse(APPLE_PIE.equals(editedPie));

        // different tags -> returns false
        editedPie = new ItemBuilder(APPLE_PIE).withSalesPrice(VALID_SALESPRICE_BAGEL).build();
        assertFalse(APPLE_PIE.equals(editedPie));
    }

    @Test
    public void variousEqualityFunction() {
        // same values -> returns true
        Item pieCopy = new ItemBuilder(APPLE_PIE).build();
        assertTrue(APPLE_PIE.isSameCount(pieCopy));
        assertTrue(APPLE_PIE.isSameName(pieCopy));
        assertTrue(APPLE_PIE.isSameId(pieCopy));
        assertTrue(APPLE_PIE.isSameItem(pieCopy));

        // same item
        assertTrue(APPLE_PIE.isSameCount(APPLE_PIE));
        assertTrue(APPLE_PIE.isSameName(APPLE_PIE));
        assertTrue(APPLE_PIE.isSameId(APPLE_PIE));
        assertTrue(APPLE_PIE.isSameItem(APPLE_PIE));

        // different items with different values
        assertFalse(APPLE_PIE.isSameCount(BAGEL.updateCount(BAGEL.getCount() + 1)));
        assertFalse(APPLE_PIE.isSameName(BAGEL));
        assertFalse(APPLE_PIE.isSameId(BAGEL));
        assertFalse(APPLE_PIE.isSameItem(BAGEL));
    }
}
