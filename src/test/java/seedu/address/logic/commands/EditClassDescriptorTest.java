package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CHEM;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PHY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_PHY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_SUN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LIMIT_ONE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditClassDescriptorBuilder;

public class EditClassDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditClassCommand.EditClassDescriptor descriptorWithSameValues = new
                EditClassCommand.EditClassDescriptor(DESC_CHEM);
        assertTrue(DESC_CHEM.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CHEM.equals(DESC_CHEM));

        // null -> returns false
        assertFalse(DESC_CHEM.equals(null));

        // different types -> returns false
        assertFalse(DESC_CHEM.equals(5));

        // different values -> returns false
        assertFalse(DESC_CHEM.equals(DESC_PHY));

        // different name -> returns false
        EditClassCommand.EditClassDescriptor editedChem = new EditClassDescriptorBuilder(DESC_CHEM)
                .withName(VALID_CLASS_PHY).build();
        assertFalse(DESC_CHEM.equals(editedChem));

        // different timeslot -> returns false
        editedChem = new EditClassDescriptorBuilder(DESC_CHEM).withTimeslot(VALID_CLASS_SUN).build();
        assertFalse(DESC_CHEM.equals(editedChem));

        // different limit -> returns false
        editedChem = new EditClassDescriptorBuilder(DESC_CHEM).withLimit(VALID_LIMIT_ONE).build();
        assertFalse(DESC_CHEM.equals(editedChem));
    }
}
