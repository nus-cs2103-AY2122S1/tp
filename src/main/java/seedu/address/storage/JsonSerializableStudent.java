package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialclass.TutorialClass;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableStudent {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_TUTORIAL_CLASS =
            "Tutorial Classes list contains duplicate tutorial class(es).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedTutorialClass> tutorialClasses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStudent} with the given students.
     */
    @JsonCreator
    public JsonSerializableStudent(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                   @JsonProperty("tutorialClasses") List<JsonAdaptedTutorialClass> tutorialClasses) {
        this.students.addAll(students);
        this.tutorialClasses.addAll(tutorialClasses);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStudent}.
     */
    public JsonSerializableStudent(ReadOnlyAddressBook source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        tutorialClasses.addAll(source
                .getTutorialClassList()
                .stream()
                .map(JsonAdaptedTutorialClass::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (addressBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            addressBook.addStudent(student);
        }
        for (JsonAdaptedTutorialClass jsonAdaptedTutorialClass : tutorialClasses) {
            TutorialClass tutorialClass = jsonAdaptedTutorialClass.toModelType();
            if (addressBook.hasTutorialClass(tutorialClass)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUTORIAL_CLASS);
            }
            addressBook.addTutorialClass(tutorialClass);
        }
        return addressBook;
    }

}
