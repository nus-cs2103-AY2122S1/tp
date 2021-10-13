package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.GamesList;
import seedu.address.model.ReadOnlyGamesList;
import seedu.address.model.game.Game;

/**
 * An Immutable games list that is serializable to JSON format.
 */
@JsonRootName(value = "games")
class JsonSerializableGamesList {

    public static final String MESSAGE_DUPLICATE_GAME = "Games list contains duplicate game(s).";

    private final List<JsonAdaptedGame> games = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableGamesList} with the given games.
     */
    @JsonCreator
    public JsonSerializableGamesList(@JsonProperty("games") List<JsonAdaptedGame> games) {
        this.games.addAll(games);
    }

    /**
     * Converts a given {@code ReadOnlyGamesList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableGamesList}.
     */
    public JsonSerializableGamesList(ReadOnlyGamesList source) {
        games.addAll(source.getGamesList().stream().map(JsonAdaptedGame::new).collect(Collectors.toList()));
    }

    /**
     * Converts this games list into the model's {@code GamesList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public GamesList toModelType() throws IllegalValueException {
        GamesList gamesList = new GamesList();
        for (JsonAdaptedGame jsonAdaptedGame : games) {
            Game game = jsonAdaptedGame.toModelType();
            if (gamesList.hasGame(game)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GAME);
            }
            gamesList.addGame(game);
        }
        return gamesList;
    }

}
