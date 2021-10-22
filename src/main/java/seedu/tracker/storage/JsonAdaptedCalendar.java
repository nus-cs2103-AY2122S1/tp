package seedu.tracker.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.tracker.commons.exceptions.IllegalValueException;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;

/**
 * Jackson-friendly version of {@link AcademicCalendar}.
 */
public class JsonAdaptedCalendar {
    private final int year;
    private final int semester;

    /**
     * Constructs a {@code JsonAdaptedCalendar} with the given calendar details.
     */
    @JsonCreator
    public JsonAdaptedCalendar(@JsonProperty("year") int year,
                               @JsonProperty("semester") int semester) {
        this.year = year;
        this.semester = semester;
    }

    /**
     * Converts a given {@code AcademicCalendar} into this class for Jackson use.
     */
    public JsonAdaptedCalendar(AcademicCalendar source) {
        this.year = source.getAcademicYear().value;
        this.semester = source.getSemester().value;
    }

    @JsonValue
    public int getYear() {
        return year;
    }

    @JsonValue
    public int getSemester() {
        return semester;
    }

    /**
     * Converts this Jackson-friendly adapted calendar object into the model's {@code AcademicCalendar} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public AcademicCalendar toModelType() throws IllegalValueException {
        if (!AcademicCalendar.isValidAcademicCalendar(year, semester)) {
            throw new IllegalValueException(AcademicCalendar.MESSAGE_CONSTRAINTS);
        }
        return new AcademicCalendar(new AcademicYear(year), new Semester(semester));
    }
}
