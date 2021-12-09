package seedu.times.storage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.times.commons.exceptions.IllegalValueException;
import seedu.times.model.person.Name;
import seedu.times.model.tuitionclass.ClassName;
import seedu.times.model.tuitionclass.ClassTiming;
import seedu.times.model.tuitionclass.Location;
import seedu.times.model.tuitionclass.Rate;
import seedu.times.model.tuitionclass.StudentNameList;
import seedu.times.model.tuitionclass.TuitionClass;

/**
 * Jackson-friendly version of {@link TuitionClass}.
 */
public class JsonAdaptedTuitionClass {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Class's %s field is missing!";

    private final String classTiming;
    private final String className;
    private final String location;
    private final String rate;
    private final List<String> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTuitionClass(@JsonProperty("classTiming") String classTiming,
                              @JsonProperty("className") String className,
                              @JsonProperty("rate") String rate,
                              @JsonProperty("location") String location,
                              @JsonProperty("students") List<String> students) {
        this.classTiming = classTiming;
        this.className = className;
        this.location = location;
        this.rate = rate;
        if (students != null) {
            this.students.addAll(students);
        }
    }

    /**
     * Converts a given {@code TuitionClass} into this class for Jackson use.
     */
    public JsonAdaptedTuitionClass(TuitionClass source) {
        classTiming = source.getClassTiming().value;
        className = source.getClassName().className;
        location = source.getLocation().value;
        rate = source.getRate().value;

        Iterator iterator = source.getStudentList().iterator();
        while (iterator.hasNext()) {
            students.add(iterator.next().toString());
        }

    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code TuitionClass} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted TuitionClass.
     */
    public TuitionClass toModelType() throws IllegalValueException {

        if (classTiming == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClassTiming.class.getSimpleName()));
        }
        if (!ClassTiming.isValidClassTiming(classTiming)) {
            throw new IllegalValueException(ClassTiming.MESSAGE_CONSTRAINTS);
        }
        final ClassTiming modelTiming = new ClassTiming(classTiming);

        if (className == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClassName.class.getSimpleName()));
        }
        if (!ClassName.isValidClassName(className)) {
            throw new IllegalValueException(ClassName.MESSAGE_CONSTRAINTS);
        }
        final ClassName modelName = new ClassName(className);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        if (rate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Rate.class.getSimpleName()));
        }
        if (!Rate.isValidRate(rate)) {
            throw new IllegalValueException(Rate.MESSAGE_CONSTRAINTS);
        }
        final Rate modelRate = new Rate(rate);

        final StudentNameList tuitionClassNameList = new StudentNameList();
        for (String name : students) {
            tuitionClassNameList.add(new Name(name));
        }

        return new TuitionClass(modelName, modelTiming, modelLocation, modelRate, tuitionClassNameList);
    }
}
