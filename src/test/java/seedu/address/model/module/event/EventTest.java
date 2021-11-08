package seedu.address.model.module.event;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE_CHESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_CHESS;
import static seedu.address.testutil.TypicalEvents.BADMINTON;
import static seedu.address.testutil.TypicalEvents.CHESS;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {

    @Test
    public void isSameType() {
        // same object -> returns true
        Assertions.assertTrue(BADMINTON.isSameType(BADMINTON));

        // null -> returns false
        Assertions.assertFalse(BADMINTON.isSameType(null));

        // same name, all other attributes different -> returns false
        Event editedBadminton = new EventBuilder(BADMINTON).withDate(VALID_EVENT_DATE_CHESS).withParticipants().build();
        Assertions.assertFalse(BADMINTON.isSameType(editedBadminton));

        // same name, same date, all other attributes different -> returns true
        editedBadminton = new EventBuilder(BADMINTON).withParticipants().build();
        Assertions.assertTrue(BADMINTON.isSameType(editedBadminton));

        // different name, all other attributes same -> returns false
        editedBadminton = new EventBuilder(BADMINTON).withName(VALID_EVENT_NAME_CHESS).build();
        Assertions.assertFalse(BADMINTON.isSameType(editedBadminton));

        // name differs in case, all other attributes same -> returns true
        Event editedChess = new EventBuilder(CHESS).withName(VALID_EVENT_NAME_CHESS.toLowerCase()).build();
        Assertions.assertTrue(CHESS.isSameType(editedChess));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_EVENT_NAME_CHESS + " ";
        editedChess = new EventBuilder(CHESS).withName(nameWithTrailingSpaces).build();
        Assertions.assertFalse(CHESS.isSameType(editedChess));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event aliceCopy = new EventBuilder(BADMINTON).build();
        Assertions.assertTrue(BADMINTON.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(BADMINTON.equals(BADMINTON));

        // null -> returns false
        Assertions.assertFalse(BADMINTON.equals(null));

        // different type -> returns false
        Assertions.assertFalse(BADMINTON.equals(5));

        // different event -> returns false
        Assertions.assertFalse(BADMINTON.equals(CHESS));

        // different name -> returns false
        Event editedBadminton = new EventBuilder(BADMINTON).withName(VALID_EVENT_NAME_CHESS).build();
        Assertions.assertFalse(BADMINTON.equals(editedBadminton));

        // different date -> returns false
        editedBadminton = new EventBuilder(BADMINTON).withDate(VALID_EVENT_DATE_CHESS).build();
        Assertions.assertFalse(BADMINTON.equals(editedBadminton));

        // different participants -> returns false
        editedBadminton = new EventBuilder(BADMINTON).withParticipants().build();
        Assertions.assertFalse(BADMINTON.equals(editedBadminton));
    }
}
