package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.CsBook;
import seedu.address.model.ReadOnlyCsBook;
import seedu.address.model.group.Group;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

/**
 * An Immutable CsBook that is serializable to JSON format.
 */
@JsonRootName(value = "csbook")
class JsonSerializableCsBook {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    public static final String MESSAGE_DUPLICATE_GROUP = "Groups list contains duplicate group(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    private final List<JsonAdaptedGroup> groups = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableCsBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableCsBook(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                  @JsonProperty("groups") List<JsonAdaptedGroup> groups) {
        this.groups.addAll(groups);
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyCsBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCsBook}.
     */
    public JsonSerializableCsBook(ReadOnlyCsBook source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        groups.addAll(source.getGroupList().stream().map(JsonAdaptedGroup::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code CsBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public CsBook toModelType() throws IllegalValueException {
        CsBook csBook = new CsBook();
        addGroupsToCsBook(csBook);
        addStudentsToCsBook(csBook);
        addStudentsToGroups(csBook);

        return csBook;
    }

    public void addGroupsToCsBook(CsBook csBook) throws IllegalValueException {
        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            Group group = jsonAdaptedGroup.toModelType();
            if (csBook.hasGroup(group)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
            }
            csBook.addGroup(group);
        }
    };

    public void addStudentsToCsBook(CsBook csBook) throws IllegalValueException {
        ObservableList<Group> groupList = csBook.getGroupList();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType(groupList);
            if (csBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            csBook.addStudent(student);
        }
    }

    public void addStudentsToGroups(CsBook csBook) {
        ObservableList<Group> groupList = csBook.getGroupList();
        for (Group groupWithoutStudentList : groupList) {
            List<Name> namesOfStudentsInGroup = csBook.getStudentList().stream()
                    .filter(student -> student.getGroupName().equals(groupWithoutStudentList.getGroupName()))
                    .map(student -> student.getName())
                    .collect(Collectors.toList());
            Group groupWithStudentList = new Group(groupWithoutStudentList.getGroupName(),
                    groupWithoutStudentList.getDescription());
            groupWithStudentList.addAllStudentNames(namesOfStudentsInGroup);
            csBook.setGroup(groupWithoutStudentList, groupWithStudentList);
        }
    }
}
