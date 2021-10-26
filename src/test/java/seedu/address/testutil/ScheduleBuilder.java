package seedu.address.testutil;

import seedu.address.model.person.Schedule;
import seedu.address.model.person.Shift;

/**
 * An ultility class for Schedule.
 */
public class ScheduleBuilder {
    private static final ShiftBuilder[][] DEFAULT_SHIFTS = new ShiftBuilder[7][2];
    private ShiftBuilder[][] shifts;

    /**
     * Creates a {@code ScheduleBuilder} with the data of the {@code scheduleToCopy}.
     *
     */
    public ScheduleBuilder(Schedule scheduleToCopy) {
        Shift[][] shifts = scheduleToCopy.getShifts();
        this.shifts = new ShiftBuilder[shifts.length][shifts[1].length];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 2; j++) {
                if (shifts[i][j] == null) {
                    continue;
                }
                this.shifts[i][j] = new ShiftBuilder(shifts[i][j]);
            }
        }
    }

    /**
     * Creates a {@code ScheduleBuilder} with the default details.
     */
    public ScheduleBuilder() {
        this.shifts = DEFAULT_SHIFTS;
    }

    /**
     * Sets the {@code ShiftBuilder} for the Schedule that we are building.
     *
     */
    public void withShift(ShiftBuilder[][] shifts) {
        this.shifts = shifts;
    }

    /**
     * Returns a new Schedule object with the fields set to the object.
     */
    public Schedule build() {
        Shift[][] shifts = new Shift[7][2];
        for (int i = 0; i < shifts.length; i++) {
            for (int j = 0; j < shifts[i].length; j++) {
                if (this.shifts[i][j] == null) {
                    continue;
                }
                shifts[i][j] = this.shifts[i][j].build();
            }
        }
        return new Schedule(shifts);
    }



}
