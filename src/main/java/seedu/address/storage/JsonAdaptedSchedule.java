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

    public Schedule toModelType() throws IllegalValueException {
        Shift[][] modelShifts = new Shift[DAY_OF_WEEK][PERIOD_OF_DAY];
        for (int i = 0; i < PERIOD_OF_DAY; i++) {
            for (int j = 0; j < DAY_OF_WEEK; j++) {
                if (this.shifts[j][i] == null) {
                    modelShifts[j][i] = null;
                    continue;
                }
                modelShifts[j][i] = this.shifts[j][i].toModelType();
            }
        }
        return new Schedule(modelShifts);

    }


}
