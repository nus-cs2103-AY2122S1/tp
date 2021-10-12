package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACILITY_NAME_COURT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACILITY_NAME_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_COURT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_FIELD;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditFacilityDescriptorBuilder;

public class EditFacilityDescriptorTest {
    private EditFacilityCommand.EditFacilityDescriptor desc1 =
            new EditFacilityDescriptorBuilder()
                    .withFacilityName(VALID_FACILITY_NAME_COURT)
                    .withLocation(VALID_LOCATION_COURT).build();
    private EditFacilityCommand.EditFacilityDescriptor desc2 =
            new EditFacilityDescriptorBuilder()
                    .withFacilityName(VALID_FACILITY_NAME_FIELD)
                    .withLocation(VALID_LOCATION_FIELD).build();

    @Test
    public void equals() {
        // same values -> returns true
        EditFacilityCommand.EditFacilityDescriptor descriptorWithSameValues =
                new EditFacilityCommand.EditFacilityDescriptor(desc1);
        assertTrue(desc1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(desc1.equals(desc1));

        // null -> returns false
        assertFalse(desc1.equals(null));

        // different types -> returns false
        assertFalse(desc1.equals(5));

        // different values -> returns false
        assertFalse(desc1.equals(desc2));

        // different name -> returns false
        EditFacilityCommand.EditFacilityDescriptor editedDesc1 =
                new EditFacilityDescriptorBuilder(desc1)
                        .withFacilityName(VALID_FACILITY_NAME_FIELD).build();
        assertFalse(desc1.equals(desc2));

        // different location -> returns false
        editedDesc1 = new EditFacilityDescriptorBuilder(desc1)
                .withLocation(VALID_LOCATION_FIELD).build();
        assertFalse(desc1.equals(editedDesc1));
    }
}
