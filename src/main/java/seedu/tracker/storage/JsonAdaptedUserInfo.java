package seedu.tracker.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.tracker.commons.exceptions.IllegalValueException;
import seedu.tracker.model.UserInfo;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.module.Mc;


/**
 * Jackson-friendly version of {@link UserInfo}.
 */
public class JsonAdaptedUserInfo {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "The %s field of User Information is missing!";

    private final JsonAdaptedCalendar currentSemester;
    private final int mcGoal;

    /**
     * Constructs a {@code JsonAdaptedUserInfo} with the given user info details.
     */
    @JsonCreator
    public JsonAdaptedUserInfo(@JsonProperty("currentSemester") JsonAdaptedCalendar currentSemester,
                             @JsonProperty("mcGoal") int mcGoal) {
        this.currentSemester = currentSemester;
        this.mcGoal = mcGoal;
    }

    /**
     * Converts a given {@code UserInfo} into this class for Jackson use.
     */
    public JsonAdaptedUserInfo(UserInfo source) {
        mcGoal = source.getMcGoal().value;
        currentSemester = new JsonAdaptedCalendar(source.getCurrentYear().value, source.getSemester().value);
    }

    /**
     * Converts this Jackson-friendly adapted user info object into the model's {@code UserInfo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted user info.
     */
    public UserInfo toModelType() throws IllegalValueException {
        if (!Mc.isValidMc(mcGoal)) {
            throw new IllegalValueException(Mc.MESSAGE_CONSTRAINTS);
        }
        final Mc infoMc = new Mc(mcGoal);

        if (currentSemester == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, AcademicCalendar.class.getSimpleName()));
        }
        final AcademicCalendar infoSemester = currentSemester.toModelType();
        return new UserInfo(infoSemester, infoMc);
    }

    public static void main(String[] args) {

    }
}
