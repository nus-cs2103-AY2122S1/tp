package seedu.smartnus.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.smartnus.commons.exceptions.IllegalValueException;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.note.NoteName;

public class JsonAdaptedNote {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Note's %s field is missing!";

    private final String title;

    /**
     * Constructs a {@code JsonAdaptedQuestion} with the given question details.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("title") String title) {
        this.title = title;
    }

    /**
     * Converts a given {@code Question} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        title = source.getTitle();
    }

    /**
     * Converts this Jackson-friendly adapted question object into the model's {@code Question} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted question.
     */
    public Note toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NoteName.class.getSimpleName()));
        }
        if (!NoteName.isValidName(title)) {
            throw new IllegalValueException(NoteName.MESSAGE_CONSTRAINTS);
        }
        final NoteName modelName = new NoteName(title);
        return new Note(modelName.fullName);
    }

}
