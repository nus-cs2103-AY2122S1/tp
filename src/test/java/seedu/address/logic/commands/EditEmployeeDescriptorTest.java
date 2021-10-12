package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EMPLOYEE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EMPLOYEE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditEmployeeCommand.EditEmployeeDescriptor;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;

public class EditEmployeeDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEmployeeDescriptor descriptorWithSameValues = new EditEmployeeDescriptor(DESC_EMPLOYEE_AMY);
        assertTrue(DESC_EMPLOYEE_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_EMPLOYEE_AMY.equals(DESC_EMPLOYEE_AMY));

        // null -> returns false
        assertFalse(DESC_EMPLOYEE_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_EMPLOYEE_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_EMPLOYEE_AMY.equals(DESC_EMPLOYEE_BOB));

        // different name -> returns false
        EditEmployeeDescriptor editedAmy =
                new EditEmployeeDescriptorBuilder(DESC_EMPLOYEE_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_EMPLOYEE_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_EMPLOYEE_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_EMPLOYEE_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_EMPLOYEE_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_EMPLOYEE_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_EMPLOYEE_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_EMPLOYEE_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_EMPLOYEE_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_EMPLOYEE_AMY.equals(editedAmy));
    }
}
