package seedu.smartnus.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.smartnus.commons.exceptions.IllegalValueException;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Question;

/**
 * An Immutable SmartNus that is serializable to JSON format.
 */
@JsonRootName(value = "smartnus")
class JsonSerializableSmartNus {

    public static final String MESSAGE_DUPLICATE_QUESTION = "Questions list contains duplicate question(s).";

    private final List<JsonAdaptedQuestion> questions = new ArrayList<>();
    private final List<JsonAdaptedNote> notes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSmartNus} with the given questions.
     */
    @JsonCreator
    public JsonSerializableSmartNus(@JsonProperty("questions") List<JsonAdaptedQuestion> questions,
                                    @JsonProperty("notes") List<JsonAdaptedNote> notes) {
        this.questions.addAll(questions);
        this.notes.addAll(notes);
    }

    /**
     * Converts a given {@code ReadOnlySmartNus} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSmartNus}.
     */
    public JsonSerializableSmartNus(ReadOnlySmartNus source) {
        questions.addAll(source.getQuestionList().stream().map(JsonAdaptedQuestion::new).collect(Collectors.toList()));
        notes.addAll(source.getNoteList().stream().map(JsonAdaptedNote::new).collect(Collectors.toList()));
    }

    /**
     * Converts this {@code JsonSerializableSmartNus} into the model's {@code SmartNus} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SmartNus toModelType() throws IllegalValueException {
        SmartNus smartNus = new SmartNus();
        for (JsonAdaptedQuestion jsonAdaptedQuestion : questions) {
            Question question = jsonAdaptedQuestion.toModelType();
            if (smartNus.hasQuestion(question)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_QUESTION);
            }
            smartNus.addQuestion(question);
        }

        for (JsonAdaptedNote jsonAdaptedNote : notes) {
            Note note = jsonAdaptedNote.toModelType();
            smartNus.addNote(note);
        }
        return smartNus;
    }
}
