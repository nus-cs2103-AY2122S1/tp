package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH_RATINGS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.vendor.TypicalVendors.DANIEL_VENDOR;
import static seedu.address.testutil.vendor.TypicalVendors.ELLE_VENDOR;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.uniquelist.exceptions.DuplicateItemException;
import seedu.address.model.uniquelist.exceptions.ItemNotFoundException;
import seedu.address.testutil.vendor.VendorBuilder;

public class UniqueVendorListTest {

    private final UniqueVendorList uniqueVendorList = new UniqueVendorList();

    @Test
    public void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueVendorList.contains(null));
    }

    @Test
    public void contains_vendorNotInList_returnsFalse() {
        assertFalse(uniqueVendorList.contains(DANIEL_VENDOR));
    }

    @Test
    public void contains_vendorInList_returnsTrue() {
        uniqueVendorList.add(DANIEL_VENDOR);
        assertTrue(uniqueVendorList.contains(DANIEL_VENDOR));
    }

    @Test
    public void contains_vendorWithSameIdentityFieldsInList_returnsTrue() {
        uniqueVendorList.add(DANIEL_VENDOR);
        Vendor editedDaniel =
                new VendorBuilder(DANIEL_VENDOR).withTags(VALID_TAG_HIGH_RATINGS)
                        .build();
        assertTrue(uniqueVendorList.contains(editedDaniel));
    }

    @Test
    public void add_nullVendor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueVendorList.add(null));
    }

    @Test
    public void add_duplicateVendor_throwsDuplicateVendorException() {
        uniqueVendorList.add(DANIEL_VENDOR);
        assertThrows(DuplicateItemException.class, () -> uniqueVendorList.add(DANIEL_VENDOR));
    }

    @Test
    public void setVendor_nullTargetVendor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueVendorList.setItem(null, DANIEL_VENDOR));
    }

    @Test
    public void setVendor_nullEditedVendor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueVendorList.setItem(DANIEL_VENDOR, null));
    }

    @Test
    public void setVendor_targetVendorNotInList_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueVendorList.setItem(DANIEL_VENDOR, DANIEL_VENDOR));
    }

    @Test
    public void setVendor_editedVendorIsSameVendor_success() {
        uniqueVendorList.add(DANIEL_VENDOR);
        uniqueVendorList.setItem(DANIEL_VENDOR, DANIEL_VENDOR);
        UniqueVendorList expectedUniqueVendorList = new UniqueVendorList();
        expectedUniqueVendorList.add(DANIEL_VENDOR);
        assertEquals(expectedUniqueVendorList, uniqueVendorList);
    }

    @Test
    public void setVendor_editedVendorHasSameIdentity_success() {
        uniqueVendorList.add(DANIEL_VENDOR);
        Vendor editedDaniel =
                new VendorBuilder(DANIEL_VENDOR).withTags(VALID_TAG_HIGH_RATINGS)
                        .build();
        uniqueVendorList.setItem(DANIEL_VENDOR, editedDaniel);
        UniqueVendorList expectedUniqueVendorList = new UniqueVendorList();
        expectedUniqueVendorList.add(editedDaniel);
        assertEquals(expectedUniqueVendorList, uniqueVendorList);
    }

    @Test
    public void setVendor_editedVendorHasDifferentIdentity_success() {
        uniqueVendorList.add(DANIEL_VENDOR);
        uniqueVendorList.setItem(DANIEL_VENDOR, ELLE_VENDOR);
        UniqueVendorList expectedUniqueVendorList = new UniqueVendorList();
        expectedUniqueVendorList.add(ELLE_VENDOR);
        assertEquals(expectedUniqueVendorList, uniqueVendorList);
    }

    @Test
    public void setVendor_editedVendorHasNonUniqueIdentity_throwsDuplicateItemException() {
        uniqueVendorList.add(DANIEL_VENDOR);
        uniqueVendorList.add(ELLE_VENDOR);
        assertThrows(DuplicateItemException.class, () -> uniqueVendorList.setItem(DANIEL_VENDOR, ELLE_VENDOR));
    }

    @Test
    public void remove_nullVendor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueVendorList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueVendorList.remove(DANIEL_VENDOR));
    }

    @Test
    public void remove_existingVendor_removesVendor() {
        uniqueVendorList.add(DANIEL_VENDOR);
        uniqueVendorList.remove(DANIEL_VENDOR);
        UniqueVendorList expectedUniqueVendorList = new UniqueVendorList();
        assertEquals(expectedUniqueVendorList, uniqueVendorList);
    }

    @Test
    public void setVendors_nullUniqueVendorList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueVendorList.setItems((UniqueVendorList) null));
    }

    @Test
    public void setVendors_uniqueVendorList_replacesOwnListWithProvidedUniqueVendorList() {
        uniqueVendorList.add(DANIEL_VENDOR);
        UniqueVendorList expectedUniqueVendorList = new UniqueVendorList();
        expectedUniqueVendorList.add(ELLE_VENDOR);
        uniqueVendorList.setItems(expectedUniqueVendorList);
        assertEquals(expectedUniqueVendorList, uniqueVendorList);
    }

    @Test
    public void setVendors_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueVendorList.setItems((List<Vendor>) null));
    }

    @Test
    public void setVendors_list_replacesOwnListWithProvidedList() {
        uniqueVendorList.add(DANIEL_VENDOR);
        List<Vendor> vendorList = Collections.singletonList(ELLE_VENDOR);
        uniqueVendorList.setItems(vendorList);
        UniqueVendorList expectedUniqueVendorList = new UniqueVendorList();
        expectedUniqueVendorList.add(ELLE_VENDOR);
        assertEquals(expectedUniqueVendorList, uniqueVendorList);
    }

    @Test
    public void setVendors_listWithDuplicateVendors_throwsDuplicateItemException() {
        List<Vendor> listWithDuplicateVendors = Arrays.asList(DANIEL_VENDOR, DANIEL_VENDOR);
        assertThrows(DuplicateItemException.class, () -> uniqueVendorList.setItems(listWithDuplicateVendors));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueVendorList.asUnmodifiableObservableList().remove(0));
    }


}
