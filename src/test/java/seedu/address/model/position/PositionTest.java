package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_TITLE_BOOKKEEPER;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;
import static seedu.address.testutil.TypicalPositions.BOOKKEEPER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PositionBuilder;

public class PositionTest {

    @Test
    public void isSamePosition() {
        // same object -> returns true
        assertTrue(ADMIN_ASSISTANT.isSamePosition(ADMIN_ASSISTANT));

        // null -> returns false
        assertFalse(ADMIN_ASSISTANT.isSamePosition(null));

        // same name, different candidates -> returns true
        Position editedAdminAssistance = new PositionBuilder(ADMIN_ASSISTANT).build();
        assertTrue(ADMIN_ASSISTANT.isSamePosition(editedAdminAssistance));

        // different name, all other attributes same -> returns false
        editedAdminAssistance = new PositionBuilder(ADMIN_ASSISTANT).withTitle(VALID_TITLE_BOOKKEEPER).build();
        assertFalse(ADMIN_ASSISTANT.isSamePosition(editedAdminAssistance));

        // name differs in case, all other attributes same -> returns false
        Position editedBookkeeper = new PositionBuilder(BOOKKEEPER).withTitle(VALID_TITLE_BOOKKEEPER.toLowerCase())
                .build();
        assertTrue(BOOKKEEPER.isSamePosition(editedBookkeeper));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_TITLE_BOOKKEEPER + " ";
        editedBookkeeper = new PositionBuilder(BOOKKEEPER).withTitle(nameWithTrailingSpaces).build();
        assertFalse(BOOKKEEPER.isSamePosition(editedBookkeeper));

        // Status is closed -> returns true
        editedBookkeeper = new PositionBuilder(BOOKKEEPER).withStatus(Position.PositionStatus.CLOSED).build();
        assertTrue(editedBookkeeper.isClosed());

    }

    @Test
    public void equals() {
        // same values -> returns true
        Position adminAssistantCopy = new PositionBuilder(ADMIN_ASSISTANT).build();
        assertTrue(ADMIN_ASSISTANT.equals(adminAssistantCopy));

        // same object -> returns true
        assertTrue(ADMIN_ASSISTANT.equals(adminAssistantCopy));

        // null -> returns false
        assertFalse(adminAssistantCopy.equals(null));

        // different type -> returns false
        assertFalse(adminAssistantCopy.equals(5));

        // different position -> returns false
        assertFalse(ADMIN_ASSISTANT.equals(BOOKKEEPER));

        // different title -> returns false
        Position editedAdminAssistant = new PositionBuilder(ADMIN_ASSISTANT).withTitle(VALID_TITLE_BOOKKEEPER).build();
        assertFalse(ADMIN_ASSISTANT.equals(editedAdminAssistant));
    }
}
