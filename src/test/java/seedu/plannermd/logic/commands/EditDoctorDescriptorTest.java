package seedu.plannermd.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_DR_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_DR_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.editcommand.EditDoctorCommand;
import seedu.plannermd.testutil.EditDoctorDescriptorBuilder;

public class EditDoctorDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditDoctorCommand.EditDoctorDescriptor descriptorWithSameValues =
                new EditDoctorCommand.EditDoctorDescriptor(DESC_DR_AMY);
        assertTrue(DESC_DR_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_DR_AMY.equals(DESC_DR_AMY));

        // null -> returns false
        assertFalse(DESC_DR_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_DR_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_DR_AMY.equals(DESC_DR_BOB));

        // different name -> returns false
        EditDoctorCommand.EditDoctorDescriptor editedAmy =
                new EditDoctorDescriptorBuilder(DESC_DR_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_DR_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditDoctorDescriptorBuilder(DESC_DR_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_DR_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditDoctorDescriptorBuilder(DESC_DR_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_DR_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditDoctorDescriptorBuilder(DESC_DR_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_DR_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditDoctorDescriptorBuilder(DESC_DR_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_DR_AMY.equals(editedAmy));

        // different birth date -> returns false
        editedAmy = new EditDoctorDescriptorBuilder(DESC_DR_AMY).withBirthDate(VALID_BIRTH_DATE_BOB).build();
        assertFalse(DESC_DR_AMY.equals(editedAmy));

        //same birth date -> returns true
        editedAmy = new EditDoctorDescriptorBuilder(DESC_DR_AMY).withBirthDate(VALID_BIRTH_DATE_AMY).build();
        assertTrue(DESC_DR_AMY.equals(editedAmy));
    }
}
