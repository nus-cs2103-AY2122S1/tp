package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.Shortcut;

/**
 * Jackson-friendly version of {@link Shortcut}.
 */
class JsonAdaptedShortcut {

    private final String keyword;
    private final String commandString;

    /**
     * Constructs a {@code JsonAdaptedShortcut} with the given {@code keyword} and {@code command}.
     */
    @JsonCreator
    public JsonAdaptedShortcut(@JsonProperty("keyword") String keyword, @JsonProperty("command") String commandString) {
        this.keyword = keyword;
        this.commandString = commandString;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getCommandString() {
        return commandString;
    }

}
