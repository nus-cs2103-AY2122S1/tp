package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.participant.Note;

public class JsonAdaptedNote {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Next of Kin's %s field is missing!";

    private final String content;
    private final String importance;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("content") String content,
                           @JsonProperty("importance") String importance) {
        this.content = content;
        this.importance = importance;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        content = source.getContent();
        importance = source.getImportance().toString();
    }

    public Note toModelType() throws IllegalValueException {
        return new Note(content, ParserUtil.parseImportance(importance));
    }

}
