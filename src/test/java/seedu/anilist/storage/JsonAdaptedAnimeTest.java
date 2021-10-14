package seedu.anilist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.anilist.storage.JsonAdaptedAnime.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.anilist.testutil.Assert.assertThrows;
import static seedu.anilist.testutil.TypicalAnimes.BRS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.anilist.commons.exceptions.IllegalValueException;
import seedu.anilist.model.anime.Episode;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.anime.Status;

public class JsonAdaptedAnimeTest {
    private static final String INVALID_NAME = " ";
    private static final String INVALID_GENRE = "#adventure";
    private static final String INVALID_EPISODE = "-1";
    private static final String INVALID_STATUS = "Wishlist";

    private static final String VALID_NAME = BRS.getName().toString();
    private static final String VALID_EPISODE = BRS.getEpisode().toString();
    private static final String VALID_STATUS = BRS.getStatus().toString();
    private static final List<JsonAdaptedGenre> VALID_GENRES = BRS.getGenres().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validAnimeDetails_returnsAnime() throws Exception {
        JsonAdaptedAnime anime = new JsonAdaptedAnime(BRS);
        assertEquals(BRS, anime.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAnime anime =
                new JsonAdaptedAnime(INVALID_NAME, VALID_EPISODE, VALID_STATUS, VALID_GENRES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, anime::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAnime anime = new JsonAdaptedAnime(null, VALID_EPISODE, VALID_STATUS, VALID_GENRES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, anime::toModelType);
    }

    public void toModelType_invalidEpisode_throwsIllegalValueException() {
        JsonAdaptedAnime anime =
                new JsonAdaptedAnime(VALID_NAME, INVALID_EPISODE, VALID_STATUS, VALID_TAGS);
        String expectedMessage = Episode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, anime::toModelType);
    }

    @Test
    public void toModelType_nullEpisode_throwsIllegalValueException() {
        JsonAdaptedAnime anime = new JsonAdaptedAnime(VALID_NAME, null, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Episode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, anime::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedAnime anime =
                new JsonAdaptedAnime(VALID_NAME, VALID_EPISODE, INVALID_STATUS, VALID_TAGS);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, anime::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedAnime anime = new JsonAdaptedAnime(VALID_NAME, VALID_EPISODE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, anime::toModelType);
    }

    @Test
    public void toModelType_invalidGenres_throwsIllegalValueException() {
        List<JsonAdaptedGenre> invalidGenres = new ArrayList<>(VALID_GENRES);
        invalidGenres.add(new JsonAdaptedGenre(INVALID_GENRE));
        JsonAdaptedAnime anime =
                new JsonAdaptedAnime(VALID_NAME, invalidGenres);
        assertThrows(IllegalValueException.class, anime::toModelType);
    }
}
