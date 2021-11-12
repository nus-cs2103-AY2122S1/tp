package seedu.anilist.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.anilist.commons.exceptions.IllegalValueException;
import seedu.anilist.model.AnimeList;
import seedu.anilist.model.ReadOnlyAnimeList;
import seedu.anilist.model.anime.Anime;

/**
 * An Immutable AnimeList that is serializable to JSON format.
 */
@JsonRootName(value = "anilist")
class JsonSerializableAnimeList {

    public static final String MESSAGE_DUPLICATE_ANIME = "Anime list contains duplicate anime(s).";

    private final List<JsonAdaptedAnime> anime = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAnimeList} with the given anime.
     */
    @JsonCreator
    public JsonSerializableAnimeList(@JsonProperty("anime") List<JsonAdaptedAnime> anime) {
        this.anime.addAll(anime);
    }

    /**
     * Converts a given {@code ReadOnlyAnimeList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAnimeList}.
     */
    public JsonSerializableAnimeList(ReadOnlyAnimeList source) {
        anime.addAll(source.getAnimeList().stream().map(JsonAdaptedAnime::new).collect(Collectors.toList()));
    }

    /**
     * Converts this anime list into the model's {@code AnimeList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AnimeList toModelType() throws IllegalValueException {
        AnimeList animeList = new AnimeList();
        for (JsonAdaptedAnime jsonAdaptedAnime : anime) {
            Anime anime = jsonAdaptedAnime.toModelType();
            if (animeList.hasAnime(anime)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ANIME);
            }
            animeList.addAnime(anime);
        }
        return animeList;
    }

}
