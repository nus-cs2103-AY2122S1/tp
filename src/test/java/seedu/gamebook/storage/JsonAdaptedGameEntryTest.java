package seedu.gamebook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.gamebook.storage.JsonAdaptedGameEntry.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.gamebook.testutil.Assert.assertThrows;
import static seedu.gamebook.testutil.TypicalGameEntries.POKER1_WITHOUT_TIME;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.gamebook.commons.exceptions.IllegalValueException;
import seedu.gamebook.model.gameentry.GameType;


public class JsonAdaptedGameEntryTest {

    private static final String VALID_DATE = POKER1_WITHOUT_TIME.getDate().toString();
    private static final String VALID_GAMETYPE = POKER1_WITHOUT_TIME.getGameType().toString();
    private static final String VALID_STARTAMOUNT = POKER1_WITHOUT_TIME.getStartAmount().toString();
    private static final String VALID_ENDAMOUNT = POKER1_WITHOUT_TIME.getEndAmount().toString();
    private static final String VALID_DURATION = POKER1_WITHOUT_TIME.getDuration().toString();
    private static final String VALID_LOCATION = POKER1_WITHOUT_TIME.getLocation().toString();
    private static final String INVALID_TAG = "#friend";
    private static final List<JsonAdaptedTag> VALID_TAGS = POKER1_WITHOUT_TIME.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validGameDetails_returnsGame() throws Exception {
        JsonAdaptedGameEntry gameEntry = new JsonAdaptedGameEntry(POKER1_WITHOUT_TIME);
        assertEquals(POKER1_WITHOUT_TIME.toString(), gameEntry.toModelType().toString());
    }

    @Test
    public void toModelType_nullGameType_throwsIllegalValueException() {
        JsonAdaptedGameEntry gameEntry = new JsonAdaptedGameEntry(null,
                VALID_STARTAMOUNT, VALID_ENDAMOUNT, VALID_DATE, VALID_DURATION, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GameType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, gameEntry::toModelType);
    }

    @Test
    public void toModelType_nullStartAmount_throwsIllegalValueException() {
        JsonAdaptedGameEntry gameEntry =
                new JsonAdaptedGameEntry(VALID_GAMETYPE, null,
                        VALID_ENDAMOUNT, VALID_DATE, VALID_DURATION, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "start amount");
        assertThrows(IllegalValueException.class, expectedMessage, gameEntry::toModelType);
    }

    @Test
    public void toModelType_nullEndAmount_throwsIllegalValueException() {
        JsonAdaptedGameEntry gameEntry =
                new JsonAdaptedGameEntry(VALID_GAMETYPE,
                        VALID_STARTAMOUNT, null, VALID_DATE, VALID_DURATION, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "end amount");
        assertThrows(IllegalValueException.class, expectedMessage, gameEntry::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedGameEntry gameEntry =
                new JsonAdaptedGameEntry(VALID_GAMETYPE,
                        VALID_STARTAMOUNT, VALID_ENDAMOUNT, null, VALID_DURATION, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "date played");
        assertThrows(IllegalValueException.class, expectedMessage, gameEntry::toModelType);
    }


    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        JsonAdaptedGameEntry gameEntry =
                new JsonAdaptedGameEntry(VALID_GAMETYPE,
                        VALID_STARTAMOUNT, VALID_ENDAMOUNT, VALID_DATE, null, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "duration");
        assertThrows(IllegalValueException.class, expectedMessage, gameEntry::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedGameEntry gameEntry =
                new JsonAdaptedGameEntry(VALID_GAMETYPE,
                        VALID_STARTAMOUNT, VALID_ENDAMOUNT, VALID_DATE, VALID_DURATION, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "location");
        assertThrows(IllegalValueException.class, expectedMessage, gameEntry::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedGameEntry gameEntry =
                new JsonAdaptedGameEntry(VALID_GAMETYPE,
                        VALID_STARTAMOUNT, VALID_ENDAMOUNT, VALID_DATE, VALID_DURATION, VALID_LOCATION, invalidTags);
        assertThrows(IllegalValueException.class, gameEntry::toModelType);
    }

}
