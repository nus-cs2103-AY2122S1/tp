package seedu.anilist.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.anilist.commons.exceptions.IllegalValueException;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.genre.Genre;

/**
 * Jackson-friendly version of {@link Anime}.
 */
class JsonAdaptedAnime {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Anime's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedGenre> genres = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAnime} with the given Anime details.
     */
    @JsonCreator
    public JsonAdaptedAnime(@JsonProperty("name") String name,
                            @JsonProperty("genres") List<JsonAdaptedGenre> genres) {
        this.name = name;
        if (genres != null) {
            this.genres.addAll(genres);
        }
    }

    /**
     * Converts a given {@code Anime} into this class for Jackson use.
     */
    public JsonAdaptedAnime(Anime source) {
        name = source.getName().fullName;
        genres.addAll(source.getGenres().stream()
                .map(JsonAdaptedGenre::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Anime object into the model's {@code Anime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Anime.
     */
    public Anime toModelType() throws IllegalValueException {
        final List<Genre> animeGenres = new ArrayList<>();
        for (JsonAdaptedGenre genre : genres) {
            animeGenres.add(genre.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);


        final Set<Genre> modelGenres = new HashSet<>(animeGenres);
        return new Anime(modelName, modelGenres);
    }

}
