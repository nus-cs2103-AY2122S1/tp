package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyShortcut;
import seedu.address.model.Shortcut;

/**
 * An Immutable Shortcut that is serializable to JSON format.
 */
@JsonRootName(value = "shortcut")
class JsonSerializableShortcut {

    private final List<JsonAdaptedShortcut> shortcuts = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableShortcut} with the given shortcuts.
     */
    @JsonCreator
    public JsonSerializableShortcut(@JsonProperty("shortcuts") List<JsonAdaptedShortcut> shortcuts) {
        this.shortcuts.addAll(shortcuts);
    }

    /**
     * Converts a given {@code ReadOnlyShortcut} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableShortcut}.
     */
    public JsonSerializableShortcut(ReadOnlyShortcut source) {
        shortcuts.clear();
        source.getShortcutMap().forEach((key, value)-> shortcuts.add(new JsonAdaptedShortcut(key, value)));
    }

    /**
     * Converts this shortcut list into the model's {@code Shortcut} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Shortcut toModelType() {
        Shortcut shortcutModel = new Shortcut();
        HashMap<String, String> shortcutMap = new HashMap<>();
        for (JsonAdaptedShortcut shortcut: shortcuts) {
            shortcutMap.put(shortcut.getKeyword(), shortcut.getCommandString());
        }
        shortcutModel.setShortcutMap(shortcutMap);
        return shortcutModel;
    }

}
