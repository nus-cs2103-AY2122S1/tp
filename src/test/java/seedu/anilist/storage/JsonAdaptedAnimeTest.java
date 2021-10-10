package seedu.anilist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.anilist.storage.JsonAdaptedAnime.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.anilist.testutil.Assert.assertThrows;
import static seedu.anilist.testutil.TypicalAnimes.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.anilist.commons.exceptions.IllegalValueException;
import seedu.anilist.model.anime.Name;

public class JsonAdaptedAnimeTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validAnimeDetails_returnsAnime() throws Exception {
        JsonAdaptedAnime anime = new JsonAdaptedAnime(BENSON);
        assertEquals(BENSON, anime.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAnime anime =
                new JsonAdaptedAnime(INVALID_NAME, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, anime::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAnime anime = new JsonAdaptedAnime(null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, anime::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedAnime anime =
                new JsonAdaptedAnime(VALID_NAME, invalidTags);
        assertThrows(IllegalValueException.class, anime::toModelType);
    }

}
