package dash.storage.userinputlist;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import dash.commons.exceptions.IllegalValueException;
import dash.model.UserInputList;

/**
 * An Immutable UserInputList that is serializable to JSON format.
 */
@JsonRootName(value = "userinputlist")
class JsonSerializableUserInputList {

    private final List<String> userInputs = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableUserInputList} with the given user inputs.
     */
    @JsonCreator
    public JsonSerializableUserInputList(@JsonProperty("userInputs") List<String> userInputs) {
        this.userInputs.addAll(userInputs);
    }

    /**
     * Converts a given {@code TaskList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskList}.
     */
    public JsonSerializableUserInputList(UserInputList source) {
        userInputs.addAll(source.getInternalUserInputList());
    }

    /**
     * Converts this task list into the model's {@code TaskList} object.
     */
    public UserInputList toModelType() throws IllegalValueException {
        return new UserInputList(userInputs);

    }

}
