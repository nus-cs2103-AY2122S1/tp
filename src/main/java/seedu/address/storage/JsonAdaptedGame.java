package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;

/**
 * Jackson-friendly version of {@link Game}.
 */
class JsonAdaptedGame {

    private final String gameName;

    /**
     * Constructs a {@code JsonAdaptedGame} with the given gameName {@code String}.
     */
    @JsonCreator
    public JsonAdaptedGame(String gameName) {
        this.gameName = gameName;
    }

    /**
     * Converts a given {@code Game} into this class for Jackson use.
     */
    public JsonAdaptedGame(Game sourceInstance) {
        gameName = sourceInstance.getGameId().value;
    }

    @JsonValue
    public String getGameName() {
        return gameName;
    }

    /**
     * Converts this Jackson-friendly adapted game object into the model's {@code Game} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Game.
     */
    public Game toModelType() throws IllegalValueException {
        if (!GameId.isValidGameId(gameName)) {
            throw new IllegalValueException(GameId.MESSAGE_CONSTRAINTS);
        }
        return new Game(new GameId(gameName));
    }

}
