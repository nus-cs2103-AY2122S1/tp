package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CANNON;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_DAISY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_DAISY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditProductCommand.EditProductDescriptor;
import seedu.address.testutil.EditProductDescriptorBuilder;

public class EditProductDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditProductDescriptor descriptorWithSameValues = new EditProductDescriptor(DESC_CANNON);
        assertTrue(DESC_CANNON.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CANNON.equals(DESC_CANNON));

        // null -> returns false
        assertFalse(DESC_CANNON.equals(null));

        // different types -> returns false
        assertFalse(DESC_CANNON.equals(5));

        // different values -> returns false
        assertFalse(DESC_CANNON.equals(DESC_DAISY));

        // different name -> returns false
        EditProductDescriptor editedCannon = new EditProductDescriptorBuilder(DESC_CANNON).withName(VALID_NAME_DAISY)
                .build();
        assertFalse(DESC_CANNON.equals(editedCannon));

        // different unit price -> returns false
        editedCannon = new EditProductDescriptorBuilder(DESC_CANNON).withUnitPrice(VALID_UNIT_PRICE_DAISY).build();
        assertFalse(DESC_CANNON.equals(editedCannon));

        // different quantity -> returns false
        editedCannon = new EditProductDescriptorBuilder(DESC_CANNON).withQuantity(VALID_QUANTITY_DAISY).build();
        assertFalse(DESC_CANNON.equals(editedCannon));
    }
}
