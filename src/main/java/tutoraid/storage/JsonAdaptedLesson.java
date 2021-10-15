package tutoraid.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tutoraid.commons.exceptions.IllegalValueException;
import tutoraid.model.lesson.Capacity;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.Name;
import tutoraid.model.lesson.Price;
import tutoraid.model.lesson.Students;
import tutoraid.model.lesson.Timing;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
public class JsonAdaptedLesson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lesson's %s field is missing!";

    private final String lessonName;
    private final String capacity;
    private final String price;
    private final String students;
    private final String timing;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(
            @JsonProperty("lessonName") String lessonName, @JsonProperty("capacity") String capacity,
            @JsonProperty("price") String price, @JsonProperty("students") String students,
            @JsonProperty("timing") String timing) {
        this.lessonName = lessonName;
        this.capacity = capacity;
        this.price = price;
        this.students = students;
        this.timing = timing;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        lessonName = source.getName().name;
        capacity = source.getCapacity().value;
        price = source.getPrice().value;
        students = source.getStudents().value;
        timing = source.getTiming().value;
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType() throws IllegalValueException {
        if (lessonName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(lessonName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelLessonName = new Name(lessonName);

        if (!capacity.equals("") && !Capacity.isValidCapacity(capacity)) {
            throw new IllegalValueException(Capacity.MESSAGE_CONSTRAINTS);
        }
        final Capacity modelLessonCapacity = new Capacity(capacity);

        if (!price.equals("") && !Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelLessonPrice = new Price(price);

        if (!students.equals("") && !Students.isValidStudents(students)) {
            throw new IllegalValueException(Students.MESSAGE_CONSTRAINTS);
        }
        final Students modelLessonStudents = new Students(students);

        if (timing == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Timing.class.getSimpleName()));
        }
        if (!Timing.isValidTiming(timing)) {
            throw new IllegalValueException(Timing.MESSAGE_CONSTRAINTS);
        }
        final Timing modelLessonTiming = new Timing(timing);

        return new Lesson(modelLessonName, modelLessonCapacity, modelLessonPrice,
                modelLessonStudents, modelLessonTiming);
    }
}
