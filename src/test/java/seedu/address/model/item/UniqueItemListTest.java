package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POPULAR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE_PIE;
import static seedu.address.testutil.TypicalItems.BAGEL;
import static seedu.address.testutil.TypicalItems.BANANA_MUFFIN;
import static seedu.address.testutil.TypicalItems.CHOCOCHIP;
import static seedu.address.testutil.TypicalItems.DONUT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.model.item.exceptions.ItemNotFoundException;
import seedu.address.testutil.ItemBuilder;
import seedu.address.testutil.ItemDescriptorBuilder;

public class UniqueItemListTest {

    private final UniqueItemList uniqueItemList = new UniqueItemList();

    @Test
    public void contains_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.contains((Item) null));
    }

    @Test
    public void contains_itemNotInList_returnsFalse() {
        assertFalse(uniqueItemList.contains(APPLE_PIE));
    }

    @Test
    public void contains_itemCountZero_returnsFalse() {
        uniqueItemList.add(APPLE_PIE.updateCount(0));

        assertFalse(uniqueItemList.contains(APPLE_PIE));
    }

    @Test
    public void contains_itemInList_returnsTrue() {
        uniqueItemList.add(APPLE_PIE);

        assertTrue(uniqueItemList.contains(APPLE_PIE));
    }

    @Test
    public void contains_itemWithSameIdentityFieldsInList_returnsTrue() {
        uniqueItemList.add(APPLE_PIE);
        Item editedPie = new ItemBuilder(APPLE_PIE).withTags(VALID_TAG_POPULAR)
                .build();
        assertTrue(uniqueItemList.contains(editedPie));
    }

    @Test
    public void add_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.add(null));
    }

    @Test
    public void add_duplicateItem_throwsDuplicateItemException() {
        uniqueItemList.add(APPLE_PIE);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.add(APPLE_PIE));
    }

    @Test
    public void setItem_nullTargetItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItem(null, APPLE_PIE));
    }

    @Test
    public void setItem_nullEditedItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItem(APPLE_PIE, null));
    }

    @Test
    public void setItem_targetItemNotInList_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueItemList.setItem(APPLE_PIE, APPLE_PIE));
    }

    @Test
    public void setItem_editedItemIsSameItem_success() {
        uniqueItemList.add(APPLE_PIE);
        uniqueItemList.setItem(APPLE_PIE, APPLE_PIE);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(APPLE_PIE);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasSameIdentity_success() {
        uniqueItemList.add(APPLE_PIE);
        Item editedPie = new ItemBuilder(APPLE_PIE).withTags(VALID_TAG_POPULAR).build();
        uniqueItemList.setItem(APPLE_PIE, editedPie);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(editedPie);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasDifferentIdentity_success() {
        uniqueItemList.add(APPLE_PIE);
        uniqueItemList.setItem(APPLE_PIE, BANANA_MUFFIN);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(BANANA_MUFFIN);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasNonUniqueIdentity_throwsDuplicateItemException() {
        uniqueItemList.add(APPLE_PIE);
        uniqueItemList.add(BANANA_MUFFIN);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.setItem(APPLE_PIE, BANANA_MUFFIN));
    }

    @Test
    public void getItem_itemInInventory_returnsItem() {
        uniqueItemList.add(BAGEL);

        // Search by name
        ItemDescriptor descriptor = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL).build();
        assertEquals(uniqueItemList.get(descriptor), List.of(BAGEL));

        // Search by id
        descriptor = new ItemDescriptorBuilder().withId(VALID_ID_BAGEL).build();
        assertEquals(uniqueItemList.get(descriptor), List.of(BAGEL));

        // Search by name and id
        descriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).build();
        assertEquals(uniqueItemList.get(descriptor), List.of(BAGEL));
    }

    @Test
    public void getItem_itemNotInInventory_returnEmptyList() {
        uniqueItemList.add(DONUT);

        // Search by name
        ItemDescriptor descriptor = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL).build();
        assertEquals(uniqueItemList.get(descriptor), List.of());

        // Search by id
        descriptor = new ItemDescriptorBuilder().withId(VALID_ID_BAGEL).build();
        assertEquals(uniqueItemList.get(descriptor), List.of());

        // Search by name and id
        descriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).build();
        assertEquals(uniqueItemList.get(descriptor), List.of());
    }

    @Test
    public void getItem_multipleMatches_returnMultiple() {
        uniqueItemList.add(DONUT);
        uniqueItemList.add(BAGEL);

        ItemDescriptor descriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_DONUT).build();
        assertEquals(uniqueItemList.get(descriptor), List.of(DONUT, BAGEL));
    }


    @Test
    public void remove_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.remove(null));
    }

    @Test
    public void remove_itemDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueItemList.remove(APPLE_PIE));
    }

    @Test
    public void remove_existingItem_removesItem() {
        uniqueItemList.add(APPLE_PIE);
        uniqueItemList.remove(APPLE_PIE);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItems_nullUniqueItemList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItems((UniqueItemList) null));
    }

    @Test
    public void setItems_uniqueItemList_replacesOwnListWithProvidedUniqueItemList() {
        uniqueItemList.add(APPLE_PIE);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        uniqueItemList.add(BANANA_MUFFIN);
        this.uniqueItemList.setItems(expectedUniqueItemList);
        assertEquals(expectedUniqueItemList, this.uniqueItemList);
    }

    @Test
    public void setItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItems((List<Item>) null));
    }

    @Test
    public void setItems_list_replacesOwnListWithProvidedList() {
        uniqueItemList.add(APPLE_PIE);
        List<Item> itemList = Collections.singletonList(BANANA_MUFFIN);
        uniqueItemList.setItems(itemList);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(BANANA_MUFFIN);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItems_listWithDuplicateItems_throwsDuplicateItemException() {
        List<Item> listWithDuplicateItems = Arrays.asList(APPLE_PIE, APPLE_PIE);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.setItems(listWithDuplicateItems));
    }

    @Test
    public void setItems_sortItems_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.sortItems(null));
    }
    @Test
    public void setItems_sortItems_successful() {
        uniqueItemList.add(BANANA_MUFFIN);
        uniqueItemList.add(APPLE_PIE);
        uniqueItemList.add(CHOCOCHIP);
        uniqueItemList.sortItems(new ItemNameComparator());

        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(APPLE_PIE);
        expectedUniqueItemList.add(BANANA_MUFFIN);
        expectedUniqueItemList.add(CHOCOCHIP);

        assertEquals(expectedUniqueItemList, this.uniqueItemList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueItemList.asUnmodifiableObservableList().remove(0));
    }
}
