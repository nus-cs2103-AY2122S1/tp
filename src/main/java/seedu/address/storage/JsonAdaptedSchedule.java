package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Schedule;
import seedu.address.model.person.Shift;

public class JsonAdaptedSchedule {

    private static final int DAY_OF_WEEK = 7;
    private static final int PERIOD_OF_DAY = 2;

    private final JsonAdaptedShift[][] shifts;


    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("shifts") JsonAdaptedShift[][] shifts) {
        this.shifts = shifts;
    }

    /**
     * Constructs a {@code JsonAdaptedSchedule} with the given {@code Schedule} source.
     */
    public JsonAdaptedSchedule(Schedule schedule) {
        Shift[][] shifts = schedule.getShifts();
        this.shifts = new JsonAdaptedShift[DAY_OF_WEEK][PERIOD_OF_DAY];
        for (int i = 0; i < PERIOD_OF_DAY; i++) {
            for (int j = 0; j < DAY_OF_WEEK; j++) {
                if (shifts[j][i] == null) {
                    continue;
                }
                this.shifts[j][i] = new JsonAdaptedShift(shifts[j][i]);
            }
        }
    }

    /**
     * Converts this Jackson-friendly adapted role object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted role.
     */
    public Schedule toModelType() throws IllegalValueException {
        Shift[][] modelShifts = new Shift[DAY_OF_WEEK][PERIOD_OF_DAY];
        for (int i = 0; i < PERIOD_OF_DAY; i++) {
            for (int j = 0; j < DAY_OF_WEEK; j++) {
                if (this.shifts[j][i] == null) {
                    continue;
                }
                Shift shift = this.shifts[j][i].toModelType();
                int location = shift.getDayOfWeek().getValue() - 1;
                int slot = shift.getSlot().getOrder();
                if (modelShifts[location][slot] != null) {
                    throw new IllegalValueException("Duplicate shift entry in storage.");
                }
                modelShifts[location][slot] = shift;

            }
        }
        return new Schedule(modelShifts);
    }
}
