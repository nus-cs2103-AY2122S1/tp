package seedu.gamebook.storage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.gamebook.commons.exceptions.IllegalValueException;
import seedu.gamebook.model.GameBook;
import seedu.gamebook.model.ReadOnlyGameBook;
import seedu.gamebook.model.gameentry.GameEntry;

/**
 * An Immutable GameBook that is serializable to JSON format.
 */
@JsonRootName(value = "gamebook")
class JsonSerializableGameBook {

    private final List<JsonAdaptedGameEntry> gameEntries = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableGameBook} with the given game entries.
     */
    @JsonCreator
    public JsonSerializableGameBook(@JsonProperty("gameEntries") List<JsonAdaptedGameEntry> gameEntries) {
        this.gameEntries.addAll(gameEntries);
    }

    /**
     * Converts a given {@code ReadOnlyGameBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableGameBook}.
     */
    public JsonSerializableGameBook(ReadOnlyGameBook source) {
        gameEntries.addAll(source.getGameEntryList().stream()
                .map(JsonAdaptedGameEntry::new).collect(Collectors.toList()));
    }

    /**
     * Converts this game book into the model's {@code GameBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public GameBook toModelType() throws IllegalValueException, ParseException {
        GameBook gameBook = new GameBook();
        for (JsonAdaptedGameEntry jsonAdaptedGameEntry : gameEntries) {
            GameEntry gameEntry = jsonAdaptedGameEntry.toModelType();
            gameBook.addGameEntry(gameEntry);
        }
        return gameBook;
    }

}
