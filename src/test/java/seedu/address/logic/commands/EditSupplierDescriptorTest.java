package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.SupplierCommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.SupplierCommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_DELIVERY_DETAIL_BOB;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_SUPPLY_TYPE_BEEF;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import seedu.address.testutil.EditSupplierDescriptorBuilder;

public class EditSupplierDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditSupplierDescriptor descriptorWithSameValues = new EditSupplierDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditSupplierDescriptor editedAmy = new EditSupplierDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditSupplierDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditSupplierDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditSupplierDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditSupplierDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different supply type -> returns false
        editedAmy = new EditSupplierDescriptorBuilder(DESC_AMY).withSupplyType(VALID_SUPPLY_TYPE_BEEF).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different delivery details -> returns false
        editedAmy = new EditSupplierDescriptorBuilder(DESC_AMY)
                .withDeliveryDetails(VALID_DELIVERY_DETAIL_BOB)
                .build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
