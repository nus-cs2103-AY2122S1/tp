package safeforhall.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.logic.commands.CommandTestUtil.DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.DESC_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_DATE_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_VENUE_SWIM_TRAINING;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.edit.EditEventCommand.EditEventDescriptor;
import safeforhall.testutil.EditEventDescriptorBuilder;

public class EditEventDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEventCommand.EditEventDescriptor descriptorWithSameValues =
                new EditEventCommand.EditEventDescriptor(DESC_FOOTBALL_TRAINING);
        assertTrue(DESC_FOOTBALL_TRAINING.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_FOOTBALL_TRAINING.equals(DESC_FOOTBALL_TRAINING));

        // null -> returns false
        assertFalse(DESC_FOOTBALL_TRAINING.equals(null));

        // different types -> returns false
        assertFalse(DESC_FOOTBALL_TRAINING.equals(5));

        // different values -> returns false
        assertFalse(DESC_FOOTBALL_TRAINING.equals(DESC_SWIM_TRAINING));

        // different name -> returns false
        EditEventDescriptor editedFootball = new EditEventDescriptorBuilder(DESC_FOOTBALL_TRAINING)
                .withName(VALID_NAME_SWIM_TRAINING).build();
        assertFalse(DESC_FOOTBALL_TRAINING.equals(editedFootball));

        // different date -> returns false
        editedFootball = new EditEventDescriptorBuilder(DESC_FOOTBALL_TRAINING)
                .withDate(VALID_DATE_SWIM_TRAINING).build();
        assertFalse(DESC_FOOTBALL_TRAINING.equals(editedFootball));

        // different venue -> returns false
        editedFootball = new EditEventDescriptorBuilder(DESC_FOOTBALL_TRAINING)
                .withVenue(VALID_VENUE_SWIM_TRAINING).build();
        assertFalse(DESC_FOOTBALL_TRAINING.equals(editedFootball));

    }
}
