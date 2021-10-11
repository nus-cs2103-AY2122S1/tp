package tutoraid.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import tutoraid.commons.exceptions.IllegalValueException;
import tutoraid.model.StudentBook;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.student.Student;

/**
 * An Immutable StudentBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableStudentBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate student(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStudentBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableStudentBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyStudentBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStudentBook}.
     */
    public JsonSerializableStudentBook(ReadOnlyStudentBook source) {
        persons.addAll(source.getStudentList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code StudentBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StudentBook toModelType() throws IllegalValueException {
        StudentBook studentBook = new StudentBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Student student = jsonAdaptedPerson.toModelType();
            if (studentBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            studentBook.addStudent(student);
        }
        return studentBook;
    }

}
