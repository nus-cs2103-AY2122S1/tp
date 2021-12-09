package seedu.times.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.times.commons.exceptions.IllegalValueException;
import seedu.times.model.ReadOnlyTimesTable;
import seedu.times.model.TimesTable;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;

/**
 * An Immutable TimesTable that is serializable to JSON format.
 */
@JsonRootName(value = "timestable")
class JsonSerializableTimesTable {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_CLASS = "Class list contains duplicate class(es).";

    private final List<JsonAdaptedStudent> persons = new ArrayList<>();
    private final List<JsonAdaptedTuitionClass> classes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTimesTable} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTimesTable(@JsonProperty("persons") List<JsonAdaptedStudent> persons,
                                      @JsonProperty("classes") List<JsonAdaptedTuitionClass> classes) {
        this.persons.addAll(persons);
        this.classes.addAll(classes);
    }

    /**
     * Converts a given {@code ReadOnlyTimesTable} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTimesTable}.
     */
    public JsonSerializableTimesTable(ReadOnlyTimesTable source) {
        persons.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        classes.addAll(source.getTuitionClassList().stream().map(JsonAdaptedTuitionClass::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TimesTable} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TimesTable toModelType() throws IllegalValueException {
        TimesTable timesTable = new TimesTable();

        for (JsonAdaptedStudent jsonAdaptedStudent : persons) {
            Student student = jsonAdaptedStudent.toModelType();
            if (timesTable.hasPerson(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            timesTable.addStudent(student);
        }

        for (JsonAdaptedTuitionClass jsonAdaptedTuitionClass : classes) {
            TuitionClass tuitionClass = jsonAdaptedTuitionClass.toModelType();
            if (timesTable.hasTuitionClass(tuitionClass)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLASS);
            }
            timesTable.addTuitionClass(tuitionClass);
        }

        return timesTable;
    }

}
