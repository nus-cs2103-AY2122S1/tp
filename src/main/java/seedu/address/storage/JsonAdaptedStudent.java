package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.TelegramHandle;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    public static final String MESSAGE_GROUP_NAME_NOT_FOUND = "No matching group can be found in the group "
                                                                + "list with the same group name as the student's.";

    private final String name;
    private final String telegramHandle;
    private final String email;
    private final String groupName;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("telegramHandle") String telegramHandle,
                              @JsonProperty("email") String email, @JsonProperty("groupName") String groupName) {
        this.name = name;
        this.telegramHandle = telegramHandle;
        this.email = email;
        this.groupName = groupName;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        telegramHandle = source.getTelegramHandle().value;
        email = source.getEmail().value;
        groupName = source.getGroupName().toString();
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType(ObservableList<Group> groupList) throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (telegramHandle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TelegramHandle.class.getSimpleName()));
        }
        if (!TelegramHandle.isValidTelegramHandle(telegramHandle)) {
            throw new IllegalValueException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        final TelegramHandle modelTelegramHandle = new TelegramHandle(this.telegramHandle);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (groupName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                GroupName.class.getSimpleName()));
        }
        if (!GroupName.isValidName(groupName)) {
            throw new IllegalValueException(GroupName.MESSAGE_CONSTRAINTS);
        }
        final GroupName modelGroupName = new GroupName(groupName);

        if (!groupExistsInGroupList(modelGroupName, groupList)) {
            throw new IllegalValueException(MESSAGE_GROUP_NAME_NOT_FOUND);
        }

        return new Student(modelName, modelTelegramHandle, modelEmail, modelGroupName);
    }

    /**
     * Returns true if the name of a {@code Group} from a given group list matches the given {@code GroupName}.
     * @return group from the group list that matches the given group name
     */
    public boolean groupExistsInGroupList(GroupName groupName, ObservableList<Group> groupList) {
        requireNonNull(groupName);
        for (Group group : groupList) {
            if (group.getGroupName().equals(groupName)) {
                return true;
            }
        }
        return false;
    }
}
