package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.position.Title;

class JsonAdaptedTitleTest {
    private static final String INVALID_TITLE = "Acc-Manager";


    @Test
    public void toModelType_validTitleDetails_returnsTitle() throws Exception {
        JsonAdaptedTitle title = new JsonAdaptedTitle(ADMIN_ASSISTANT.getTitle());
        assertEquals(ADMIN_ASSISTANT.title, title.toModelType());
    }

    @Test
    public void toModelType_invalidTitleName_throwsIllegalValueException() {
        JsonAdaptedTitle title =
                new JsonAdaptedTitle(INVALID_TITLE);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, title::toModelType);
    }

    @Test
    public void toModelType_nullTitleName_throwsIllegalValueException() {
        JsonAdaptedTitle title =
                new JsonAdaptedTitle("");
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, title::toModelType);
    }

    @Test
    public void toModelType_validTitleDetails_returnsSameTitle() throws Exception {
        JsonAdaptedTitle title = new JsonAdaptedTitle("Administrative Assistant");
        assertEquals(ADMIN_ASSISTANT.getTitle().fullTitle, title.getTitle());
    }
}
