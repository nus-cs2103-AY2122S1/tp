package seedu.address.storage;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tuition.ClassLimit;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.Counter;
import seedu.address.model.tuition.StudentList;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedTuition {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final int limit;
    private final int counter;
    private final String timeslot;
    private final ArrayList<String> student = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTuition(@JsonProperty("name") String name, @JsonProperty("limit") int limit,
                             @JsonProperty("counter") int counter, @JsonProperty("timeslot") String timeslot,
                             @JsonProperty("student") ArrayList<String> student) {
        this.name = name;
        this.limit = limit;
        this.counter = counter;
        this.timeslot = timeslot;

        if (student != null) {
            this.student.addAll(student);
        }


    }

    /**
     * Converts a given {@code TuitionClass} into this class for Jackson use.
     */
    public JsonAdaptedTuition(TuitionClass source) {
        name = source.getName().getName();
        limit = source.getLimit().getLimit();
        counter = source.getCounter().getCounter();
        timeslot = source.getTimeslot().getTime();
        student.addAll(source.getStudentList().getStudents());
    }

    /**
     * Converts this Jackson-friendly adapted tuition object into the model's {@code TuitionClass} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public TuitionClass toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final ClassName modelName = new ClassName(name);

        final ClassLimit modelLimit = new ClassLimit(limit);

        final Counter modelCounter = new Counter(counter);

        if (timeslot == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }

        final Timeslot modelTimeslot = new Timeslot(timeslot);

        final StudentList modelStudent = new StudentList(student);
        return new TuitionClass(modelName, modelLimit, modelCounter, modelTimeslot, modelStudent);

    }

}
