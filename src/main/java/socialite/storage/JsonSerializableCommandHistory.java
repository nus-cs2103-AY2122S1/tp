package socialite.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import socialite.model.CommandHistory;
import socialite.model.ReadOnlyCommandHistory;

/**
 * An immutable CommandHistory that is serializable to JSON format.
 */
@JsonRootName(value = "history")
public class JsonSerializableCommandHistory {
    private final List<String> history = new ArrayList<>();

    @JsonCreator
    public JsonSerializableCommandHistory(@JsonProperty("history") List<String> history) {
        this.history.addAll(history);
    }

    public JsonSerializableCommandHistory(ReadOnlyCommandHistory source) {
        history.addAll(source.getCommandHistory());
    }

    /**
     * Converts this command history into the model's {@code CommandHistory} object.
     */
    public CommandHistory toModelType() {
        CommandHistory commandHistory = new CommandHistory();
        for (String command : history) {
            commandHistory.addCommand(command);
        }
        return commandHistory;
    }

}
