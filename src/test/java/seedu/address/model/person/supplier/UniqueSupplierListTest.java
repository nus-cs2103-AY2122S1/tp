package seedu.address.model.person.supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSuppliers.AMY;
import static seedu.address.testutil.TypicalSuppliers.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateSupplierException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.SupplierBuilder;

public class UniqueSupplierListTest {

    private final UniqueSupplierList uniqueSupplierList = new UniqueSupplierList();

    @Test
    public void contains_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.contains(null));
    }

    @Test
    public void contains_supplierNotInList_returnsFalse() {
        assertFalse(uniqueSupplierList.contains(AMY));
    }

    @Test
    public void contains_supplierInList_returnsTrue() {
        uniqueSupplierList.add(AMY);
        assertTrue(uniqueSupplierList.contains(AMY));
    }

    @Test
    public void contains_supplierWithSameIdentityFieldsInList_returnsTrue() {
        uniqueSupplierList.add(AMY);
        Supplier editedAmy = new SupplierBuilder(AMY).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueSupplierList.contains(editedAmy));
    }

    @Test
    public void add_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.add(null));
    }

    @Test
    public void add_duplicateSupplier_throwsDuplicatePersonException() {
        uniqueSupplierList.add(AMY);
        assertThrows(DuplicateSupplierException.class, () -> uniqueSupplierList.add(AMY));
    }

    @Test
    public void setSupplier_nullTargetSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setSupplier(null, AMY));
    }

    @Test
    public void setSupplier_nullEditedSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setSupplier(AMY, null));
    }

    @Test
    public void setSupplier_targetSupplierNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueSupplierList.setSupplier(AMY, AMY));
    }

    @Test
    public void setSupplier_editedSupplierIsSameSupplier_success() {
        uniqueSupplierList.add(AMY);
        uniqueSupplierList.setSupplier(AMY, AMY);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(AMY);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_editedSupplierHasSameIdentity_success() {
        uniqueSupplierList.add(AMY);
        Supplier editedAmy = new SupplierBuilder(AMY).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueSupplierList.setSupplier(AMY, editedAmy);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(editedAmy);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_editedSupplierHasDifferentIdentity_success() {
        uniqueSupplierList.add(AMY);
        uniqueSupplierList.setSupplier(AMY, BOB);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(BOB);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_editedSupplierHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueSupplierList.add(AMY);
        uniqueSupplierList.add(BOB);
        assertThrows(DuplicateSupplierException.class, () -> uniqueSupplierList.setSupplier(AMY, BOB));
    }

    @Test
    public void remove_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.remove(null));
    }

    @Test
    public void remove_supplierDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueSupplierList.remove(AMY));
    }

    @Test
    public void remove_existingSupplier_removesSupplier() {
        uniqueSupplierList.add(AMY);
        uniqueSupplierList.remove(AMY);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSuppliers_nullUniqueSupplierList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setSuppliers((UniqueSupplierList) null));
    }

    @Test
    public void setSuppliers_uniqueSupplierList_replacesOwnListWithProvidedUniqueSupplierList() {
        uniqueSupplierList.add(AMY);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(BOB);
        uniqueSupplierList.setSuppliers(expectedUniqueSupplierList);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSuppliers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setSuppliers((List<Supplier>) null));
    }

    @Test
    public void setSuppliers_list_replacesOwnListWithProvidedList() {
        uniqueSupplierList.add(AMY);
        List<Supplier> supplierList = Collections.singletonList(BOB);
        uniqueSupplierList.setSuppliers(supplierList);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(BOB);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSuppliers_listWithDuplicateSuppliers_throwsDuplicateSupplierException() {
        List<Supplier> listWithDuplicateSuppliers = Arrays.asList(AMY, AMY);
        assertThrows(DuplicateSupplierException.class, () ->
                uniqueSupplierList.setSuppliers(listWithDuplicateSuppliers));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueSupplierList.asUnmodifiableObservableList().remove(0));
    }
}

