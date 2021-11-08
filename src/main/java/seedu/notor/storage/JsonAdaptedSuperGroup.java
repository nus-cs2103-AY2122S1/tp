package seedu.notor.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.notor.commons.exceptions.IllegalValueException;
import seedu.notor.model.common.Name;
import seedu.notor.model.common.Note;
import seedu.notor.model.group.SuperGroup;

public class JsonAdaptedSuperGroup {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's format is wrong!";

    private final String name;

    private List<JsonAdaptedSubGroup> subGroups = new ArrayList<>();

    private final String note;
    private final String noteDate;

    /**
     * Constructs a {@code JsonAdaptedSuperGroup} with the given group details.
     */
    @JsonCreator
    public JsonAdaptedSuperGroup(@JsonProperty("name") String name, @JsonProperty("note") String note,
            @JsonProperty("noteDate") String noteDate,
            @JsonProperty("subGroups") List<JsonAdaptedSubGroup> subGroups) {
        this.name = name;
        this.subGroups.addAll(subGroups);
        this.note = note;
        this.noteDate = noteDate;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedSuperGroup(SuperGroup source) {
        this.name = source.getName();
        subGroups.addAll(source.getSubGroups().asUnmodifiableObservableList().stream()
                .map(JsonAdaptedSubGroup::new)
                .collect(Collectors.toList()));
        note = source.getNote().value;
        noteDate = source.getNote().getSavedDate();
    }

    /**
     * Converts this Jackson-friendly adapted SuperGroup object into the model's {@code SuperGroup}
     * and {@code SuperGroup} objects.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public SuperGroup toModelType() throws IllegalValueException {
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        if (note == null) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }
        if (noteDate == null) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }
        final Note modelNote = Note.of(note, noteDate);
        SuperGroup group = new SuperGroup(new Name(name), modelNote);
        for (JsonAdaptedSubGroup subGroup : this.subGroups) {
            group.addSubGroup(subGroup.toModelType());
        }
        return group;
    }
}
